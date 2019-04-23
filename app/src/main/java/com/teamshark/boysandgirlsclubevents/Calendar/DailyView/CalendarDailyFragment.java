package com.teamshark.boysandgirlsclubevents.Calendar.DailyView;

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

import com.teamshark.boysandgirlsclubevents.Calendar.Event;
import com.teamshark.boysandgirlsclubevents.Calendar.EventAdapter;
import com.teamshark.boysandgirlsclubevents.R;

import org.threeten.bp.LocalDate;

import java.util.List;


public class CalendarDailyFragment extends Fragment
{
    private static final String TAG = "CalendarDailyFragment";
    private LocalDate mLocalDate;
    private CalendarDailyLogic mLogic;

    private TextView mDateText;
    private TextView mDayText;
    private RecyclerView mEventsRecyclerView;

    public static CalendarDailyFragment newInstance(LocalDate date)
    {
        CalendarDailyFragment fragment = new CalendarDailyFragment();
        fragment.setCurrentDate(date);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mLogic = new CalendarDailyLogic(this, mLocalDate);
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

    private void setCurrentDate(LocalDate localDate)
    {
        mLocalDate = localDate;
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
        EventAdapter eventAdapter = new EventAdapter(eventList, EventAdapter.Size.large, this.getContext());
        mEventsRecyclerView.setAdapter(eventAdapter);
    }
}
