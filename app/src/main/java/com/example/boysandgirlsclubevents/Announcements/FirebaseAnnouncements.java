package com.example.boysandgirlsclubevents.Announcements;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.boysandgirlsclubevents.Util.Observable;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseAnnouncements extends Observable
{
    private final String TAG = "FirebaseAnnouncements";
    private static FirebaseAnnouncements instance;
    private final String ANNOUNCEMENT_PATH = "announcements";
    private FirebaseFirestore mDB = FirebaseFirestore.getInstance();
    private List<Announcement> mAnnouncements = new ArrayList<>();

    public enum MessageCode
    {
        finishedAddingAnnouncement,
        finishedLoadingAnnouncements
    }

    private FirebaseAnnouncements() {}

    public static FirebaseAnnouncements getInstance()
    {
        if (instance == null)
        {
            instance = new FirebaseAnnouncements();
        }

        return instance;
    }

    public void loadAnnouncements()
    {
        mDB.collection(ANNOUNCEMENT_PATH).get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
            {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                    {
                        Announcement announcement = document.toObject(Announcement.class);
                        Log.d(TAG, "onSuccess: " + announcement.getTitle());
                        mAnnouncements.add(announcement);
                    }

                    notifyObservers(MessageCode.finishedLoadingAnnouncements.toString());
                }
            })
            .addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception e)
                {

                }
            });
    }

    public void addAnnouncement(Announcement announcement)
    {
        mDB.collection(ANNOUNCEMENT_PATH).add(announcement)
        .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        {
            @Override
            public void onSuccess(DocumentReference documentReference)
            {
                notifyObservers(MessageCode.finishedAddingAnnouncement.toString());
            }

        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {

            }
        });
    }

    public List<Announcement> getAnnouncements()
    {
        return mAnnouncements;
    }
}
