package com.example.boysandgirlsclubevents.Calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Event
{
    private final String STR_DATE_FORMAT = "h:mm a";
    private final DateFormat DATE_FORMAT = new SimpleDateFormat(STR_DATE_FORMAT, Locale.US);

    private String mTitle;
    private int mLowerAge;
    private int mUpperAge;
    private int mIcon;
    private int mDuration; //Measured in minutes
    private Date mStartTime;
    private Date mEndTime;

    private ClubLocation mClubLocation;
    private Color mColor;

    public enum ClubLocation
    {
        Columbia(0),
        Hill(1),
        JackWalker(2),
        Southeast(3);

        private int locNum;

        ClubLocation(int locNum) {
            this.locNum = locNum;
        }

        public int getLocNum() {
            return this.locNum;
        }
    }

    public enum Color
    {
        Blue,
        Red,
        Green,
        Yellow,
        Orange,
        Purple
    }

    public Event(String title, ClubLocation clubLocation, Calendar startTime, int duration, int icon)
    {
        mTitle = title;
        mClubLocation = clubLocation;
        mDuration = duration;
        mStartTime = startTime.getTime();
        startTime.add(Calendar.MINUTE, duration);
        mEndTime = startTime.getTime();
        mIcon = icon;
        Color[] colors = Color.values();
        mColor = colors[new Random().nextInt(colors.length)];
    }

    public Event setTitle(String newTitle)
    {
        this.mTitle = newTitle;
        return this;
    }

    public Event setLowerAge(int newAge){
        this.mLowerAge = newAge;
        return this;
    }

    public Event setUpperAge(int newAge){
        this.mUpperAge = newAge;
        return this;
    }

    public Event setIcon(int newIcon){
        this.mIcon = newIcon;
        return this;
    }

    public Event setLocation(ClubLocation newClubLocation)
    {
        this.mClubLocation = newClubLocation;
        return this;
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

    public int getLowerAge()
    {
        return mLowerAge;
    }

    public int getUpperAge()
    {
        return mUpperAge;
    }

    public Color getColor()
    {
        return mColor;
    }
}
