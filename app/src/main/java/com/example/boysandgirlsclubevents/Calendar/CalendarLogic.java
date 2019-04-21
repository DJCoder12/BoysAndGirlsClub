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
    }

    private void handleShowingCorrectView()
    {
        CalendarSettings.CalendarType curCalendarType = CalendarSettings.getCalendarType();

        if (curCalendarType == CalendarSettings.CalendarType.Daily)
        {
            mView.showDailyCalendar();
            handleShowCurrentDay();
        }
        else if (curCalendarType == CalendarSettings.CalendarType.Weekly)
        {
            mView.showWeeklyCalendar();
            handleShowCurrentWeek();
        }
        else if (curCalendarType == CalendarSettings.CalendarType.Monthly)
        {
            mView.showMonthlyCalendar();
            handleShowCurrentMonth();
        }
    }

    public void handlePrevClick()
    {
        CalendarSettings.CalendarType currCalType = CalendarSettings.getCalendarType();
        if (currCalType == CalendarSettings.CalendarType.Monthly)
        {
            mClubCalendar.prevYear();
            handleNewYear();
        }
        else
        {
            mClubCalendar.prevMonth();
            handleNewMonth();
        }
    }

    public void handleNextClick()
    {
        CalendarSettings.CalendarType currCalType = CalendarSettings.getCalendarType();
        if (currCalType == CalendarSettings.CalendarType.Monthly)
        {
            mClubCalendar.nextYear();
            handleNewYear();
        }
        else
        {
            mClubCalendar.nextMonth();
            handleNewMonth();
        }
    }

    private void handleNewYear()
    {
        mView.updateAdapter();
        mView.showTitle(Integer.toString(mClubCalendar.getYear()));
        handleShowCurrentMonth();
    }

    private void handleNewMonth()
    {
        mView.updateAdapter();
        mView.showTitle(mClubCalendar.getMonth() + ' ' + Integer.toString(mClubCalendar.getYear()));

        CalendarSettings.CalendarType curCalendarType = CalendarSettings.getCalendarType();
        if (curCalendarType == CalendarSettings.CalendarType.Daily)
        {
            handleShowFirstDay();
        }
        if (curCalendarType == CalendarSettings.CalendarType.Weekly)
        {
            handleShowFirstWeek();
        }
    }

    public void handleShowCurrentMonth() {
        // The pager is zero-indexed so convert by subtracting 1.
        mView.showMonth(mClubCalendar.getMonthValue() - 1);
    }

    public void handleShowCurrentDay()
    {
        //The pager is 0 indexed so to convert to index, subtract 1
        mView.showDay(mClubCalendar.getDate() - 1);
    }

    public void handleShowFirstDay()
    {
        mView.showDay(0);
    }

    public void handleShowCurrentWeek()
    {
        //The pager is 0 indexed so to convert to index, subtract 1
        mView.showWeek(mClubCalendar.getWeekOfMonth() - 1);
    }

    public void handleShowFirstWeek()
    {
        mView.showWeek(0);
    }
}
