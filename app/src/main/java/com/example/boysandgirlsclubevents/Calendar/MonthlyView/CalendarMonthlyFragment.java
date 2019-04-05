package com.example.boysandgirlsclubevents.Calendar.MonthlyView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boysandgirlsclubevents.R;

public class CalendarMonthlyFragment extends Fragment
{
    public static CalendarMonthlyFragment newInstance(int date, String month)
    {
        return new CalendarMonthlyFragment();
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
        View view = inflater.inflate(R.layout.calendar_monthly_view, container, false);
        return view;
    }
}
