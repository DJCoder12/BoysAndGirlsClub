package com.example.boysandgirlsclubevents.Calendar.WeeklyView;


import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;
import com.example.boysandgirlsclubevents.Calendar.Event;

import org.threeten.bp.LocalDate;

import java.util.Calendar;
import java.util.List;

public class CalendarWeeklyLogic
{
    private CalendarWeeklyFragment mView;
    private LocalDate mFirstDate;
    private LocalDate[] mLocalDates = new LocalDate[7];

    public CalendarWeeklyLogic(CalendarWeeklyFragment view, LocalDate firstDate)
    {
        mView = view;
        mFirstDate = firstDate;
    }

    public void handleInitialization()
    {
        if (mFirstDate != null)
        {
            int[] mDates = new int[7];
            LocalDate endDate = mFirstDate.plusDays(7);
            int index = 0;

            for (LocalDate date = mFirstDate; date.isBefore(endDate); date = date.plusDays(1))
            {
                mLocalDates[index] = date;
                mDates[index] = date.getDayOfMonth();
                index++;
            }

            mView.showDates(mDates);
            mView.showEvents();
        }
    }

    public List<Event> getEventList(int index)
    {
        return ClubCalendar.getEventsForDay(mLocalDates[index]);
    }
}
