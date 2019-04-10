package com.example.boysandgirlsclubevents.Calendar.WeeklyView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;

public class WeeklyPagerAdapter extends FragmentStatePagerAdapter
{
    private ClubCalendar mClubCalendar;

    public WeeklyPagerAdapter(FragmentManager fragmentManager, ClubCalendar clubCalendar)
    {
        super(fragmentManager);
        mClubCalendar = clubCalendar;
    }

    @Override
    public Fragment getItem(int position)
    {
        return CalendarWeeklyFragment.newInstance(position, mClubCalendar.getMonth());
    }

    @Override
    public int getCount()
    {
        //TODO: implement
        return 4;
    }
}
