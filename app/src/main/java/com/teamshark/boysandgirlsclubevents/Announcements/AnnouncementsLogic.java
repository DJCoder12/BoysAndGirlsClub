package com.teamshark.boysandgirlsclubevents.Announcements;

import com.teamshark.boysandgirlsclubevents.Util.Observer;

import java.util.List;

public class AnnouncementsLogic implements Observer
{
    private FirebaseAnnouncements mAnnouncementsDB = FirebaseAnnouncements.getInstance();
    private AnnouncementsFragment mView;
    private List<Announcement> mAnnouncements;

    public AnnouncementsLogic(AnnouncementsFragment view)
    {
        mView = view;
        mAnnouncementsDB.subscribe(this);
    }

    @Override
    public void update(String message)
    {
        if (message.equals(FirebaseAnnouncements.MessageCode.finishedLoadingAnnouncements.toString()))
        {
            mAnnouncements = mAnnouncementsDB.getAnnouncements();
            mView.showAnnouncements(mAnnouncements);
        }
    }

    public void handleSubmitAnnouncement(String title, String body)
    {

    }

    public void handleLoadingAnnouncements()
    {
        mAnnouncementsDB.loadAnnouncements();
    }
}
