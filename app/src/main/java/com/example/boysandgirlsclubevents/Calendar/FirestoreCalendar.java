package com.example.boysandgirlsclubevents.Calendar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.threeten.bp.YearMonth;

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

    public void deleteEvent(int year, int month, String id) {
        String eventType = "non-recurring";

        mFirestore.collection("events").document(eventType)
                .collection("years").document(Integer.toString(year))
                .collection("months").document(Integer.toString(month))
                .collection("events").document(id).delete();
    }

    public void queryEventsForYearMonth(YearMonth ym, OnCompleteListener<QuerySnapshot> ocl) {
        String eventType = "non-recurring";
        String year = Integer.toString(ym.getYear());

        // Month value from YearMonth is from 1 to 12.
        String month = Integer.toString(ym.getMonthValue());

        mFirestore.collection("events").document(eventType)
                .collection("years").document(year)
                .collection("months").document(month)
                .collection("events").get().addOnCompleteListener(ocl);
    }
}
