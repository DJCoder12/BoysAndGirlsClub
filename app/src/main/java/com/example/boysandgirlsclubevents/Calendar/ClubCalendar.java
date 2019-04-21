package com.example.boysandgirlsclubevents.Calendar;

import android.util.Log;

import com.example.boysandgirlsclubevents.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.WeekFields;

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

    public static LocalDate mLocalDate = LocalDate.now();
    private static HashMap<Integer, HashMap<String, HashMap<Integer, List<Event>>>> mYears = new HashMap<>();

    public ClubCalendar()
    {
        initEvents();
    }

    //TODO: Delete when this is connected to the db
    private void initEvents()
    {
        //Make some events
        List<Event> dummyEvents1 = new ArrayList<>();
        Event event1 = new Event("Basketball", Event.ClubLocation.AnnStreet, Calendar.getInstance(), 60, R.drawable.football)
                .setLowerAge(5).setUpperAge(18);
        Event event2 = new Event("Leadership", Event.ClubLocation.Columbia, Calendar.getInstance(), 180, R.drawable.torch)
                .setLowerAge(13).setUpperAge(18);
        Event event3 = new Event("Study Help", Event.ClubLocation.WaterStreet, Calendar.getInstance(), 440, R.drawable.homework)
                .setLowerAge(8).setUpperAge(14);
        dummyEvents1.add(event1);
        dummyEvents1.add(event2);
        dummyEvents1.add(event3);

        List<Event> dummyEvents2 = new ArrayList<>();
        Event event1a = new Event("Football", Event.ClubLocation.AnnStreet, Calendar.getInstance(), 60, R.drawable.football)
                .setLowerAge(13).setUpperAge(16);
        Event event3a = new Event("Tutoring", Event.ClubLocation.WaterStreet, Calendar.getInstance(), 440, R.drawable.homework)
                .setLowerAge(10).setUpperAge(14);
        dummyEvents2.add(event1a);
        dummyEvents2.add(event3a);

        //populate some days with dummy events
        HashMap<Integer, List<Event>> april = new HashMap<>();
        april.put(24, dummyEvents2);
        april.put(21, dummyEvents1);

        HashMap<String, HashMap<Integer, List<Event>>> y2019 = new HashMap<>();
        y2019.put("April", april);

        mYears.put(2019, y2019);
    }

    /*//Creates an event object and adds the event to the month ArrayList. Also sets up mMonths if first time being used
    protected void addEvent(Event newEvent, LocalDate date)
    {
        int year = date.getYear();
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
        int dateOfMonth = date.getDayOfMonth();

        if(!mYears.containsKey(year))
        {
            HashMap<Integer, List<Event>> monthMap = new HashMap<>();
            List<Event> events = new ArrayList<>();
            events.add(newEvent);
            monthMap.put(dateOfMonth, events);
            HashMap<String, HashMap<Integer, List<Event>>> yearMap = new HashMap<>();
            yearMap.put(month, monthMap);
            mYears.put(year, yearMap);
        }
        else
        {
            HashMap<String, HashMap<Integer, List<Event>>> yearMap = mYears.get(year);
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

    public static List<Event> getEventsForDay(LocalDate date)
    {
        if (date != null)
        {
            HashMap<String, HashMap<Integer, List<Event>>> curYear = mYears.get(date.getYear());

            if (curYear == null)
            {
                return null;
            }

            HashMap<Integer, List<Event>> curMonth = curYear.get(date.getMonth().getDisplayName(TextStyle.FULL, Locale.US));

            if (curMonth == null)
            {
                return null;
            }

            List<Event> events = curMonth.get(date.getDayOfMonth());
            return events;
        }

        return null;
    }

    public int getDate()
    {
        return mLocalDate.getDayOfMonth();
    }

    public int getDaysInMonth()
    {
        return mLocalDate.lengthOfMonth();
    }

    public int getWeeksInMonth()
    {
        LocalDate lastDayOfMonth = mLocalDate.withDayOfMonth(mLocalDate.lengthOfMonth());
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = lastDayOfMonth.get(weekFields.weekOfMonth());
        Log.d(TAG, "getWeeksInMonth: " + weekNumber);
        return weekNumber;
    }

    public int getWeekOfMonth()
    {
        return mLocalDate.get(WeekFields.ISO.weekOfMonth());
    }

    public String getMonth()
    {
        return mLocalDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
    }

    public void prevMonth()
    {
        mLocalDate = mLocalDate.minusMonths(1);
    }

    public void nextMonth()
    {
        mLocalDate = mLocalDate.plusMonths(1);
    }
}
