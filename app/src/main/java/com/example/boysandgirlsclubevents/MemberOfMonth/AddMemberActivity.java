package com.example.boysandgirlsclubevents.MemberOfMonth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boysandgirlsclubevents.R;
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

        editTextClubhouse = findViewById(R.id.newMemberClubhouse);
        editTextName = findViewById(R.id.newMemberName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_member_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_member:
                saveCard();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        FirebaseMemberOfTheMonth test = FirebaseMemberOfTheMonth.getInstance();
        test.addMemberOfTheMonth(new MemberMonth(clubhouse, member));

        Toast.makeText(this, "Member added", Toast.LENGTH_SHORT);
        finish();
    }
}
