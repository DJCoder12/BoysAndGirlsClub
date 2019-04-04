package com.example.boysandgirlsclubevents.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boysandgirlsclubevents.R;

public class CalendarFragment extends Fragment
{
    public static String TAG = "CalendarFragment";
    private ClubCalendar mClubCalendar;

    private FragmentStatePagerAdapter mPagerAdapter;
    private ViewPager mPager;

    public CalendarFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mClubCalendar = new ClubCalendar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        setUpPager(view);
        return view;
    }

    private void setUpPager(View view)
    {
        mPager = view.findViewById(R.id.vp_calendar);
        CalendarSettings.CalendarType curCalendarType = CalendarSettings.getCalendarType();

        if (curCalendarType == CalendarSettings.CalendarType.Daily)
        {
            showDailyCalendar();
        }
        else if (curCalendarType == CalendarSettings.CalendarType.Weekly)
        {
            showWeeklyCalendar();
        }
        else if (curCalendarType == CalendarSettings.CalendarType.Monthly)
        {
            showMonthlyCalendar();
        }
    }

    private void showDailyCalendar()
    {
        mPagerAdapter = new DailyPagerAdapter(getFragmentManager(), mClubCalendar);

        if (mPager != null)
        {
            mPager.setAdapter(mPagerAdapter);

            //The pager is 0 indexed so to convert to index, subtract 1
            mPager.setCurrentItem(mClubCalendar.getCurrentDate() - 1, true);
        }
    }

    private void showWeeklyCalendar()
    {
        mPagerAdapter = new WeeklyPagerAdapter(getFragmentManager(), mClubCalendar);

        if (mPager != null)
        {
            mPager.setAdapter(mPagerAdapter);
        }
        //TODO: Init current item
    }

    private void showMonthlyCalendar()
    {
        mPagerAdapter = new MonthlyPagerAdapter(getFragmentManager(), mClubCalendar);

        if (mPager != null)
        {
            mPager.setAdapter(mPagerAdapter);
        }

        //TODO: Init current item
    }
}
