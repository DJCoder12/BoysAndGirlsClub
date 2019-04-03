package com.example.boysandgirlsclubevents.Announcements;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseAnnouncements
{
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private final String ANNOUNCEMENT_PATH = "announcements";

    public void addAnnouncement(Announcement announcement)
    {
        database.collection(ANNOUNCEMENT_PATH).add(announcement)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        {
            @Override
            public void onSuccess(DocumentReference documentReference)
            {

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {

            }
        });
    }
}
