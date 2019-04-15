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

import java.util.ArrayList;

public class AnnouncementsFragment extends Fragment
{

    public static String TAG = "AnnouncementsFragment";

        private ArrayList<Announcement> allAnnouncements = new ArrayList<Announcement>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        return inflater.inflate(R.layout.fragment_announcements, container, false);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "SSS onCreate: started.");
        initImageBitmaps();
        Log.d(TAG, "initImage passed.");
    }

    private void initImageBitmaps() {
        Log.d(TAG, "SSS initImageBitmaps: preparing bitmaps");
        Announcement announcement1 = new Announcement("A1","This is an announcment1", "https://radiocms-images.us1.eldarioncloud.com/resize/750/https://storage.googleapis.com/media.mwcradio.com/mimesis/2014-12/09/boys%20and%20girls%20club.png");
        Announcement announcement2 = new Announcement("A2","This is an announcment2", "https://radiocms-images.us1.eldarioncloud.com/resize/750/https://storage.googleapis.com/media.mwcradio.com/mimesis/2014-12/09/boys%20and%20girls%20club.png");
        Announcement announcement3 = new Announcement("A3","This is an announcment3", "https://radiocms-images.us1.eldarioncloud.com/resize/750/https://storage.googleapis.com/media.mwcradio.com/mimesis/2014-12/09/boys%20and%20girls%20club.png");
        Announcement announcement4 = new Announcement("A4","This is an announcment4", "https://radiocms-images.us1.eldarioncloud.com/resize/750/https://storage.googleapis.com/media.mwcradio.com/mimesis/2014-12/09/boys%20and%20girls%20club.png");
        Announcement announcement5 = new Announcement("A5","This is an announcment5", "https://radiocms-images.us1.eldarioncloud.com/resize/750/https://storage.googleapis.com/media.mwcradio.com/mimesis/2014-12/09/boys%20and%20girls%20club.png");
        Announcement announcement6 = new Announcement("A6","This is an announcment6", "https://radiocms-images.us1.eldarioncloud.com/resize/750/https://storage.googleapis.com/media.mwcradio.com/mimesis/2014-12/09/boys%20and%20girls%20club.png");
        Announcement announcement7 = new Announcement("A7","This is an announcment7", "https://radiocms-images.us1.eldarioncloud.com/resize/750/https://storage.googleapis.com/media.mwcradio.com/mimesis/2014-12/09/boys%20and%20girls%20club.png");
        Announcement announcement8 = new Announcement("A8","This is an announcment8", "https://radiocms-images.us1.eldarioncloud.com/resize/750/https://storage.googleapis.com/media.mwcradio.com/mimesis/2014-12/09/boys%20and%20girls%20club.png");
        Announcement announcement9 = new Announcement("A9","This is an announcment9", "https://radiocms-images.us1.eldarioncloud.com/resize/750/https://storage.googleapis.com/media.mwcradio.com/mimesis/2014-12/09/boys%20and%20girls%20club.png");

        allAnnouncements.add(announcement1);
        allAnnouncements.add(announcement2);
        allAnnouncements.add(announcement3);
        allAnnouncements.add(announcement4);
        allAnnouncements.add(announcement5);
        allAnnouncements.add(announcement6);
        allAnnouncements.add(announcement7);
        allAnnouncements.add(announcement8);
        allAnnouncements.add(announcement9);
        Log.d(TAG, " SSS initImages added.");

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.my_recycler_view);
        Log.d(TAG, "SSS VIEW MADE .");
        AnnouncementsRecyclerViewAdapter adapter = new AnnouncementsRecyclerViewAdapter(allAnnouncements, getContext());
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "SSS adapter set.");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
