package com.example.boysandgirlsclubevents.Calendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.R;

public class CalendarDailyFragment extends Fragment
{
    private static final String TAG = "CalendarDailyFragment";
    private static final String DATE_KEY = "date";
    private static final String MONTH_KEY = "month";
    private static final String DAY_KEY = "day";

    private int mDate;
    private String mDay;

    private TextView mDateText;
    private TextView mDayText;

    public static CalendarDailyFragment newInstance(int date, String month, String dayOfWeek)
    {
        Bundle args = new Bundle();
        args.putInt(DATE_KEY, date);
        args.putString(MONTH_KEY, month);
        args.putString(DAY_KEY, dayOfWeek);

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
            mDate = args.getInt(DATE_KEY);
            mDay = args.getString(DAY_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.calendar_daily_view, container, false);
        setUpDate(view);
        return view;
    }

    private void setUpDate(View view)
    {
        mDateText = view.findViewById(R.id.tv_date_daily);
        mDayText = view.findViewById(R.id.tv_day_daily);
        mDateText.setText(String.valueOf(mDate));
        mDayText.setText(String.valueOf(mDay));
    }
}
