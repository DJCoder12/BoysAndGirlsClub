package com.teamshark.boysandgirlsclubevents.Calendar;

public class CalendarSettings
{
    public enum CalendarType
    {
        Daily,
        Weekly,
        Monthly
    }

    private static CalendarType mCalendarType = CalendarType.Daily;
    private static Event.ClubLocation mCurrentLocation = Event.ClubLocation.Hill;
    private static Event mSelectedEvent;

    public static void switchDisplayType(CalendarType type)
    {
        mCalendarType = type;
    }

    public static CalendarType getCalendarType()
    {
        return mCalendarType;
    }

    public static void switchLocationFilter(Event.ClubLocation location)
    {
        mCurrentLocation = location;
    }

    public static Event.ClubLocation getLocation()
    {
        return mCurrentLocation;
    }

    public static Event getSelectedEvent()
    {
        return mSelectedEvent;
    }

    public static void setSelectedEvent(Event event)
    {
        mSelectedEvent = event;
    }
}
