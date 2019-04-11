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

        CalendarSettings.CalendarType curCalendarType = CalendarSettings.getCalendarType();
        if (curCalendarType == CalendarSettings.CalendarType.Daily)
        {
            handleShowFirstDay();
        }
        else if (curCalendarType == CalendarSettings.CalendarType.Weekly)
        {
            handleShowFirstWeek();
        }
        else if (curCalendarType == CalendarSettings.CalendarType.Monthly)
        {
            //handle month logic
        }

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
