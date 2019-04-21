package com.example.boysandgirlsclubevents.Calendar.MonthlyView;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.boysandgirlsclubevents.Calendar.CalendarFragment;
import com.example.boysandgirlsclubevents.Calendar.CalendarSettings;
import com.example.boysandgirlsclubevents.Calendar.ClubCalendar;
import com.example.boysandgirlsclubevents.Calendar.Event;
import com.example.boysandgirlsclubevents.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CalendarMonthlyFragment extends Fragment {

    public static final String TAG = "CalendarMonthlyFragment";

    private ClubCalendar mClubCalendar;
    private YearMonth mYearMonth;
    private TextView mMonthTitle;
    private ArrayList<ConstraintLayout> mCells = new ArrayList<>();

    public static CalendarMonthlyFragment newInstance(YearMonth ym) {
        CalendarMonthlyFragment cmf = new CalendarMonthlyFragment();
        cmf.setYearMonth(ym);
        return cmf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_monthly_view, container, false);
        initCells(view);
        initTitle(view);
        showCalendarDays();
        showEvents(ClubCalendar.getEventsForMonth(mYearMonth));
        return view;
    }

    public void setYearMonth(YearMonth ym) {
        mYearMonth = ym;
    }

    public YearMonth getYearMonth() {
        return mYearMonth;
    }

    private void initCells(View v) {
        // Add the seven rows to the list of rows.
        ArrayList<ConstraintLayout> rows = new ArrayList<>();
        rows.add((ConstraintLayout) v.findViewById(R.id.rowOne));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowTwo));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowThree));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowFour));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowFive));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowSix));

        // For each row, add the cells to the list of cells.
        for (int i = 0; i < rows.size(); i++) {
            ConstraintLayout currRow = rows.get(i);
            mCells.add((ConstraintLayout) currRow.findViewById(R.id.dateItemOne));
            mCells.add((ConstraintLayout) currRow.findViewById(R.id.dateItemTwo));
            mCells.add((ConstraintLayout) currRow.findViewById(R.id.dateItemThree));
            mCells.add((ConstraintLayout) currRow.findViewById(R.id.dateItemFour));
            mCells.add((ConstraintLayout) currRow.findViewById(R.id.dateItemFive));
            mCells.add((ConstraintLayout) currRow.findViewById(R.id.dateItemSix));
            mCells.add((ConstraintLayout) currRow.findViewById(R.id.dateItemSeven));
        }
    }

    private void initTitle(View v) {
        mMonthTitle = v.findViewById(R.id.textViewMonthTitle);
        mMonthTitle.setText(mYearMonth.getMonth().toString());
    }

    private int getOffset() {
        // Offset is the day of the week that the month starts - 1.
        // For example, if the month starts on a Sunday (0), offset is -1.
        return mYearMonth.atDay(1).getDayOfWeek().getValue() - 1;
    }

    private void showCalendarDays() {
        int offset = getOffset();

        // Add one because of zero index.
        int maxDays = mYearMonth.lengthOfMonth() + 1;

        for (int i = 0; i < mCells.size(); i++) {
            // Get the cell.
            ConstraintLayout cell = mCells.get(i);

            // This is the actual day of the month we're on.
            final int actual = i - offset;

            // If the day number is valid.
            if (actual > 0 && actual < maxDays) {
                TextView tv = cell.findViewById(R.id.textViewDate);
                tv.setText(String.format(getResources().getConfiguration().locale,
                        "%d", actual));

                // Add an on-click listener that opens a new fragment.
                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Link cell to appropriate local date.
                        LocalDate ld = LocalDate.of(mYearMonth.getYear(), mYearMonth.getMonth(), actual);
                        ClubCalendar.setLocalDate(ld);

                        // Get CalendarFragment.
                        CalendarFragment mainCalFrag = (CalendarFragment)
                                getFragmentManager().findFragmentByTag(CalendarFragment.TAG);

                        // Update CalendarFragment and settings.
                        CalendarSettings.switchDisplayType(CalendarSettings.CalendarType.Daily);
                        mainCalFrag.showDailyCalendar();
                        mainCalFrag.showDay(actual - 1);

                    }
                });
            } else { // If the day number is invalid.
                cell.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void showEvents(HashMap<Integer, List<Event>> events) {
        if (events == null) {
            return;
        }

        int offset = getOffset();

        // Add one because of zero index.
        int maxDays = mYearMonth.lengthOfMonth() + 1;

        for (int i = 0; i < mCells.size(); i++) {
            // Get the cell.
            ConstraintLayout cell = mCells.get(i);

            // This is the actual day of the month we're on.
            int actual = i - offset;

            // If the day number is valid.
            if (actual > 0 && actual < maxDays) {
                List<Event> eventsOnDay = events.get(actual);
                if (eventsOnDay != null && !eventsOnDay.isEmpty()) {
                    Event firstEvent = eventsOnDay.get(0);

                    Resources res = getResources();
                    switch (firstEvent.getColor()) {
                        case Red:
                            cell.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.eventRed)));
                            break;
                        case Green:
                            cell.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.eventGreen)));
                            break;
                        case Yellow:
                            cell.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.eventYellow)));
                            break;
                        case Blue:
                            cell.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.eventBlue)));
                            break;
                        case Orange:
                            cell.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.eventOrange)));
                            break;
                        case Purple:
                            cell.setBackgroundTintList(ColorStateList.valueOf(res.getColor(R.color.eventPurple)));
                            break;
                    }

                    ImageView iconIV = cell.findViewById(R.id.imageViewIcon);
                    Glide.with(cell).load(firstEvent.getIconUrl()).into(iconIV);
                }
            }
        }
    }
}