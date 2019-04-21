package com.example.boysandgirlsclubevents.Calendar;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.ZoneId;

import java.util.Map;

public class FirestoreCalendar {

    private static FirestoreCalendar theOneAndOnly;
    private FirebaseFirestore mFirestore;

    public static FirestoreCalendar getInstance() {
        if (theOneAndOnly == null) {
            theOneAndOnly = new FirestoreCalendar();
        }

        return theOneAndOnly;
    }

    private FirestoreCalendar() {
        // Initialize Firestore.
        mFirestore = FirebaseFirestore.getInstance();
    }

    public void queryEventsForYearMonth(YearMonth ym, OnCompleteListener<QuerySnapshot> ocl) {
        String eventType = "non-recurring";
        String year = Integer.toString(ym.getYear());

        // Month value from YearMonth is from 1 to 12.
        String month = Integer.toString(ym.getMonthValue());

        // Add one to avoid zero indexing.
        int dayCount = ym.lengthOfMonth() + 1;

        mFirestore.collection("events").document(eventType)
                .collection("years").document(year)
                .collection("months").document(month)
                .collection("events").get().addOnCompleteListener(ocl);
    }
}
