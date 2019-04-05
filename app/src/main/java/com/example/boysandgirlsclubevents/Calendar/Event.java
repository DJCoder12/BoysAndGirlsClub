package com.example.boysandgirlsclubevents.Calendar;

import java.io.File;

public class Event {

    private String mTitle;
    private String mDate;
    private String mAge;
    private File mIcon;
    private ClubLocation mClubLocation;

    public enum ClubLocation
    {
        WaterStreet,
        LemonStreet,
        AnnStreet,
        Columbia
    }

    public Event(String title, String date, String age, ClubLocation clubLocation, File icon)
    {
        mTitle = title;
        mDate = date;
        mAge = age;
        mClubLocation = clubLocation;
        mIcon = icon;
    }

    public Event(String title, String date, String age, ClubLocation clubLocation)
    {
        mTitle = title;
        mDate = date;
        mAge = age;
        mClubLocation = clubLocation;
    }

    public void setTitle(String newTitle){
        this.mTitle = newTitle;
    }

    public void setDate(String newDate){
        this.mDate = newDate;
    }

    public void setAge(String newAge){
        this.mAge = newAge;
    }

    public void setIcon(File newIcon){
        this.mIcon = newIcon;
    }

    public void setLocation(ClubLocation newClubLocation){
        this.mClubLocation = newClubLocation;
    }

    public String getTitle()
    {
        return mTitle;
    }

}
