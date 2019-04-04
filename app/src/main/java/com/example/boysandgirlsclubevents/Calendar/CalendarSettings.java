package com.example.boysandgirlsclubevents.Calendar;

public class CalendarSettings
{
    public enum CalendarType
    {
        Daily,
        Weekly,
        Monthly
    }

    private static CalendarType mCalendarType = CalendarType.Daily;

    public static void switchDisplayType(CalendarType type)
    {
        mCalendarType = type;
    }

    public static CalendarType getCalendarType()
    {
        return mCalendarType;
    }
}
