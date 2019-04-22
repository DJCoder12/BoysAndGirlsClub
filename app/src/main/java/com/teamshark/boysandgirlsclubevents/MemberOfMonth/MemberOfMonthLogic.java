package com.teamshark.boysandgirlsclubevents.MemberOfMonth;

import com.teamshark.boysandgirlsclubevents.Util.Observer;

import java.util.List;

public class MemberOfMonthLogic implements Observer
{
    private List<MemberMonth> mMembers;
    private FirebaseMemberOfTheMonth mFirestoreDB;
    private MemberMonthFragment mView;

    public MemberOfMonthLogic(MemberMonthFragment view)
    {
        mFirestoreDB = FirebaseMemberOfTheMonth.getInstance();
        mFirestoreDB.subscribe(this);
        mView = view;
    }

    @Override
    public void update(String message)
    {
        if (message.equals(FirebaseMemberOfTheMonth.MessageCode.finishedLoadingWords.toString()))
        {
            mMembers = mFirestoreDB.getMembersOfMonth();
            mView.showData(mMembers);
        }
    }

    public void handleLoadingData()
    {
        mFirestoreDB.loadMembersOfMonth();
    }
}
