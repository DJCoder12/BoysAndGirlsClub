package com.teamshark.boysandgirlsclubevents.Calendar;

import android.support.annotation.NonNull;

import com.teamshark.boysandgirlsclubevents.NavigationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import android.util.Log;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.WeekFields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    private static LocalDate mLocalDate = LocalDate.now();

    public static LocalDate getLocalDate()
    {
        return mLocalDate;
    }

    public static void setLocalDate(LocalDate ld)
    {
        mLocalDate = ld;
    }

    // (k, v) = (years, months) -> (k, v) = (months, days) -> (k, v) = (days, events)
    private static HashMap<Integer, HashMap<Integer, HashMap<Integer, List<Event>>>> mYears = new HashMap<>();

    // List containing recurring events.
    // Initialized after first query.
    private static ArrayList<LinkedList<Event>> mRecurringEvents;

    // Date formats.
    public static java.text.DateFormat mDateFormat =
            java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);
    public static java.text.DateFormat mTimeFormat =
            java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT);

    private static FirestoreCalendar mFirestoreCalendar = FirestoreCalendar.getInstance();

    public ClubCalendar() {}

    public static List<Event> getEventsForDay(LocalDate date)
    {
        if (date == null)
        {
            return null;
        }

        // Instantiate a new list to hold all events.
        List<Event> allEvents = new ArrayList<>();

        // Add non-recurring events.
        HashMap<Integer, HashMap<Integer, List<Event>>> curYear = mYears.get(date.getYear());
        HashMap<Integer, List<Event>> curMonth = null;
        if (curYear != null)
        {
            curMonth = curYear.get(date.getMonthValue());
        }
        List<Event> temp = null;
        if (curMonth != null)
        {
            temp = curMonth.get(date.getDayOfMonth());
        }
        if (temp != null)
        {
            allEvents.addAll(temp);
        }

        // Add recurring events.
        if (mRecurringEvents != null)
        {
            int dayOfWeek = convertDayOfWeek(date.getDayOfWeek());
            temp = mRecurringEvents.get(dayOfWeek);
            for (Event event : temp)
            {
                // Calculate new dates. (Only needed for daily view because event details can be seen here.)
                int offset = mDateFormat.getTimeZone().getRawOffset();
                long epochMillis = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
                Date newStartDate = new Date(epochMillis + event.getStartTime().getTime() + offset);
                Date newEndDate = new Date(epochMillis + event.getEndTime().getTime() + offset);

                Event copiedEvent = new Event(event.getId(), event.getTitle(), event.getIconUrl(),
                        event.getClubLocationString(), new Timestamp(newStartDate),
                        new Timestamp(newEndDate), event.getLowerAge(),
                        event.getUpperAge(), event.getDescription(), event.getRecurringDays());
                allEvents.add(copiedEvent);
            }
        }

        Collections.sort(allEvents);

        return allEvents;
    }

    public static HashMap<Integer, List<Event>> getEventsForMonth(YearMonth ym)
    {
        if (ym == null)
        {
            return null;
        }

        // Instantiate new hashmap to hold all events.
        HashMap<Integer, List<Event>> allEvents = new HashMap<>();

        // Get non-recurring events.
        HashMap<Integer, HashMap<Integer, List<Event>>> curYear = mYears.get(ym.getYear());
        HashMap<Integer, List<Event>> curMonth = null;
        if (curYear != null)
        {
            curMonth = curYear.get(ym.getMonthValue());
        }
        if (curMonth != null)
        {
            for (Integer key : curMonth.keySet())
            {
                allEvents.put(key, curMonth.get(key));
            }
        }

        // Add recurring events.
        if (mRecurringEvents != null)
        {
            for (int i = 1; i < ym.lengthOfMonth() + 1; i++)
            {
                if (!allEvents.containsKey(i))
                {
                    allEvents.put(i, new LinkedList<Event>());
                }
            }

            for (Integer key : allEvents.keySet())
            {
                int dayOfWeek = convertDayOfWeek(LocalDate.of(ym.getYear(), ym.getMonth(), key).getDayOfWeek());
                List<Event> recurringEventsOnDay = mRecurringEvents.get(dayOfWeek);
                List<Event> allEventsOnDay = allEvents.get(key);
                allEventsOnDay.addAll(recurringEventsOnDay);
            }
        }

        return allEvents;
    }

    public static HashMap<Integer, List<Event>> handleLocationFiltering(HashMap<Integer, List<Event>> month)
    {
        HashMap<Integer, List<Event>> filteredEvents = new HashMap<>();

        if (month == null)
        {
            return null;
        }

        for (Integer key : month.keySet())
        {
            filteredEvents.put(key, new LinkedList<Event>());
            List<Event> events = filteredEvents.get(key);
            for (Event event : month.get(key))
            {
                if (event.getClubLocation() == CalendarSettings.getLocation())
                {
                    events.add(event);
                }
            }
        }

        return filteredEvents;
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

    public int getMonthValue()
    {
        return mLocalDate.getMonthValue();
    }

    public void nextYear()
    {
        mLocalDate = mLocalDate.plusYears(1);
    }

    public void prevYear()
    {
        mLocalDate = mLocalDate.minusYears(1);
    }

    public void prevMonth()
    {
        mLocalDate = mLocalDate.minusMonths(1);
    }

    public void nextMonth()
    {
        mLocalDate = mLocalDate.plusMonths(1);
    }

    public int getYear() {
        return mLocalDate.getYear();
    }

    public static void refreshData(NavigationActivity mainActivity) {

        // Refresh non-recurring events.
        int year = mLocalDate.getYear();
        mYears.clear();
        for (int i = 1; i <= 12; i++)
        {
            YearMonth ym = YearMonth.of(year, i);
            mFirestoreCalendar.queryEventsForYearMonth(ym, new QueryYMOnCompleteListener(ym, null));
        }

        // Refresh recurring events.
        if (mRecurringEvents != null)
        {
            for (int i = 0; i < mRecurringEvents.size(); i++)
            {
                mRecurringEvents.get(i).clear();
            }
        }
        mFirestoreCalendar.queryRecurringEvents(new QueryRecurringOnCompleteListener(mainActivity));
    }

    public static void deleteEvent(Event event) {
        Date date = event.getStartTime();
        LocalDate ld = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        if (event.isRecurring())
        {
            mFirestoreCalendar.deleteRecurringEvent(event.getId());
        }
        else
        {
            mFirestoreCalendar.deleteNonRecurringEvent(ld.getYear(), ld.getMonthValue(), event.getId());
        }
    }

    private static class QueryRecurringOnCompleteListener implements OnCompleteListener<QuerySnapshot>
    {

        // Activity is used here for callback to update calendar fragment.
        private NavigationActivity mMainActivity;

        QueryRecurringOnCompleteListener(NavigationActivity mainActivity)
        {
            mMainActivity = mainActivity;
        }


        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task)
        {
            if (task.isSuccessful())
            {
                if (mRecurringEvents == null)
                {
                    mRecurringEvents = new ArrayList<>();
                    for (int i = 0; i < 7; i++)
                    {
                        mRecurringEvents.add(new LinkedList<Event>());
                    }
                }

                for (QueryDocumentSnapshot doc : task.getResult())
                {
                    Map<String, Object> fields = doc.getData();

                    String id = doc.getId();
                    String title = (String) fields.get("title");
                    String iconUrl = (String) fields.get("icon_url");
                    Integer lowerAge = ((Long) fields.get("lower_age")).intValue();
                    Integer upperAge = ((Long) fields.get("upper_age")).intValue();
                    String location = (String) fields.get("location");
                    Timestamp startTimestamp = (Timestamp) fields.get("start_time");
                    Timestamp endTimestamp = (Timestamp) fields.get("end_time");
                    String description = (String) fields.get("description");
                    ArrayList<Boolean> recurringDays = (ArrayList<Boolean>) fields.get("recurring_days");

                    for (int i = 0; i < recurringDays.size(); i++)
                    {
                        List<Event> day = mRecurringEvents.get(i);

                        // If it recurs on this day according to the array.
                        if (recurringDays.get(i))
                        {
                            day.add(new Event(id, title, iconUrl, location,
                                    startTimestamp, endTimestamp, lowerAge, upperAge, description,
                                    recurringDays));
                        }
                    }
                }

                // Can supply a null activity if callback should not occur.
                if (mMainActivity != null)
                {
                    mMainActivity.showFragment(0, CalendarFragment.TAG);
                }
            }
        }
    }

    private static class QueryYMOnCompleteListener implements OnCompleteListener<QuerySnapshot>
    {

        private YearMonth mYearMonth;

        // Activity is used here for callback to update calendar fragment.
        private NavigationActivity mMainActivity;

        QueryYMOnCompleteListener(YearMonth ym, NavigationActivity mainActivity)
        {
            mYearMonth = ym;
            mMainActivity = mainActivity;
        }

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task)
        {
            int year = mYearMonth.getYear();
            int month = mYearMonth.getMonthValue();

            if (task.isSuccessful())
            {

                if (!mYears.containsKey(year))
                {
                    mYears.put(year, new HashMap<Integer, HashMap<Integer, List<Event>>>());
                }
                HashMap<Integer, HashMap<Integer, List<Event>>> months = mYears.get(year);

                if (!months.containsKey(month))
                {
                    months.put(month, new HashMap<Integer, List<Event>>());
                }
                HashMap<Integer, List<Event>> days = months.get(month);

                for (QueryDocumentSnapshot doc : task.getResult())
                {
                    Map<String, Object> fields = doc.getData();

                    String id = doc.getId();
                    String title = (String) fields.get("title");
                    String iconUrl = (String) fields.get("icon_url");
                    Integer lowerAge = ((Long) fields.get("lower_age")).intValue();
                    Integer upperAge = ((Long) fields.get("upper_age")).intValue();
                    String location = (String) fields.get("location");
                    Timestamp startTimestamp = (Timestamp) fields.get("start_time");
                    Timestamp endTimestamp = (Timestamp) fields.get("end_time");
                    String description = (String) fields.get("description");

                    Integer day = Instant.ofEpochMilli(startTimestamp.toDate().getTime())
                            .atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth();

                    List<Event> eventsOnDay = days.get(day);
                    if (eventsOnDay == null)
                    {
                        eventsOnDay = new LinkedList<>();
                    }

                    boolean containsEvent = false;
                    for (Event event : eventsOnDay)
                    {
                        if (event.getId().equals(id))
                        {
                            containsEvent = true;
                        }
                    }

                    if (!containsEvent)
                    {
                        eventsOnDay.add(new Event(id, title, iconUrl, location,
                                startTimestamp, endTimestamp, lowerAge, upperAge, description));
                    }

                    days.put(day, eventsOnDay);
                }

                if (mMainActivity != null)
                {
                    mMainActivity.showFragment(0, CalendarFragment.TAG);
                }
            }
        }
    }

    public static List<Event> handleLocationFiltering(List<Event> allEvents)
    {
        //Loop though allEvents and only add the events from the current location
        List<Event> filteredEvents = new ArrayList<>();

        if (allEvents == null)
        {
            return filteredEvents;
        }

        for (Event curEvents : allEvents)
        {
            if (curEvents.getClubLocation() == CalendarSettings.getLocation())
            {
                filteredEvents.add(curEvents);
            }
        }

        return filteredEvents;
    }

    // LocalDate has some weird values for the start of the week.
    //
    // This is a utility method that allows us to convert the day of the week to something
    // more familiar i.e. Sunday is the first day of the week and the values are zero indexed.
    public static int convertDayOfWeek(DayOfWeek dayOfWeek)
    {
        switch (dayOfWeek)
        {
            case SUNDAY:
                return 0;
            case MONDAY:
                return 1;
            case TUESDAY:
                return 2;
            case WEDNESDAY:
                return 3;
            case THURSDAY:
                return 4;
            case FRIDAY:
                return 5;
            case SATURDAY:
                return 6;
        }
        return -1;
    }
}
