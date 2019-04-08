package com.example.boysandgirlsclubevents.Calendar.DailyView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.Calendar.Event;
import com.example.boysandgirlsclubevents.R;

import java.util.List;


public class CalendarDailyFragment extends Fragment
{
    private static final String TAG = "CalendarDailyFragment";
    private static final String DATE_KEY = "date";
    private static final String MONTH_KEY = "month";
    private static final String YEAR_KEY = "year";
    private static final String DAY_KEY = "day";

    private CalendarDailyLogic mLogic;

    private TextView mDateText;
    private TextView mDayText;
    private RecyclerView mEventsRecyclerView;

    public static CalendarDailyFragment newInstance(int date, int year, String month, String dayOfWeek)
    {
        Bundle args = new Bundle();
        args.putInt(DATE_KEY, date);
        args.putString(MONTH_KEY, month);
        args.putString(DAY_KEY, dayOfWeek);
        args.putString(YEAR_KEY, String.valueOf(year));

        CalendarDailyFragment fragment = new CalendarDailyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null)
        {
            int date = args.getInt(DATE_KEY);
            String day = args.getString(DAY_KEY);
            String year = args.getString(YEAR_KEY);
            String month = args.getString(MONTH_KEY);


            mLogic = new CalendarDailyLogic(this, year, month, day, date);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.calendar_daily_view, container, false);
        setUpDate(view);
        setUpEvents(view);
        mLogic.handleDate();
        mLogic.handleLoadingEvents();
        return view;
    }

    private void setUpEvents(View view)
    {
        mEventsRecyclerView = view.findViewById(R.id.rv_events_daily);
    }

    private void setUpDate(View view)
    {
        mDateText = view.findViewById(R.id.tv_date_daily);
        mDayText = view.findViewById(R.id.tv_day_daily);
    }

    public void showDate(int date, String day)
    {
        mDateText.setText(String.valueOf(date));
        mDayText.setText(String.valueOf(day));
    }

    public void showEvents(List<Event> eventList)
    {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mEventsRecyclerView.setLayoutManager(layoutManager);
        DailyViewAdapter dailyViewAdapter = new DailyViewAdapter(eventList, this.getContext());
        mEventsRecyclerView.setAdapter(dailyViewAdapter);
    }
}
