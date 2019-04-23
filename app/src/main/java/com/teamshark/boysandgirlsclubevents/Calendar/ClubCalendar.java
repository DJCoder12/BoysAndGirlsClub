package com.teamshark.boysandgirlsclubevents.Calendar;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.teamshark.boysandgirlsclubevents.Calendar.DailyView.CalendarDailyFragment;
import com.teamshark.boysandgirlsclubevents.Calendar.MonthlyView.CalendarMonthlyFragment;
import com.teamshark.boysandgirlsclubevents.NavigationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.threeten.bp.Instant;
import android.util.Log;

import com.teamshark.boysandgirlsclubevents.R;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.WeekFields;

import java.util.ArrayList;
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

    private static FirestoreCalendar mFirestoreCalendar = FirestoreCalendar.getInstance();

    public ClubCalendar() {}

    public static List<Event> getEventsForDay(LocalDate date)
    {
        if (date != null)
        {
            HashMap<Integer, HashMap<Integer, List<Event>>> curYear = mYears.get(date.getYear());

            if (curYear == null)
            {
                return null;
            }

            HashMap<Integer, List<Event>> curMonth = curYear.get(date.getMonthValue());

            if (curMonth == null)
            {
                return null;
            }

            return curMonth.get(date.getDayOfMonth());
        }
        return null;
    }

    public static HashMap<Integer, List<Event>> getEventsForMonth(YearMonth ym)
    {
        if (ym != null)
        {
            HashMap<Integer, HashMap<Integer, List<Event>>> curYear = mYears.get(ym.getYear());

            if (curYear == null)
            {
                return null;
            }

            HashMap<Integer, List<Event>> curMonth = curYear.get(ym.getMonthValue());

            if (curMonth == null)
            {
                return null;
            }

            return curMonth;
        }

        return null;
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
        int year = mLocalDate.getYear();
        mYears.clear();
        for (int i = 1; i <= 12; i++) {
            YearMonth ym = YearMonth.of(year, i);
            mFirestoreCalendar.queryEventsForYearMonth(ym, new QueryYMOnCompleteListener(ym, mainActivity));
        }
    }

    public static void deleteEvent(Event event) {
        Date date = event.getStartTime();
        LocalDate ld = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        mFirestoreCalendar.deleteEvent(ld.getYear(), ld.getMonthValue(), event.getId());
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
                    doc.getId();
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

                mMainActivity.showFragment(0, CalendarFragment.TAG);
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
}
