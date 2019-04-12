package com.example.boysandgirlsclubevents.Calendar.MonthlyView;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;

import org.threeten.bp.YearMonth;

public class MonthlyPagerAdapter extends FragmentStatePagerAdapter
{

    public MonthlyPagerAdapter(FragmentManager fragmentManager, ClubCalendar clubCalendar)
    {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position)
    {
        int year = ClubCalendar.mLocalDate.getYear();
        int month = ClubCalendar.mLocalDate.getMonth().getValue();
        return CalendarMonthlyFragment.newInstance(YearMonth.of(year, month));
    }

    @Override
    public int getCount()
    {
        // There are 12 months in a year.
        return 12;
    }
}
