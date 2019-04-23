package com.teamshark.boysandgirlsclubevents.Calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamshark.boysandgirlsclubevents.R;

public class EventDetailActivity extends AppCompatActivity
{
    private EventDetailLogic mLogic;
    private ImageView mImage;
    private TextView mTitleText;
    private TextView mDateText;
    private TextView mTimeText;
    private TextView mDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        initializeViews();
        mLogic = new EventDetailLogic(this, CalendarSettings.getSelectedEvent());
        mLogic.handleLoadingData();
    }

    private void initializeViews()
    {
        mImage = findViewById(R.id.iv_icon_eventDetail);
        mTitleText = findViewById(R.id.tv_title_eventDetail);
        mDateText = findViewById(R.id.tv_date_eventDetail);
        mTimeText = findViewById(R.id.tv_time_eventDetail);
        mDescriptionText = findViewById(R.id.tv_description_eventDetail);
    }

    public void showTitle(String title)
    {
        mTitleText.setText(title);
    }

    public void showImage(String imageURL)
    {
        Glide.with(this)
                .load(imageURL)
                .into(mImage);
    }

    public void showDate(String date)
    {
        mDateText.setText(date);
    }

    public void showTime(String time)
    {
        mTimeText.setText(time);
    }

    public void showDescription(String description)
    {
        mDescriptionText.setText(description);
    }
}
