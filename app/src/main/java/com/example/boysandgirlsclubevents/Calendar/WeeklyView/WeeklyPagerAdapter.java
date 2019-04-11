package com.example.boysandgirlsclubevents.Calendar.WeeklyView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.WeekFields;

import java.util.Locale;

public class WeeklyPagerAdapter extends FragmentStatePagerAdapter
{
    private ClubCalendar mClubCalendar;
    private int mNumWeeks;

    public WeeklyPagerAdapter(FragmentManager fragmentManager, ClubCalendar clubCalendar)
    {
        super(fragmentManager);
        mClubCalendar = clubCalendar;
        mNumWeeks = mClubCalendar.getWeeksInMonth();
    }

    @Override
    public Fragment getItem(int position)
    {
        //Get start day of week
        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();

        //Get first date in month
        YearMonth yearMonth =  YearMonth.now();
        LocalDate firstDate = yearMonth.atDay(1);

        //Get first sunday of week
        firstDate = firstDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
       // firstDate = firstDate.with(fieldUS, 1);

        //set correct week
        firstDate = firstDate.plusWeeks(position);

        return CalendarWeeklyFragment.newInstance(firstDate);
    }

    @Override
    public int getCount()
    {
        return mNumWeeks;
    }
}
