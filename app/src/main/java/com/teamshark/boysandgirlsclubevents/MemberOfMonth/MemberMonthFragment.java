package com.teamshark.boysandgirlsclubevents.MemberOfMonth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

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
        MemberAdapter memberAdapter = new MemberAdapter(memberList);
        mRecyclerView.setAdapter(memberAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        memberAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        MemberAdapter adapter = (MemberAdapter) mRecyclerView.getAdapter();

        if (adapter != null)
        {
            adapter.clear();
        }

        mLogic.handleLoadingData();
    }
}
