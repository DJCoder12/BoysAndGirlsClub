package com.teamshark.boysandgirlsclubevents.MemberOfMonth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.teamshark.boysandgirlsclubevents.R;

import java.util.ArrayList;


import com.teamshark.boysandgirlsclubevents.R;
import java.util.List;


public class MemberMonthFragment extends Fragment
{
    public static String TAG = "MemberMonthFragment";
    private RecyclerView mRecyclerView;
    private MemberOfMonthLogic mLogic;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mLogic = new MemberOfMonthLogic(this);
        mLogic.handleLoadingData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_member_month, container, false);
        setUpRecyclerView(rootView);
        return rootView;
    }

    private void setUpRecyclerView(View view)
    {
        mRecyclerView = view.findViewById(R.id.newRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void showData(List<MemberMonth> memberList)
    {
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
                //updateAllData(view);
            }
        };
        MemberAdapter memberAdapter = new MemberAdapter(memberList, listener);
        memberAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(memberAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void updateAllData(View view){
        mLogic.updateNewData();
    }

}
