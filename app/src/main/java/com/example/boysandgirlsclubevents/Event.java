package com.example.boysandgirlsclubevents;

import java.io.File;

class Event {

    String Title;
    String Date;
    String Age;
    File Icon;
    String Location;

    public Event(String title, String date, String age, String location, File icon){
        Title = title;
        Date = date;
        Age = age;
        Location = location;
        Icon = icon;
    }

    public Event(String title, String date, String age, String location){
        Title = title;
        Date = date;
        Age = age;
        Location = location;

    }


    protected void setTitle(String newTitle){
        this.Title = newTitle;
    }

    protected void setDate(String newDate){
        this.Date = newDate;
    }

    protected void setAge(String newAge){
        this.Age = newAge;
    }

    protected void setIcon(File newIcon){
        this.Icon = newIcon;
    }

    protected void setLocation(String newLocation){
        this.Location = newLocation;
    }

}
