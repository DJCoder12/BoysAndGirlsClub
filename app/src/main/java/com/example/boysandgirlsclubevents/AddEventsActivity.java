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
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddEventsActivity extends AppCompatActivity {

    public static final String TAG = "AddEventsActivity";

    // Activity UI elements.
    private EditText mTitleField;
    private EditText mLocationField;
    private EditText mDescriptionField;
    private EditText mEndDateField;
    private EditText mStartDateField;
    private EditText mStartTimeField;
    private EditText mEndTimeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        // Keep references to views contained in activity.
        mTitleField = findViewById(R.id.editText_title);
        mLocationField = findViewById(R.id.editText_location);
        mDescriptionField = findViewById(R.id.editText_description);
        mStartDateField = findViewById(R.id.editText_startDate);
        mStartTimeField = findViewById(R.id.editText_startTime);
        mEndDateField = findViewById(R.id.editText_endDate);
        mEndTimeField = findViewById(R.id.editText_endTime);
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
            java.text.DateFormat f = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);
            Calendar c = new GregorianCalendar(year, month, dayOfMonth,
                    0, 0);

            // Update the EditText to reflect the date chosen.
            et.setText(f.format(c.getTime()));
        }
    }

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
            java.text.DateFormat f = java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT);
            Calendar c = new GregorianCalendar(0, 0, 0,
                    hourOfDay, minute);

            // Update the EditText to reflect the time chosen.
            et.setText(f.format(c.getTime()));
        }
    }
}

