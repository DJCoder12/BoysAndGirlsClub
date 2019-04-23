package com.teamshark.boysandgirlsclubevents;

import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddAnnouncementActivity extends Activity {

    private FirebaseFirestore mFirestore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);
        mFirestore = FirebaseFirestore.getInstance();
    }
}
