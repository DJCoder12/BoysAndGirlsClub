package com.example.boysandgirlsclubevents.Announcements;

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
import com.example.boysandgirlsclubevents.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnnouncementsRecyclerViewAdapter extends RecyclerView.Adapter<AnnouncementsRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerView";
    private  ArrayList<Announcement> allAppAnnouncements = new ArrayList<Announcement>();
    private Context appContext;

    public AnnouncementsRecyclerViewAdapter(ArrayList<Announcement> announcements, Context context){
        allAppAnnouncements = announcements;
        appContext = context;
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
        viewHolder.text.setText(allAppAnnouncements.get(i).getBody());
        viewHolder.title.setText(allAppAnnouncements.get(i).getTitle());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on some text");
                Glide.with(appContext)
                        .asBitmap()
                        .load(allAppAnnouncements.get(i).getImage())
                        .into(viewHolder.image);
                viewHolder.text.setText(allAppAnnouncements.get(i).getBody());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return allAppAnnouncements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView text;
        TextView title;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.Body);
            title = itemView.findViewById(R.id.Title);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
