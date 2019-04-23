package com.teamshark.boysandgirlsclubevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.teamshark.boysandgirlsclubevents.Announcements.Announcement;
import com.teamshark.boysandgirlsclubevents.Announcements.FirebaseAnnouncements;

public class AddAnnouncementActivity extends Activity {

    private final String TAG = "FirebaseAnnouncements";
    private static FirebaseAnnouncements instance;
    private final String ANNOUNCEMENT_PATH = "announcements";
    private FirebaseFirestore mDB = FirebaseFirestore.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);
    }

    public void sendAnnouncement(View v){

        EditText titlev = findViewById(R.id.Title);
        EditText datev = findViewById(R.id.Date);
        EditText bodyv = findViewById(R.id.Body);

        String title = titlev.getText().toString();
        String date = datev.getText().toString();
        String body = bodyv.getText().toString();

        Announcement newAnnouncement = new Announcement(title, body,"stuff", date );
        mDB.collection(ANNOUNCEMENT_PATH).add(newAnnouncement);

        Intent i = new Intent(this,NavigationActivity.class);
        startActivity(i);
        

    }
}
