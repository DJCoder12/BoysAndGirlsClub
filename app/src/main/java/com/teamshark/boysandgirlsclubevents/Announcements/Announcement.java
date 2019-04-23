package com.teamshark.boysandgirlsclubevents.Announcements;

import com.google.firebase.Timestamp;

//Represents an announcement
public class Announcement
{
    private String title;
    private String body;
    private String date;
    private String image;

    public Announcement() {}

    public Announcement(String title, String body, String image, String date)
    {
        this.title = title;
        this.body = body;
        this.date = date;
        this.image = image;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String newImage)
    {
        this.image = newImage;
    }
}
