package com.example.boysandgirlsclubevents.Calendar.WeeklyView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.R;

public class CalendarWeeklyFragment extends Fragment
{
    public static CalendarWeeklyFragment newInstance(int firstDayDate, String month)
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
        View mon = view.findViewById(R.id.day_mon_weekly);
        TextView dayText = mon.findViewById(R.id.tv_day_daily);
        dayText.setText("Mon");
        return view;
    }

    public void showDayTitles()
    {

    }
}
