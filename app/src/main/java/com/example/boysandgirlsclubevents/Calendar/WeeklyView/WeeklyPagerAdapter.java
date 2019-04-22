package com.example.boysandgirlsclubevents.Calendar.WeeklyView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.temporal.TemporalAdjusters;

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
        //Get first date in month
        YearMonth yearMonth =  YearMonth.from(ClubCalendar.getLocalDate());
        LocalDate firstDate = yearMonth.atDay(1);

        //Get first sunday of week
        firstDate = firstDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        //set correct week - 1?
        firstDate = firstDate.plusWeeks(position - 1);

        return CalendarWeeklyFragment.newInstance(firstDate);
    }

    @Override
    public int getCount()
    {
        return mClubCalendar.getWeeksInMonth();
    }
}
