package com.teamshark.boysandgirlsclubevents.Announcements;

import android.os.health.TimerStat;

import com.google.firebase.Timestamp;

import java.sql.Time;

//Represents an announcement
public class Announcement implements Comparable<Announcement>
{
    private String title;
    private String body;
    private Timestamp date;
    private String image;
    private Timestamp createdAt;

    public Announcement() {}

    public Announcement(String title, String body, String image)
    {
        this.title = title;
        this.body = body;
        this.date = Timestamp.now();
        this.image = image;
        this.createdAt = Timestamp.now();
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



    public String getImage()
    {
        return image;
    }

    public void setImage(String newImage)
    {
        this.image = newImage;
    }




    @Override
    public int compareTo(Announcement o) {
        return date.compareTo(o.date);
    }
}
