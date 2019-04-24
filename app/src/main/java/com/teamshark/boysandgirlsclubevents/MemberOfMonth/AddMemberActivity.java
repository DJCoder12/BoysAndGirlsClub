package com.teamshark.boysandgirlsclubevents.MemberOfMonth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.teamshark.boysandgirlsclubevents.R;

import java.util.HashMap;

public class AddMemberActivity extends AppCompatActivity
{
    private final String TAG = "AddMemberActivity";
    private final String TITLE = "Add Members of the Month";

    private EditText mHill1EditText;
    private EditText mHill2EditText;
    private EditText mJackWalker1EditText;
    private EditText mJackWalker2EditText;
    private EditText mColumbia1EditText;
    private EditText mColumbia2EditText;
    private EditText mSoutheast1EditText;
    private EditText mSoutheast2EditText;

    private AddMembersLogic mLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        mLogic = new AddMembersLogic(this);
        initializeVisuals();
        findViews();
    }

    private void initializeVisuals()
    {
        setTitle(TITLE);
    }

    private void findViews()
    {
        mHill1EditText = findViewById(R.id.et_hill1_addMember);
        mHill2EditText = findViewById(R.id.et_hill2_addMember);
        mJackWalker1EditText = findViewById(R.id.et_jackWalker1_addMember);
        mJackWalker2EditText = findViewById(R.id.et_jackWalker2_addMember);
        mColumbia1EditText = findViewById(R.id.et_columbia1_addMember);
        mColumbia2EditText = findViewById(R.id.et_columbia2_addMember);
        mSoutheast1EditText = findViewById(R.id.et_southEast1_addMember);
        mSoutheast2EditText = findViewById(R.id.et_southEast2_addMember);
    }

    public void submit(View view)
    {
        if (mHill1EditText.getText().toString().isEmpty() || mHill2EditText.getText().toString().isEmpty()
            || mJackWalker1EditText.getText().toString().isEmpty() || mJackWalker2EditText.getText().toString().isEmpty()
            || mColumbia1EditText.getText().toString().isEmpty() || mColumbia2EditText.getText().toString().isEmpty()
            || mSoutheast1EditText.getText().toString().isEmpty() || mSoutheast2EditText.getText().toString().isEmpty())
        {
            showError();
            return;
        }

        HashMap<String, String> memberNames = new HashMap<>();
        memberNames.put("Hill1", mHill1EditText.getText().toString());
        memberNames.put("Hill2", mHill2EditText.getText().toString());
        memberNames.put("Jack Walker1", mJackWalker1EditText.getText().toString());
        memberNames.put("Jack Walker2", mJackWalker2EditText.getText().toString());
        memberNames.put("Columbia1", mColumbia1EditText.getText().toString());
        memberNames.put("Columbia2", mColumbia2EditText.getText().toString());
        memberNames.put("Southeast1", mSoutheast1EditText.getText().toString());
        memberNames.put("Southeast2", mSoutheast2EditText.getText().toString());

        mLogic.handleSendingToDB(memberNames);
    }

    private void showError()
    {
        Toast.makeText(this, R.string.addMemberMonth_errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void showSuccess()
    {
        Toast.makeText(this, R.string.addMemberMonth_successMessage, Toast.LENGTH_SHORT).show();
    }
}
