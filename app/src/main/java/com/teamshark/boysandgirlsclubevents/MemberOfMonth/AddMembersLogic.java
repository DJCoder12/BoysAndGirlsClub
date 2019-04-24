package com.teamshark.boysandgirlsclubevents.MemberOfMonth;

import com.teamshark.boysandgirlsclubevents.Calendar.Event;
import com.teamshark.boysandgirlsclubevents.Util.Observer;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddMembersLogic implements Observer
{
    private AddMemberActivity mView;
    private FirebaseMemberOfTheMonth mDB;

    public AddMembersLogic(AddMemberActivity view)
    {
        mView = view;
        mDB = FirebaseMemberOfTheMonth.getInstance();
        mDB.subscribe(this);
    }

    @Override
    public void update(String message)
    {
        if (message.equals(FirebaseMemberOfTheMonth.MessageCode.finishedAddingMembers.toString()))
        {
            mView.showSuccess();
        }
    }

    public void handleSendingToDB(HashMap<String, String> memberNames)
    {
        List<String> memberNamesKeys = new ArrayList<>(memberNames.keySet());
        HashMap<String, MemberMonth> members = new HashMap<>();

        for (String curKey : memberNamesKeys)
        {
            String memberName = memberNames.get(curKey);
            String clubName = curKey.substring(0, curKey.length() - 1);
            MemberMonth curMember = new MemberMonth(clubName, memberName);
            members.put(curKey, curMember);
        }

        mDB.addMembersOfTheMonth(members);
    }
}
