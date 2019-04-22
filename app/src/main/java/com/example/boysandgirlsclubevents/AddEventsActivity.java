package com.example.boysandgirlsclubevents;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.boysandgirlsclubevents.Calendar.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class AddEventsActivity extends AppCompatActivity {

    public static final String TAG = "AddEventsActivity";

    // Activity UI elements.
    private EditText mTitleField;
    private EditText mIconUrlField;
    private Spinner mLocationField;
    private EditText mDescriptionField;
    private EditText mEventDateField;
    private EditText mStartTimeField;
    private EditText mEndTimeField;
    private EditText mLowerAgeField;
    private EditText mUpperAgeField;

    // Formats based on locales.
    public static java.text.DateFormat mDateFormat =
            java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);
    public static java.text.DateFormat mTimeFormat =
            java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT);

    // Database.
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        // Keep references to views contained in activity.
        mTitleField = findViewById(R.id.editText_title);

        // Populate choices for location spinner by creating an array adapter.
        mLocationField = findViewById(R.id.editText_location);
        ArrayAdapter<CharSequence> a = ArrayAdapter.createFromResource(this,
                R.array.clubhouses_selection, android.R.layout.simple_spinner_item);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLocationField.setAdapter(a);

        mIconUrlField = findViewById(R.id.editText_iconUrl);
        mEventDateField = findViewById(R.id.editText_eventDate);
        mStartTimeField = findViewById(R.id.editText_startTime);
        mEndTimeField = findViewById(R.id.editText_endTime);
        mLowerAgeField = findViewById(R.id.editText_lowerAge);
        mUpperAgeField = findViewById(R.id.editText_upperAge);
        mDescriptionField = findViewById(R.id.editText_description);

        // Initialize Firestore.
        mFirestore = FirebaseFirestore.getInstance();
    }

    public void showTimePicker(View v) {
        // Create an instance of the TimePickerFragment subclass.
        DialogFragment df = new TimePickerFragment();

        // Tell the fragment what view to modify.
        Bundle b = new Bundle();
        b.putInt(TimePickerFragment.viewId, v.getId());
        df.setArguments(b);

        // Show the dialog.
        df.show(getSupportFragmentManager(), "Time Picker");
    }

    public void showDatePicker(View v) {
        // Create an instance of DatePickerFragment subclass.
        DialogFragment df = new DatePickerFragment();

        // Tell the fragment what view to modify.
        Bundle b = new Bundle();
        b.putInt(TimePickerFragment.viewId, v.getId());
        df.setArguments(b);

        // Show the dialog.
        df.show(getSupportFragmentManager(), "Date Picker");
    }

    private boolean emptyFieldExists() {
        return mLocationField.getSelectedItem().toString().equals("Select a location") ||
                mTitleField.getText().toString().isEmpty() ||
                mIconUrlField.getText().toString().isEmpty() ||
                mEventDateField.getText().toString().isEmpty() ||
                mStartTimeField.getText().toString().isEmpty() ||
                mEndTimeField.getText().toString().isEmpty() ||
                mUpperAgeField.getText().toString().isEmpty() ||
                mLowerAgeField.getText().toString().isEmpty() ||
                mDescriptionField.getText().toString().isEmpty();
    }

    public boolean isValidAgeRange() {
        Integer lowerAge = Integer.parseInt(mLowerAgeField.getText().toString());
        Integer upperAge = Integer.parseInt(mUpperAgeField.getText().toString());

        return upperAge >= lowerAge;
    }

    public boolean isValidTimeDifference() {
        // Get formatted dates.
        String startTimeFormatted = mStartTimeField.getText().toString();
        String endTimeFormatted = mEndTimeField.getText().toString();

        // Parse the dates and times.
        Date startTime, endTime;
        try {
            startTime = mTimeFormat.parse(startTimeFormatted);
            endTime = mTimeFormat.parse(endTimeFormatted);
        } catch (ParseException pe) {
            return false;
        }

        // Create the offset value to convert from UTC to local.
        TimeZone tz = mDateFormat.getTimeZone();
        int offset = tz.getRawOffset();

        // Create calendars for comparison.
        Date start = new Date();
        start.setTime(startTime.getTime() + offset);
        Date end = new Date();
        end.setTime(endTime.getTime() + offset);

        // Return result of comparison.
        return end.getTime() > start.getTime();
    }

    public void submit(View v) {
        if (emptyFieldExists()) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
        } else if (!isValidAgeRange()) {
            Toast.makeText(this, "Age range is invalid. Upper age must be greater than or equal to lower age.",
                    Toast.LENGTH_SHORT).show();

            // Clear the invalid fields.
            mUpperAgeField.setText("");
            mLowerAgeField.setText("");
        } else if (!isValidTimeDifference()) {
            Toast.makeText(this, "End time must be after start time.", Toast.LENGTH_SHORT).show();

            // Clear the invalid fields.
            mStartTimeField.setText("");
            mEndTimeField.setText("");
        } else {
            // Create and send to firestore.
            sendToFirestore();
        }
    }

    private void sendToFirestore() {
        // Get basic string data from fields.
        String title = mTitleField.getText().toString();
        String iconUrl = mIconUrlField.getText().toString();
        String location = mLocationField.getSelectedItem().toString();
        String dayFormatted = mEventDateField.getText().toString();
        String startTimeFormatted = mStartTimeField.getText().toString();
        String endTimeFormatted = mEndTimeField.getText().toString();
        Integer lowerAge = Integer.parseInt(mLowerAgeField.getText().toString());
        Integer upperAge = Integer.parseInt(mUpperAgeField.getText().toString());
        String description = mDescriptionField.getText().toString();

        // Parse dates into Date objects.
        Date day, startTime, endTime;
        try {
            day = mDateFormat.parse(dayFormatted);
            startTime = mTimeFormat.parse(startTimeFormatted);
            endTime = mTimeFormat.parse(endTimeFormatted);
        } catch (ParseException pe) {
            Log.d(TAG, "Field parse failed.");
            return;
        }

        // Create the offset value to convert from UTC to local.
        TimeZone tz = mDateFormat.getTimeZone();
        int offset = tz.getRawOffset();

        // Finally create date objects for use with timestamps.
        Date startDate = new Date();
        startDate.setTime(day.getTime() + startTime.getTime() + offset);
        Date endDate = new Date();
        endDate.setTime(day.getTime() + endTime.getTime() + offset);

        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(startDate.getTime());

        // Create event map to send to Firestore.
        Map<String, Object> event = new HashMap<>();
        event.put("title", title);
        event.put("icon_url", iconUrl);
        event.put("location", location);
        event.put("start_time", new Timestamp(startDate));
        event.put("end_time", new Timestamp(endDate));
        event.put("lower_age", lowerAge);
        event.put("upper_age", upperAge);
        event.put("description", description);

        // Form Firestore set command.
        String eventType = "non-recurring";
        String year = Integer.toString(c.get(Calendar.YEAR));
        String month = Integer.toString(c.get(Calendar.MONTH) + 1);

        // Add to Firestore.
        mFirestore.collection("events").document(eventType)
                .collection("years").document(year)
                .collection("months").document(month)
                .collection("events").add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Event created successfully.",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to create event.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Subclass for creating the DatePicker dialog.
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        public static String viewId = "editTextId";

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date values in the picker.
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Get the arguments passed to the date picker.
            Bundle b = this.getArguments();
            if (b == null) {
                return;
            }

            // Get the id of the EditText to update.
            Integer id = b.getInt(viewId);

            // Get the parent activity and validate.
            Activity parentActivity = getActivity();
            if (parentActivity == null) {
                return;
            }
            EditText et = getActivity().findViewById(id);

            // Get the time in the correct format.
            Calendar c = new GregorianCalendar(year, month, dayOfMonth,
                    0, 0);

            // Update the EditText to reflect the date chosen.
            et.setText(mDateFormat.format(c.getTime()));
        }
    }

    // Subclass for creating the TimePicker dialog.
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        public static String viewId = "editTextId";

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time values in the picker.
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);

            return new TimePickerDialog(getActivity(), this, hour, 0,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Get the arguments passed to the time picker.
            Bundle b = this.getArguments();
            if (b == null) {
                return;
            }

            // Get the id of the EditText to update.
            Integer id = b.getInt(viewId);

            // Get the parent activity and validate.
            Activity parentActivity = getActivity();
            if (parentActivity == null) {
                return;
            }
            EditText et = getActivity().findViewById(id);

            // Get the time in the correct format.
            Calendar c = new GregorianCalendar(0, 0, 0,
                    hourOfDay, minute);

            // Update the EditText to reflect the time chosen.
            et.setText(mTimeFormat.format(c.getTime()));
        }
    }
}

