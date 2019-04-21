package com.example.boysandgirlsclubevents.Calendar;

import android.support.annotation.NonNull;

import com.example.boysandgirlsclubevents.Calendar.MonthlyView.CalendarMonthlyFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.WeekFields;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

public class ClubCalendar {
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

    private FirestoreCalendar mFirestoreCalendar;

    public ClubCalendar() {
        mFirestoreCalendar = FirestoreCalendar.getInstance();
    }

    public static List<Event> getEventsForDay(LocalDate date)
    {
//        if (date != null)
//        {
//            HashMap<String, HashMap<Integer, List<Event>>> curYear = mYears.get(date.getYear());
//
//            if (curYear == null)
//            {
//                return null;
//            }
//
//            HashMap<Integer, List<Event>> curMonth = curYear.get(date.getMonth().getDisplayName(TextStyle.FULL, Locale.US));
//
//            if (curMonth == null)
//            {
//                return null;
//            }
//
//            List<Event> events = curMonth.get(date.getDayOfMonth());
//            return events;
//        }

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
        return lastDayOfMonth.get(WeekFields.ISO.weekOfMonth());
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

    public void updateViewOnData(CalendarMonthlyFragment cmf) {
        YearMonth ym = cmf.getYearMonth();
        Integer year = ym.getYear();
        Integer month = ym.getMonthValue();

        HashMap<Integer, HashMap<Integer, List<Event>>> months = mYears.get(year);
        HashMap<Integer, List<Event>> days = null;
        if (months != null) {
             days = months.get(month);
        }

        if (days == null) {
            mFirestoreCalendar.queryEventsForYearMonth(cmf.getYearMonth(), new MonthlyQueryOnComplete(cmf));
        } else {
            cmf.updateEvents(days);
        }
    }

    private class MonthlyQueryOnComplete implements OnCompleteListener<QuerySnapshot> {

        private CalendarMonthlyFragment mCmf;

        MonthlyQueryOnComplete(CalendarMonthlyFragment cmf) {
            this.mCmf = cmf;
        }

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            int year = mCmf.getYearMonth().getYear();
            int month = mCmf.getYearMonth().getMonthValue();

            if (task.isSuccessful()) {

                if (!mYears.containsKey(year)) {
                    mYears.put(year, new HashMap<Integer, HashMap<Integer, List<Event>>>());
                }
                HashMap<Integer, HashMap<Integer, List<Event>>> months = mYears.get(year);

                if (!months.containsKey(month)) {
                    months.put(month, new HashMap<Integer, List<Event>>());
                }
                HashMap<Integer, List<Event>> days = months.get(month);

                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Map<String, Object> fields = doc.getData();

                    String title = (String) fields.get("title");
                    String description = (String) fields.get("description");
                    Integer lowerAge = ((Long) fields.get("lower_age")).intValue();
                    Integer upperAge = ((Long) fields.get("upper_age")).intValue();
                    Integer location = ((Long) fields.get("location")).intValue();
                    Timestamp startTimestamp = (Timestamp) fields.get("start_time");
                    Timestamp endTimestamp = (Timestamp) fields.get("end_time");

                    Integer day = Instant.ofEpochMilli(startTimestamp.toDate().getTime())
                            .atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth();

                    List<Event> eventsOnDay = days.get(day);
                    if (eventsOnDay == null) {
                        eventsOnDay = new LinkedList<>();
                    }

                    eventsOnDay.add(new Event(title, description, location, startTimestamp,
                            endTimestamp, lowerAge, upperAge));

                    days.put(day, eventsOnDay);
                }

                this.mCmf.updateEvents(days);
            }
        }
    }
}
