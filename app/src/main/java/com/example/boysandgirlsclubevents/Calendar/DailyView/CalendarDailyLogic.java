package com.example.boysandgirlsclubevents.Calendar.DailyView;

import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;
import com.example.boysandgirlsclubevents.Calendar.Event;

import java.util.ArrayList;
import java.util.List;

public class CalendarDailyLogic
{
    private String mYear;
    private String mMonth;
    private String mDay;
    private int mDate;

    private CalendarDailyFragment mDailyView;
    private List<Event> mEvents = new ArrayList<>();

    public CalendarDailyLogic(CalendarDailyFragment dailyView, String year, String month, String day, int date)
    {
        mDailyView = dailyView;
        mYear = year;
        mMonth = month;
        mDay = day;
        mDate = date;
    }

    public void handleLoadingEvents()
    {
        mEvents = ClubCalendar.getEventsForDay(mYear, mMonth, String.valueOf(mDate));

        //Set events to an empty list to avoid errors
        if (mEvents == null)
        {
            mEvents = new ArrayList<>();
        }

        mDailyView.showEvents(mEvents);
    }

    public void handleDate()
    {
        mDailyView.showDate(mDate, mDay);
    }
}
