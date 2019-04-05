package com.example.boysandgirlsclubevents.Calendar.WeeklyView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boysandgirlsclubevents.R;

public class CalendarWeeklyFragment extends Fragment
{
    public static CalendarWeeklyFragment newInstance(int date, String month)
    {
        return new CalendarWeeklyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.calendar_weekly_view, container, false);
        return view;
    }
}
