package com.example.boysandgirlsclubevents.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.Calendar.DailyView.DailyPagerAdapter;
import com.example.boysandgirlsclubevents.Calendar.MonthlyView.MonthlyPagerAdapter;
import com.example.boysandgirlsclubevents.Calendar.WeeklyView.WeeklyPagerAdapter;
import com.example.boysandgirlsclubevents.R;

import java.util.Calendar;

public class CalendarFragment extends Fragment
{
    public static String TAG = "CalendarFragment";
    private final ClubCalendar mClubCalendar = new ClubCalendar();
    private CalendarLogic logic;

    private FragmentStatePagerAdapter mPagerAdapter;
    private ViewPager mPager;
    private TextView mMonthText;
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
        setUpMonthSwitcher(view);
        logic.handleInitialView();
        return view;
    }

    private void setUpPager(View view)
    {
        mPager = view.findViewById(R.id.vp_calendar);
    }

    private void setUpMonthSwitcher(View view)
    {
        mMonthText = view.findViewById(R.id.tv_monthTitle_calendar);
        mPrev = view.findViewById(R.id.ib_prevMonth_calendar);
        mNext = view.findViewById(R.id.ib_nextMonth_calendar);
        mPrev.setOnClickListener(prevListener);
        mNext.setOnClickListener(nextListener);
        showMonthTitle(mClubCalendar.getMonth());
    }

    public void showDailyCalendar()
    {
        mPagerAdapter = new DailyPagerAdapter(getFragmentManager(), mClubCalendar);

        if (mPager != null)
        {
            mPager.setAdapter(mPagerAdapter);
            mPagerAdapter.notifyDataSetChanged();
        }
    }

    public void showWeeklyCalendar()
    {
        mPagerAdapter = new WeeklyPagerAdapter(getFragmentManager(), mClubCalendar);

        if (mPager != null)
        {
            mPager.setAdapter(mPagerAdapter);
            mPagerAdapter.notifyDataSetChanged();
        }

    }

    public void showMonthlyCalendar()
    {
        mPagerAdapter = new MonthlyPagerAdapter(getFragmentManager(), mClubCalendar);

        if (mPager != null)
        {
            mPager.setAdapter(mPagerAdapter);
            mPagerAdapter.notifyDataSetChanged();
        }
    }

    public void showCurrentDay()
    {
        //The pager is 0 indexed so to convert to index, subtract 1
        mPager.setCurrentItem(mClubCalendar.getDate() - 1, true);
    }

    public void showFirstDay()
    {
        mPager.setCurrentItem(0, true);
    }

    public void showMonthTitle(String title)
    {
        mMonthText.setText(title);
    }

    public void onPrevMonth(View view)
    {

    }

    public void onNextMonth(View view)
    {

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