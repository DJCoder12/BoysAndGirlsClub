package com.example.boysandgirlsclubevents.Calendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class DailyPagerAdapter extends FragmentStatePagerAdapter
{
    private ClubCalendar mClubCalendar;

    public DailyPagerAdapter(FragmentManager fragmentManager, ClubCalendar clubCalendar)
    {
        super(fragmentManager);
        mClubCalendar = clubCalendar;
    }

    @Override
    public Fragment getItem(int position)
    {
        //The pager is 0 indexed so to convert to date, add 1
        return CalendarDailyFragment.newInstance(position + 1, "April");
    }

    @Override
    public int getCount()
    {
        return mClubCalendar.getCurrentDaysInMonth();
    }
}