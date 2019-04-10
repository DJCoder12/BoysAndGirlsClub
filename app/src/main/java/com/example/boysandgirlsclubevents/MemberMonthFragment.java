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

public class MemberMonthFragment extends Fragment
{
    public static String TAG = "MemberMonthFragment";

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


        String[] title = new String[]{
                "Ashlen is a new member to the club and has made a great impression since she started! Each day she participates in Power Hour where she completes her homework and tutors others. Her favorite activity to help with is Supper Club! Ashlen has a cheerful personality that draws in other members as she models responsible and respectful behavior!",
                "Carlos is a long-time member that we love having around our Club! He participated in Flag Football and Passport to Manhood during the Fall and is looking forward to being involved with Jack Walker’s Basketball League. Carlos does a great job of motivating his fellow members and inspiring them to also be involved in the Club. He is a great mentor to younger members by modeling respectful and responsible behavior every day."
        };

        int [] picturePath = new int[]{
                R.drawable.ashlencurry,
                R.drawable.carloscuevas
        };

        ListAdapter listAdapter = new ListAdapter(title, picturePath);
        recyclerView.setAdapter(listAdapter);


        return rootView;
    }
}
