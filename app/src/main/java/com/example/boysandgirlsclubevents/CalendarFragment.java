package com.example.boysandgirlsclubevents;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarFragment extends Fragment
{

    public static String TAG = "CalendarFragment";

    private Toolbar calendar_toolbar;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    CompactCalendarView compactCalendar;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.compactcalendar, container, false);

        calendar_toolbar = view.findViewById(R.id.calendar_toolbar);

        final TextView mTitle = calendar_toolbar.findViewById(R.id.toolbar_title);

        compactCalendar = view.findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        Event ev1 = new Event(Color.RED, 1554436800000L, "Teacher's Professional Day");
        compactCalendar.addEvent(ev1);

        Event ev2 = new Event(Color.RED, 1556596800000L, "Healthy Lifestyle Day");
        compactCalendar.addEvent(ev2);

        mTitle.setText(dateFormatForMonth.format(compactCalendar.getFirstDayOfCurrentMonth()));

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {


            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getActivity().getApplicationContext();

                if (dateClicked.toString().compareTo("Fri Apr 05 00:00:00 EDT 2019") == 0) {
                    Toast.makeText(context, "Teacher's Professional Day", Toast.LENGTH_SHORT).show();
                }

                else if (dateClicked.toString().compareTo("Tue Apr 30 00:00:00 EDT 2019") == 0) {
                    Toast.makeText(context, "Healthy Lifestyle Day", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                // Changes toolbar title on monthChange
                //actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
                mTitle.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        gotoToday();

        return view;
    }

    public void gotoToday() {

        // Set any date to navigate to particular date
        compactCalendar.setCurrentDate(Calendar.getInstance(Locale.getDefault()).getTime());

    }
}
