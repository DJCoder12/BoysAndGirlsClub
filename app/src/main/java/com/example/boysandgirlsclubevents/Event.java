package com.example.boysandgirlsclubevents;

import java.io.File;

class Event {

    String Title;
    String Date;
    String Age;
    File Icon;

    public Event(String title, String date, String age, File icon){
        Title = title;
        Date = date;
        Age = age;
        Icon = icon;
    }

    public Event(String title, String date, String age){
        Title = title;
        Date = date;
        Age = age;
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

}
