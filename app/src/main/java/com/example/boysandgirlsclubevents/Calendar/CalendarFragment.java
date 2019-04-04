package com.example.boysandgirlsclubevents.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boysandgirlsclubevents.R;

import java.util.HashMap;
import java.util.Map;

public class CalendarFragment extends Fragment
{
    public static String TAG = "CalendarFragment";
    private Map<String, Fragment> fragmentMap = new HashMap<>();
    enum CalendarType
    {
        Daily,
        Weekly,
        Monthly
    }

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ClubCalendar mClubCalendar;

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
        buildFragmentMap();
        showCalendarType(fragmentMap.get(CalendarType.Daily.name()), CalendarType.Daily.name());
        setUpPager(view);
        return view;
    }

    private void setUpPager(View view)
    {
        mPager = view.findViewById(R.id.vp_calendar);
        mPagerAdapter = new PagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        //The pager is 0 indexed so to convert to index, subtract 1
        mPager.setCurrentItem(mClubCalendar.getCurrentDate() - 1, true);
    }

    private void buildFragmentMap()
    {
        fragmentMap.put(CalendarType.Daily.name(), new CalendarDailyFragment());
        fragmentMap.put(CalendarType.Weekly.name(), new CalendarWeeklyFragment());
        fragmentMap.put(CalendarType.Monthly.name(), new CalendarMonthlyFragment());
    }

    private void showCalendarType(Fragment fragment, String tag)
    {
        /*getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_calendar, fragment, tag)
                .commit();*/
    }

    private class PagerAdapter extends FragmentStatePagerAdapter
    {
        public PagerAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
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
}
