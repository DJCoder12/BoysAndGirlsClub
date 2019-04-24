package com.teamshark.boysandgirlsclubevents.MemberOfMonth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.teamshark.boysandgirlsclubevents.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.nio.file.Path;

public class AddMemberActivity extends AppCompatActivity {

    private EditText editTextClubhouse;
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        setTitle("Add a New Member of the Month");

    }

    private void saveCard() {
        String clubhouse = editTextClubhouse.getText().toString();
        String member = editTextName.getText().toString();

        if (clubhouse.trim().isEmpty() || member.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a clubhouse and a name", Toast.LENGTH_SHORT);
            return;
        }


        CollectionReference memberRef = FirebaseFirestore.getInstance()
                .collection("member of the month");
        memberRef.add(new MemberMonth(clubhouse, member));

        Toast.makeText(this, "Member added", Toast.LENGTH_SHORT);
        finish();
    }

    public void submit(View view) {
        saveCard();
    }
}
