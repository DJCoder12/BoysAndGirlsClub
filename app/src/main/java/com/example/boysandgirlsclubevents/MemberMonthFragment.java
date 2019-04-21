package com.example.boysandgirlsclubevents;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;


public class MemberMonthFragment extends Fragment
{
    public static String TAG = "MemberMonthFragment";
    ArrayList<MemberMonth> members;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_member_month, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.newRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        members = new ArrayList<>();
        for(int i = 1; i <= 4; i++){
            members.add(new MemberMonth("Person " + i, "Clubhouse " + i));
        }

        FirebaseMemberOfTheMonth fire = new FirebaseMemberOfTheMonth();

        fire.addMemberOfTheMonth(members.get(0));
        fire.addMemberOfTheMonth(members.get(1));
        fire.addMemberOfTheMonth(members.get(2));
        fire.addMemberOfTheMonth(members.get(3));

        //members = MemberMonth.createMembersList(20);
        MemberAdapter memberAdapter = new MemberAdapter(members);
        recyclerView.setAdapter(memberAdapter);


        return rootView;
    }
}
