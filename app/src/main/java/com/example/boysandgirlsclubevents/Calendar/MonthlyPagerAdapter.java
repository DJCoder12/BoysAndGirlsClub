package com.example.boysandgirlsclubevents.Calendar;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MonthlyPagerAdapter extends FragmentStatePagerAdapter
{
    private ClubCalendar mClubCalendar;

    public MonthlyPagerAdapter(FragmentManager fragmentManager, ClubCalendar clubCalendar)
    {
        super(fragmentManager);
        mClubCalendar = clubCalendar;
    }

    @Override
    public Fragment getItem(int position)
    {
        return CalendarMonthlyFragment.newInstance(position, "April");
    }

    @Override
    public int getCount()
    {
        //TODO: implement
        return 1;
    }
}
