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

        statements.add("This is a sample announcement1");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement2");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement3");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement4");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement5");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement6");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        statements.add("This is a sample announcement7");
        imageUrls.add("https://positivecoach.org/media/805204/bcga.png");

        Log.d(TAG, " SSS initImages added.");

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.my_recycler_view);
        Log.d(TAG, "SSS VIEW MADE .");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(statements, imageUrls, getContext());
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "SSS adapter set.");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
