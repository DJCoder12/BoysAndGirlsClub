package com.example.boysandgirlsclubevents.Announcements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boysandgirlsclubevents.R;
import com.example.boysandgirlsclubevents.RecyclerViewAdapter;

import java.util.ArrayList;

public class AnnouncementsFragment extends Fragment
{

    public static String TAG = "AnnouncementsFragment";

        private ArrayList<String> statements = new ArrayList<String>();
        private ArrayList<String> imageUrls = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_announcements);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
        Log.d(TAG, "initImage passed.");
        return inflater.inflate(R.layout.fragment_announcements, container, false);


    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        statements.add("This is a sample announcement");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");



        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.my_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(statements, imageUrls, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
