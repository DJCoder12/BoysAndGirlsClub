package com.example.boysandgirlsclubevents.Announcements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boysandgirlsclubevents.R;

import java.util.List;

public class AnnouncementsFragment extends Fragment
{
    public static String TAG = "AnnouncementsFragment";
    private AnnouncementsLogic mLogic;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_announcements, container, false);
        mLogic = new AnnouncementsLogic(this);
        mLogic.handleLoadingAnnouncements();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "SSS onCreate: started.");
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        mRecyclerView = getView().findViewById(R.id.my_recycler_view);
        Log.d(TAG, "SSS VIEW MADE .");
    }

    public void showAnnouncements(List<Announcement> announcements)
    {

        AnnouncementsRecyclerViewAdapter adapter = new AnnouncementsRecyclerViewAdapter(announcements, getContext());
        mRecyclerView.setAdapter(adapter);
        Log.d(TAG, "SSS adapter set.");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
