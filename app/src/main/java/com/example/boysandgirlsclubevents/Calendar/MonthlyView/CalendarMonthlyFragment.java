package com.example.boysandgirlsclubevents.Calendar.MonthlyView;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.Calendar.ClubCalendarNew;
import com.example.boysandgirlsclubevents.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.ZoneId;

import java.util.ArrayList;
import java.util.Map;

public class CalendarMonthlyFragment extends Fragment {

    public static final String TAG = "CalendarMonthlyFragment";

    private ClubCalendarNew mClubCalendar;
    private YearMonth mYearMonth;
    private boolean mEvents[];
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

        // Initialize events array.
        mEvents = new boolean[mYearMonth.lengthOfMonth() + 1];

        mClubCalendar = ClubCalendarNew.getInstance();
        initCells(view);
        updateCalendarNumbers();
        mClubCalendar.queryEventsForMonth(mYearMonth, new UpdateCalendarOnComplete());
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

    private int getOffset() {
        // Offset is the day of the week that the month starts - 1.
        // For example, if the month starts on a Sunday (0), offset is -1.
        return mYearMonth.atDay(1).getDayOfWeek().getValue() - 1;
    }


    private void updateCalendarNumbers() {
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
                TextView tv = cell.findViewById(R.id.textViewDate);
                tv.setText(String.format(getResources().getConfiguration().locale,
                        "%d", actual));
            } else { // If the day number is invalid.
                cell.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void updateCalendarEvents() {
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
                if (mEvents[actual]) {
                    cell.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.eventGreen)));
                }
            }
        }
    }

    private class UpdateCalendarOnComplete implements OnCompleteListener<QuerySnapshot> {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Map<String, Object> fields = doc.getData();
                    String title = (String) fields.get("title");

                    // Convert timestamp to local date.
                    Timestamp startTimestamp = (Timestamp) fields.get("start_time");
                    Long startTime = startTimestamp.toDate().getTime();
                    LocalDate date = Instant.ofEpochMilli(startTime).atZone(ZoneId.systemDefault()).toLocalDate();

                    mEvents[date.getDayOfMonth()] = true;

                    updateCalendarEvents();
                }
            }
        }
    }
}