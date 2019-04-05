package com.example.boysandgirlsclubevents.Calendar;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ClubCalendar
{
    private final static String TAG = "ClubCalendar";

    public ClubCalendar()
    {

    }

    HashMap<String, HashMap > years = new HashMap<String, HashMap>();
    HashMap<String, ArrayList> monthsOfYear = new HashMap<String, ArrayList>();
    ArrayList<Event> events = new ArrayList<Event>();
    ArrayList<Event> events1 = new ArrayList<Event>();
    ArrayList<Event> events2 = new ArrayList<Event>();
    ArrayList<Event> events3= new ArrayList<Event>();
    ArrayList<Event> events4 = new ArrayList<Event>();
    ArrayList<Event> events5= new ArrayList<Event>();
    ArrayList<Event> events6 = new ArrayList<Event>();
    ArrayList<Event> events7 = new ArrayList<Event>();
    ArrayList<Event> events8 = new ArrayList<Event>();
    ArrayList<Event> events9 = new ArrayList<Event>();
    ArrayList<Event> events10 = new ArrayList<Event>();
    ArrayList<Event> events11 = new ArrayList<Event>();



    //Creates an event object and adds the event to the month ArrayList. Also sets up monthsOfYear if first time being used
    protected void addEvent(String Title, String Date, String Age, String Location, File Icon, String year, String month){
        Event newEvent = new Event(Title, Date, Age, Location, Icon);
        if (monthsOfYear.isEmpty()){
            monthsOfYear.put("January", events);
            monthsOfYear.put("February", events1);
            monthsOfYear.put("March", events2);
            monthsOfYear.put("April", events3);
            monthsOfYear.put("May", events4);
            monthsOfYear.put("June", events5);
            monthsOfYear.put("July", events6);
            monthsOfYear.put("August", events7);
            monthsOfYear.put("September", events8);
            monthsOfYear.put("October", events9);
            monthsOfYear.put("November", events10);
            monthsOfYear.put("December", events11);
        }
        if(!years.containsKey(year)){
            years.put(year,monthsOfYear);

        }

        ArrayList eventsOftheMonth = monthsOfYear.get(month);
        eventsOftheMonth.add(newEvent);


    }
    //Gets the event of interest and alters the values for each event field based on what the user passes.
    protected void editEvent(Event event, String Title, String Date, String Age, String Location, File Icon, String month, String year){
        HashMap yearOfInterest = years.get(year);
        ArrayList<Event> monthEvents = monthsOfYear.get(month);
        int indexOfEvent = monthEvents.indexOf(event);
        Event eventToEdit = monthEvents.get(indexOfEvent);
        eventToEdit.setTitle(Title);
        eventToEdit.setIcon(Icon);
        eventToEdit.setDate(Date);
        eventToEdit.setAge(Age);
        eventToEdit.setLocation(Location);
    }

    // Removes the event from the event list for the month
    protected void deleteEvent(Event event, String month, String year){
        HashMap yearOfInterest = years.get(year);
        ArrayList<Event> monthEvents = monthsOfYear.get(month);
        monthEvents.remove(event);

    }

    public int getCurrentYearCode()
    {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public int getCurrentMonthCode()
    {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public int getCurrentDate()
    {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public int getCurrentDaysInMonth()
    {
        return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public String getCurrentMonth()
    {
        return Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
    }

    public String getCurrentDayOfWeek()
    {
        return Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
    }

    public String getDayOfWeek(int year, int month, int date)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
    }

}
