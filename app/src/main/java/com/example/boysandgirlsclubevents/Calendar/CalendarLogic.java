package com.example.boysandgirlsclubevents.Calendar;


public class CalendarLogic
{
    private CalendarFragment mView;
    private ClubCalendar mClubCalendar;

    public CalendarLogic(CalendarFragment view, ClubCalendar calendar)
    {
        mView = view;
        mClubCalendar = calendar;
    }

    public void handleInitialView()
    {
        handleShowingCorrectView();
        mView.showCurrentDay();
    }

    private void handleShowingCorrectView()
    {
        CalendarSettings.CalendarType curCalendarType = CalendarSettings.getCalendarType();

        if (curCalendarType == CalendarSettings.CalendarType.Daily)
        {
            mView.showDailyCalendar();
        }
        else if (curCalendarType == CalendarSettings.CalendarType.Weekly)
        {
            mView.showWeeklyCalendar();
        }
        else if (curCalendarType == CalendarSettings.CalendarType.Monthly)
        {
            mView.showMonthlyCalendar();
        }
    }

    public void handlePrevClick()
    {
        mClubCalendar.prevMonth();
        handleNewMonth();
    }

    public void handleNextClick()
    {
        mClubCalendar.nextMonth();
        handleNewMonth();
    }

    private void handleNewMonth()
    {
        mView.updateAdapter();
        mView.showMonthTitle(mClubCalendar.getMonth());
        mView.showFirstDay();
    }
}
