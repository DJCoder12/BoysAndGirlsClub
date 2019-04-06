package com.example.boysandgirlsclubevents.Calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Event
{
    private final String STR_DATE_FORMAT = "h:mm a";
    private final DateFormat DATE_FORMAT = new SimpleDateFormat(STR_DATE_FORMAT, Locale.US);
    
    private String mTitle;
    private String mAge;
    private int mIcon;
    private int mDuration; //Measured in minutes
    private Date mStartTime;
    private Date mEndTime;

    private ClubLocation mClubLocation;

    public enum ClubLocation
    {
        WaterStreet,
        LemonStreet,
        AnnStreet,
        Columbia
    }

    public Event(String title, String age, ClubLocation clubLocation, Calendar startTime, int duration, int icon)
    {
        mTitle = title;
        mAge = age;
        mClubLocation = clubLocation;
        mDuration = duration;
        mStartTime = startTime.getTime();
        startTime.add(Calendar.MINUTE, duration);
        mEndTime = startTime.getTime();
        mIcon = icon;
    }

    public void setTitle(String newTitle){
        this.mTitle = newTitle;
    }

    public void setAge(String newAge){
        this.mAge = newAge;
    }

    public void setIcon(int newIcon){
        this.mIcon = newIcon;
    }

    public void setLocation(ClubLocation newClubLocation){
        this.mClubLocation = newClubLocation;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public int getIcon()
    {
        return mIcon;
    }

    public String getStartTimeString()
    {
        return DATE_FORMAT.format(mStartTime);
    }

    public String getEndTimeString()
    {
        return DATE_FORMAT.format(mEndTime);
    }

}
