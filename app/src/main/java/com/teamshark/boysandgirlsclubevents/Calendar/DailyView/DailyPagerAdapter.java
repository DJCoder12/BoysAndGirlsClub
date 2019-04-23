package com.teamshark.boysandgirlsclubevents.Calendar.DailyView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.teamshark.boysandgirlsclubevents.Calendar.ClubCalendar;

import org.threeten.bp.LocalDate;

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
        LocalDate localDate = ClubCalendar.getLocalDate().withDayOfMonth(date);

        return CalendarDailyFragment.newInstance(localDate);
    }

    @Override
    public int getCount()
    {
        return mClubCalendar.getDaysInMonth();
    }
}