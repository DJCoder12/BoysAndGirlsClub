package com.teamshark.boysandgirlsclubevents.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.teamshark.boysandgirlsclubevents.Calendar.DailyView.DailyPagerAdapter;
import com.teamshark.boysandgirlsclubevents.Calendar.MonthlyView.MonthlyPagerAdapter;
import com.teamshark.boysandgirlsclubevents.Calendar.WeeklyView.WeeklyPagerAdapter;
import com.teamshark.boysandgirlsclubevents.R;

public class CalendarFragment extends Fragment
{
    public static String TAG = "CalendarFragment";
    private final ClubCalendar mClubCalendar = new ClubCalendar();
    private CalendarLogic logic;

    private FragmentStatePagerAdapter mPagerAdapter;
    private ViewPager mPager;
    private TextView mPagerText;
    private ImageButton mPrev;
    private ImageButton mNext;

    public CalendarFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        logic = new CalendarLogic(this, mClubCalendar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        setUpPager(view);
        setUpSwitcher(view);
        logic.handleInitialView();
        return view;
    }

    private void setUpPager(View view)
    {
        mPager = view.findViewById(R.id.vp_calendar);
    }

    private void setUpSwitcher(View view)
    {
        mPagerText = view.findViewById(R.id.tv_monthTitle_calendar);
        mPrev = view.findViewById(R.id.ib_prevMonth_calendar);
        mNext = view.findViewById(R.id.ib_nextMonth_calendar);
        mPrev.setOnClickListener(prevListener);
        mNext.setOnClickListener(nextListener);
    }

    public void showDailyCalendar()
    {
        showTitle(mClubCalendar.getMonth() + ' ' + Integer.toString(mClubCalendar.getYear()));
        mPagerAdapter = new DailyPagerAdapter(getFragmentManager(), mClubCalendar);

        if (mPager != null)
        {
            mPager.setAdapter(mPagerAdapter);
            mPagerAdapter.notifyDataSetChanged();
        }
    }

    public void showWeeklyCalendar()
    {
        showTitle(mClubCalendar.getMonth() + ' ' + Integer.toString(mClubCalendar.getYear()));
        mPagerAdapter = new WeeklyPagerAdapter(getFragmentManager(), mClubCalendar);

        if (mPager != null)
        {
            mPager.setAdapter(mPagerAdapter);
            mPagerAdapter.notifyDataSetChanged();
        }

    }

    public void showMonthlyCalendar()
    {
        showTitle(Integer.toString(mClubCalendar.getYear()));
        mPagerAdapter = new MonthlyPagerAdapter(getFragmentManager(), mClubCalendar);

        if (mPager != null)
        {
            mPager.setAdapter(mPagerAdapter);
            mPagerAdapter.notifyDataSetChanged();
        }
    }

    // Input to showDay, showWeek, showMonth must be zero-indexed for the pager.
    public void showDay(int date)
    {
        mPager.setCurrentItem(date);
    }

    public void showWeek(int week)
    {
        mPager.setCurrentItem(week);
    }

    public void showMonth(int month)
    {
        mPager.setCurrentItem(month);
    }

    public void showTitle(String title)
    {
        mPagerText.setText(title);
    }

    public void updateAdapter()
    {
        mPager.setAdapter(mPagerAdapter);
        mPagerAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener prevListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            logic.handlePrevClick();
        }
    };

    private View.OnClickListener nextListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            logic.handleNextClick();
        }
    };
}
