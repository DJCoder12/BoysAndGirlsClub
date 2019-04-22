package com.teamshark.boysandgirlsclubevents.Calendar.DailyView;

import com.teamshark.boysandgirlsclubevents.Calendar.ClubCalendar;
import com.teamshark.boysandgirlsclubevents.Calendar.Event;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.TextStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalendarDailyLogic
{
    private LocalDate mDate;

    private CalendarDailyFragment mDailyView;
    private List<Event> mEvents = new ArrayList<>();

    public CalendarDailyLogic(CalendarDailyFragment dailyView, LocalDate date)
    {
        mDailyView = dailyView;
        mDate = date;
    }

    public void handleLoadingEvents()
    {
        if (mDate != null)
        {
            mEvents = ClubCalendar.handleLocationFiltering(ClubCalendar.getEventsForDay(mDate));

            //Set events to an empty list to avoid errors
            if (mEvents == null)
            {
                mEvents = new ArrayList<>();
            }

            mDailyView.showEvents(mEvents);
        }
    }

    public void handleDate()
    {
        if (mDate != null)
        {
            mDailyView.showDate(mDate.getDayOfMonth(), mDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US));
        }
    }
}
