package com.example.boysandgirlsclubevents.Announcements;

import com.google.firebase.Timestamp;

//Represents an announcement
public class Announcement
{
    private String title;
    private String body;
    private Timestamp date;
    private String image;

    public Announcement() {}

    public Announcement(String title, String body, String image, Timestamp date)
    {
        this.title = title;
        this.body = body;
        this.date = Timestamp.now();
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

    public Timestamp getDate()
    {
        return date;
    }

    public void setDate(Timestamp date)
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
