package com.example.boysandgirlsclubevents.Calendar.DailyView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;

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
        int date = position + 1;
        int yearCode = mClubCalendar.getCurrentYearCode();
        int monthCode = mClubCalendar.getCurrentMonthCode();

        return CalendarDailyFragment.newInstance
                (date,
                mClubCalendar.getCurrentYear(),
                mClubCalendar.getCurrentMonth(),
                mClubCalendar.getDayOfWeek(yearCode, monthCode, date));
    }

    @Override
    public int getCount()
    {
        return mClubCalendar.getCurrentDaysInMonth();
    }
}