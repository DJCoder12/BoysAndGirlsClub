package com.example.boysandgirlsclubevents.Announcements;

import com.google.firebase.Timestamp;

//Represents an announcement
public class Announcement
{
    private String mTitle;
    private String mBody;
    private Timestamp mDate;
    private String mImage;

    public Announcement(String title, String body, String image)
    {
        mTitle = title;
        mBody = body;
        mDate = Timestamp.now();
        mImage = image;
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

    public String getImage(){
        return mImage;
    }

    public void setImage(String newImage){
        this.mImage = newImage;
    }
}
