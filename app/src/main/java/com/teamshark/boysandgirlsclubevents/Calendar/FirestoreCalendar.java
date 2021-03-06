package com.teamshark.boysandgirlsclubevents.Calendar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
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

    public void deleteRecurringEvent(String id) {
        mFirestore.collection("events").document("recurring")
                .collection("events").document(id).delete();
    }

    public void deleteNonRecurringEvent(int year, int month, String id) {
        mFirestore.collection("events").document("non-recurring")
                .collection("years").document(Integer.toString(year))
                .collection("months").document(Integer.toString(month))
                .collection("events").document(id).delete();
    }

    public void queryEventsForYearMonth(YearMonth ym, OnCompleteListener<QuerySnapshot> ocl) {
        String year = Integer.toString(ym.getYear());

        // Month value from YearMonth is from 1 to 12.
        String month = Integer.toString(ym.getMonthValue());

        mFirestore.collection("events").document("non-recurring")
                .collection("years").document(year)
                .collection("months").document(month)
                .collection("events").get().addOnCompleteListener(ocl);
    }

    public void queryRecurringEvents(OnCompleteListener<QuerySnapshot> ocl) {
        mFirestore.collection("events").document("recurring")
                .collection("events").get().addOnCompleteListener(ocl);
    }
}
