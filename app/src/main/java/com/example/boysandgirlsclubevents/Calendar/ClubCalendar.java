package com.example.boysandgirlsclubevents.Calendar;

import com.example.boysandgirlsclubevents.R;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ClubCalendar
{
    private final static String TAG = "ClubCalendar";

    public static final String SUN_ABRV = "Sun";
    public static final String MON_ABRV = "Mon";
    public static final String TUE_ABRV = "Tue";
    public static final String WED_ABRV = "Wed";
    public static final String THU_ABRV = "Thu";
    public static final String FRI_ABRV = "Fri";
    public static final String SAT_ABRV = "Sat";

    private static Calendar mCalendar = Calendar.getInstance();
    public static LocalDate mLocalDate = LocalDate.now();
    private static HashMap<String, HashMap<String, HashMap<String, List<Event>>>> mYears = new HashMap<>();

    public ClubCalendar()
    {
        initEvents();
    }

    //TODO: Delete when this is connected to the db
    private void initEvents()
    {
        //Make some events
        List<Event> dummyEvents1 = new ArrayList<>();
        Event event1 = new Event("Basketball", "8-14", Event.ClubLocation.AnnStreet, Calendar.getInstance(), 60, R.drawable.football);
        Event event2 = new Event("Leadership", "5-16", Event.ClubLocation.Columbia, Calendar.getInstance(), 180, R.drawable.torch);
        Event event3 = new Event("Study Help", "8-13", Event.ClubLocation.WaterStreet, Calendar.getInstance(), 440, R.drawable.homework);
        dummyEvents1.add(event1);
        dummyEvents1.add(event2);
        dummyEvents1.add(event3);

        List<Event> dummyEvents2 = new ArrayList<>();
        Event event1a = new Event("Football", "8-14", Event.ClubLocation.AnnStreet, Calendar.getInstance(), 60, R.drawable.football);
        Event event3a = new Event("Tutoring", "8-13", Event.ClubLocation.WaterStreet, Calendar.getInstance(), 440, R.drawable.homework);
        dummyEvents2.add(event1a);
        dummyEvents2.add(event3a);

        //populate some days with dummy events
        HashMap<String, List<Event>> april = new HashMap<>();
        april.put("8", dummyEvents2);
        april.put("10", dummyEvents1);

        HashMap<String, HashMap<String, List<Event>>> y2019 = new HashMap<>();
        y2019.put("April", april);

        mYears.put("2019", y2019);
    }

    //Creates an event object and adds the event to the month ArrayList. Also sets up mMonths if first time being used
    /*protected void addEvent(String Title, String Age, Event.ClubLocation Location, int Icon, String year, String month)
    {
        Event newEvent = new Event(Title, Age, Location, Icon);
        if (mMonths.isEmpty())
        {
            mMonths.put("January", events);
            mMonths.put("February", events1);
            mMonths.put("March", events2);
            mMonths.put("April", events3);
            mMonths.put("May", events4);
            mMonths.put("June", events5);
            mMonths.put("July", events6);
            mMonths.put("August", events7);
            mMonths.put("September", events8);
            mMonths.put("October", events9);
            mMonths.put("November", events10);
            mMonths.put("December", events11);
        }
        if(!mYears.containsKey(year)){
            mYears.put(year,mMonths);

        }

        ArrayList eventsOftheMonth = mMonths.get(month);
        eventsOftheMonth.add(newEvent);
    }*/

    //Gets the event of interest and alters the values for each event field based on what the user passes.
    /*protected void editEvent(Event event, String Title, String Date, String Age, Event.ClubLocation Location, int Icon, String month, String year){
        HashMap yearOfInterest = mYears.get(year);
        ArrayList<Event> monthEvents = mMonths.get(month);
        int indexOfEvent = monthEvents.indexOf(event);
        Event eventToEdit = monthEvents.get(indexOfEvent);
        eventToEdit.setTitle(Title);
        eventToEdit.setIcon(Icon);
        eventToEdit.setAge(Age);
        eventToEdit.setLocation(Location);
    }

    // Removes the event from the event list for the month
    protected void deleteEvent(Event event, String month, String year){
        HashMap yearOfInterest = mYears.get(year);
        ArrayList<Event> monthEvents = mMonths.get(month);
        monthEvents.remove(event);

    }*/

    public static List<Event> getEventsForDay(String year, String month, String date)
    {
        HashMap<String, HashMap<String, List<Event>>> curYear = mYears.get(year);
        if (curYear == null)
        {
            return null;
        }

        HashMap<String, List<Event>> curMonth = curYear.get(month);
        if (curMonth == null)
        {
            return null;
        }

        List<Event> events = curMonth.get(date);
        return events;
    }

    public static int getCurrentYear()
    {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Calendar getBaseCalendar()
    {
        return mCalendar;
    }

    public int getYear()
    {
        return mCalendar.get(Calendar.YEAR);
    }

    public static int getCurrentYearCode()
    {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public int getYearCode()
    {
        return mCalendar.get(Calendar.YEAR);
    }


    public static int getCurrentMonthCode()
    {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public int getMonthCode()
    {
        return mCalendar.get(Calendar.MONTH);
    }

    public static int getCurrentDate()
    {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public int getDate()
    {
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getWeek()
    {
        return mCalendar.get(Calendar.WEEK_OF_MONTH);
    }

    public int getFirstDateInWeek(int weekOfYear)
    {
        mCalendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getDate();
    }

    public static int getCurrentDaysInMonth()
    {
        return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int getDaysInMonth()
    {
        return mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int getWeeksInMonth()
    {
        return mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    public int getWeekOfYear()
    {
        return mCalendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static String getCurrentMonth()
    {
        return Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
    }

    public String getMonth()
    {
        return mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
    }

    public static String getCurrentDayOfWeek()
    {
        return Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
    }

    public static String getDayOfWeek(int year, int month, int date)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
    }

    public String getDayOfWeek()
    {
        return mCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
    }

    public void prevMonth()
    {
        mCalendar.add(Calendar.MONTH, -1);
    }

    public void nextMonth()
    {
        mCalendar.add(Calendar.MONTH, 1);
    }

    public void setDate(int date)
    {
        mCalendar.set(Calendar.DATE, date);
    }

    public void setWeekOfMonth(int week)
    {
        mCalendar.set(Calendar.WEEK_OF_MONTH, week);
    }

    public void setToFirstDayOfWeek()
    {
        mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    }
}
