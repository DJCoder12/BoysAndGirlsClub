package com.teamshark.boysandgirlsclubevents.Calendar;

public class EventDetailLogic
{
    private EventDetailActivity mView;
    private Event mEvent;

    public EventDetailLogic(EventDetailActivity view, Event event)
    {
        mView = view;
        mEvent = event;
    }

    public void handleLoadingData()
    {
        mView.showImage(mEvent.getIconUrl());
        mView.showTitle(mEvent.getTitle());
        mView.showDate(mEvent.getDateString());
        mView.showTime(mEvent.getStartTimeString());
        mView.showDescription(mEvent.getDescription());
    }
}
