package com.example.boysandgirlsclubevents.Calendar.WeeklyView;


import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;

import org.threeten.bp.LocalDate;

import java.util.Calendar;

public class CalendarWeeklyLogic
{
    private CalendarWeeklyFragment mView;
    private LocalDate mFirstDate;

    public CalendarWeeklyLogic(CalendarWeeklyFragment view, LocalDate firstDate)
    {
        mView = view;
        mFirstDate = firstDate;
    }

    public void handleInitialization()
    {
        int[] dates = new int[7];
        LocalDate endDate = mFirstDate.plusDays(7);
        int index = 0;

        for (LocalDate date = mFirstDate; date.isBefore(endDate); date = date.plusDays(1))
        {
            dates[index] = date.getDayOfMonth();
            index++;
        }

        mView.showDates(dates);
    }
}
