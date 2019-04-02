package com.example.boysandgirlsclubevents;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);
    }

    public void onClickTest(View v) {
         DialogFragment df = new TimePickerFragment();
         df.show(getSupportFragmentManager(), "Time Picker");
    }
}
