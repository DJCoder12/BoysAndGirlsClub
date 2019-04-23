package com.teamshark.boysandgirlsclubevents.Calendar;

import com.teamshark.boysandgirlsclubevents.R;
import com.google.firebase.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Event
{
    private final String STR_DATE_FORMAT = "h:mm a";
    private final DateFormat DATE_FORMAT = new SimpleDateFormat(STR_DATE_FORMAT, Locale.US);

    private String mId;
    private String mTitle;
    private int mLowerAge;
    private int mUpperAge;
    private String mIconUrl;
    private Date mStartTime;
    private Date mEndTime;
    private String mDescription;
    private ArrayList<Boolean> mRecurringDays;

    private ClubLocation mClubLocation;
    private Color mColor;

    public enum ClubLocation
    {
        Hill,
        JackWalker,
        Southeast,
        Columbia
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

    public Event(String id, String title, String iconUrl, String location,
                 Timestamp startTimestamp, Timestamp endTimestamp, Integer lowerAge,
                 Integer upperAge, String description)
    {
        mId = id;
        mTitle = title;
        mLowerAge = lowerAge;
        mUpperAge = upperAge;

        switch (location)
        {
            case ("Columbia"): mClubLocation = Event.ClubLocation.Columbia;
                break;
            case ("Hill"): mClubLocation = Event.ClubLocation.Hill;
                break;
            case ("Jack Walker"): mClubLocation = Event.ClubLocation.JackWalker;
                break;
            case ("Southeast"): mClubLocation = Event.ClubLocation.Southeast;
                break;
        }

        mStartTime = new Date(startTimestamp.toDate().getTime());
        mEndTime = new Date(endTimestamp.toDate().getTime());
        mDescription = description;
        initializeColor();
        mIconUrl = iconUrl;

        mRecurringDays = null;
    }

    public Event(String id, String title, String iconUrl, String location,
                 Timestamp startTimestamp, Timestamp endTimestamp, Integer lowerAge,
                 Integer upperAge, String description, ArrayList<Boolean> recurringDays)
    {
        this(id, title, iconUrl, location, startTimestamp, endTimestamp, lowerAge, upperAge,
                description);

        this.mRecurringDays = recurringDays;
    }

    private void initializeColor()
    {
        if (4 <= mLowerAge && mLowerAge < 6)
        {
            mColor = Color.Purple;
        }
        else if(6 <= mLowerAge && mLowerAge < 9)
        {
            mColor = Color.Yellow;
        }
        else if (9 <= mLowerAge && mLowerAge < 11)
        {
            mColor = Color.Blue;
        }
        else if (11 <= mLowerAge && mLowerAge < 13)
        {
            mColor = Color.Red;
        }
        else if (13 <= mLowerAge && mLowerAge < 16)
        {
            mColor = Color.Green;
        }
        else
        {
            mColor = Color.Orange;
        }
    }

    public Event setId(String id)
    {
        this.mId = id;
        return this;
    }

    public Event setTitle(String newTitle)
    {
        this.mTitle = newTitle;
        return this;
    }

    public Event setLowerAge(int newAge)
    {
        this.mLowerAge = newAge;
        return this;
    }

    public Event setUpperAge(int newAge)
    {
        this.mUpperAge = newAge;
        return this;
    }

    public Event setIcon(String newIconUrl)
    {
        this.mIconUrl = newIconUrl;
        return this;
    }

    public Event setLocation(ClubLocation newClubLocation)
    {
        this.mClubLocation = newClubLocation;
        return this;
    }

    public String getId()
    {
        return mId;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public String getIconUrl()
    {
        return mIconUrl;
    }

    public Date getStartTime()
    {
        return mStartTime;
    }

    public Date getEndTime()
    {
        return mEndTime;
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

    public ClubLocation getClubLocation()
    {
        return mClubLocation;
    }

    public boolean isRecurring()
    {
        return mRecurringDays != null;
    }
}
