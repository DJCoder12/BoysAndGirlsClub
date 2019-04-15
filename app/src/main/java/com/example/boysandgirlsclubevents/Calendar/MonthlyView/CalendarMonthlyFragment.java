package com.example.boysandgirlsclubevents.Calendar.MonthlyView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.R;

import org.threeten.bp.YearMonth;

import java.util.ArrayList;

public class CalendarMonthlyFragment extends Fragment
{

    private YearMonth mYearMonth;
    private ArrayList<ConstraintLayout> mCells = new ArrayList<>();

    public static CalendarMonthlyFragment newInstance(YearMonth ym)
    {
        CalendarMonthlyFragment cmf = new CalendarMonthlyFragment();
        cmf.setYearMonth(ym);
        return cmf;
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
        initCells(view);
        updateCalendar();
        return view;
    }

    public void setYearMonth(YearMonth ym) {
        mYearMonth = ym;
    }

    private void initCells(View v) {
        ArrayList<ConstraintLayout> rows = new ArrayList<>();
        rows.add((ConstraintLayout) v.findViewById(R.id.rowOne));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowTwo));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowThree));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowFour));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowFive));
        rows.add((ConstraintLayout) v.findViewById(R.id.rowSix));
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

    private void updateCalendar() {
        // Offset is the day of the week that the month starts - 1.
        // For example, if the month starts on a Sunday (0), offset is -1.
        int offset = mYearMonth.atDay(1).getDayOfWeek().getValue() - 1;

        // Keep track of how much days there are in a month.
        int maxDays = mYearMonth.lengthOfMonth();

        for (int i = 0; i < mCells.size(); i++) {
            // Get the cell.
            ConstraintLayout cell = mCells.get(i);

            // This is the actual day of the month we're on.
            int actual = i - offset;

            // If the day number is valid.
            if (actual > 0 && actual < maxDays + 1) {
                TextView tv = cell.findViewById(R.id.textViewDate);
                tv.setText(String.format(getResources().getConfiguration().locale,
                        "%d", actual));
            } else { // If the day number is invalid.
                cell.setVisibility(View.INVISIBLE);
            }
        }
    }
}
