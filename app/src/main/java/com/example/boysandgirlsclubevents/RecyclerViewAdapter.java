package com.example.boysandgirlsclubevents;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerView";
    private  ArrayList<String> appImages = new ArrayList<String>();
    private ArrayList<String> appAnnouncements = new ArrayList<String>();
    private Context appContext;

    public RecyclerViewAdapter(ArrayList<String> announcements, ArrayList<String> images, Context context){
        appAnnouncements = announcements;
        appContext = context;
        appImages = images;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG,"onBindViewHolder: called.");
        viewHolder.text.setText(appAnnouncements.get(i));
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on some text");
                Glide.with(appContext)
                        .asBitmap()
                        .load(appImages.get(i))
                        .into(viewHolder.image);
                viewHolder.text.setText(appAnnouncements.get(i));

                viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG,"onClick:clicked on: "+ appAnnouncements.get(i));
                        Toast.makeText(appContext, "this is happening", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return appAnnouncements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView text;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.announcement);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
