package com.example.boysandgirlsclubevents;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
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
         DialogFragment df = new TimePickerFragment();

         // Tell the fragment what is the ID of the view to modify.
         Bundle b = new Bundle();
         b.putInt(TimePickerFragment.viewId, v.getId());
         df.setArguments(b);

         df.show(getSupportFragmentManager(), "Time Picker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        public static String viewId = "edittextId";

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date and time values in the picker.
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
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
            java.text.DateFormat f = DateFormat.getTimeFormat(getContext());
            Calendar c = new GregorianCalendar(0, 0, 0,
                    hourOfDay, minute, 0);

            // Update the EditText to reflect the time chosen.
            et.setText(f.format(c));
        }
    }
}

