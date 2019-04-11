package com.example.boysandgirlsclubevents.Calendar.WeeklyView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;
import com.example.boysandgirlsclubevents.R;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarWeeklyFragment extends Fragment
{
    private final static String FIRST_DATE_KEY = "first date";
    private final static String MONTH_KEY = "month";
    private final static String YEAR_KEY = "year";

    private LocalDate mFirstDate;

    private ConstraintLayout mSun;
    private ConstraintLayout mMon;
    private ConstraintLayout mTue;
    private ConstraintLayout mWed;
    private ConstraintLayout mThu;
    private ConstraintLayout mFri;
    private ConstraintLayout mSat;

    private CalendarWeeklyLogic mLogic;
    private List<ConstraintLayout> mDays = new ArrayList<>();

    public static CalendarWeeklyFragment newInstance(LocalDate firstDate)
    {
       CalendarWeeklyFragment fragment = new CalendarWeeklyFragment();
       fragment.setFirstDate(firstDate);
       return fragment;
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
        showDayTitles(view);
        mLogic = new CalendarWeeklyLogic(this, mFirstDate);
        mLogic.handleInitialization();
        return view;
    }

    private void setFirstDate(LocalDate date)
    {
        mFirstDate = date;
    }

    public void showDayTitles(View view)
    {
        mSun = view.findViewById(R.id.day_sun_weekly);
        mMon = view.findViewById(R.id.day_mon_weekly);
        mTue = view.findViewById(R.id.day_tue_weekly);
        mWed = view.findViewById(R.id.day_wed_weekly);
        mThu = view.findViewById(R.id.day_thu_weekly);
        mFri = view.findViewById(R.id.day_fri_weekly);
        mSat = view.findViewById(R.id.day_sat_weekly);

        mDays.add(mSun);
        mDays.add(mMon);
        mDays.add(mTue);
        mDays.add(mWed);
        mDays.add(mThu);
        mDays.add(mFri);
        mDays.add(mSat);

        TextView sunText = mSun.findViewById(R.id.tv_day_daily);
        TextView monText = mMon.findViewById(R.id.tv_day_daily);
        TextView tueText = mTue.findViewById(R.id.tv_day_daily);
        TextView wedText = mWed.findViewById(R.id.tv_day_daily);
        TextView thuText = mThu.findViewById(R.id.tv_day_daily);
        TextView friText = mFri.findViewById(R.id.tv_day_daily);
        TextView satText = mSat.findViewById(R.id.tv_day_daily);

        sunText.setText(ClubCalendar.SUN_ABRV);
        monText.setText(ClubCalendar.MON_ABRV);
        tueText.setText(ClubCalendar.TUE_ABRV);
        wedText.setText(ClubCalendar.WED_ABRV);
        thuText.setText(ClubCalendar.THU_ABRV);
        friText.setText(ClubCalendar.FRI_ABRV);
        satText.setText(ClubCalendar.SAT_ABRV);
    }

    public void showDates(int[] dates)
    {
        int index = 0;
        for (ConstraintLayout dayLayout : mDays)
        {
            TextView dateText = dayLayout.findViewById(R.id.tv_date_daily);
            dateText.setText(String.valueOf(dates[index]));
            index++;
        }
    }
}
