package com.example.boysandgirlsclubevents.MemberOfMonth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boysandgirlsclubevents.R;
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
        MemberAdapter memberAdapter = new MemberAdapter(memberList);
        mRecyclerView.setAdapter(memberAdapter);
    }
}
