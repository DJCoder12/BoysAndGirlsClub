package com.example.boysandgirlsclubevents;

import java.io.File;

public class ClubCalendar {

    //now this is a really cool change
    protected void addEvent(String Title, String Date, String Age, File Icon){
        Event newEvent = new Event(Title, Date, Age, Icon);
    }

    protected void editEvent(Event event, String Title, String Date, String Age, File Icon){
        event.setTitle(Title);
        event.setIcon(Icon);
        event.setDate(Date);
        event.setAge(Age);
    }

    protected void deleteEvent(Event event){
        event.setTitle(null);
        event.setIcon(null);
        event.setDate(null);
        event.setAge(null);
    }
}
