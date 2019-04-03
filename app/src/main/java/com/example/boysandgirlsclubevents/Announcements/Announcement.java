package com.example.boysandgirlsclubevents.Announcements;

import com.google.firebase.Timestamp;

//Represents an announcement
public class Announcement
{
    private String mTitle;
    private String mBody;
    private Timestamp mDate;

    public Announcement(String title, String body)
    {
        mTitle = title;
        mBody = body;
        mDate = Timestamp.now();
    }

    public String getBody()
    {
        return mBody;
    }

    public void setBody(String body)
    {
        this.mBody = mBody;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String title)
    {
        this.mTitle = mTitle;
    }

    public Timestamp getDate()
    {
        return mDate;
    }

    public void setDate(Timestamp date)
    {
        this.mDate = mDate;
    }
}
