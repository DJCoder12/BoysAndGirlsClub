package com.example.boysandgirlsclubevents.Calendar.DailyView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.boysandgirlsclubevents.Calendar.Event;
import com.example.boysandgirlsclubevents.R;

import java.util.List;

public class DailyViewAdapter extends RecyclerView.Adapter<DailyViewAdapter.ViewHolder>
{
    private List<Event> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View baseView;
        public TextView titleText;
        public TextView timeText;
        public ImageView iconImage;

        public ViewHolder(View view)
        {
            super(view);
            baseView = view;
            titleText = view.findViewById(R.id.tv_title_eventItem);
            timeText = view.findViewById(R.id.tv_time_eventItem);
            iconImage = view.findViewById(R.id.iv_icon_eventItem);
        }
    }

    public DailyViewAdapter(List<Event> dataSet)
    {
        mDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View eventView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new ViewHolder(eventView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        //get event at this position and populate with event specific data
        Event curEvent = mDataSet.get(position);
        holder.titleText.setText(curEvent.getTitle());
        holder.timeText.setText(curEvent.getStartTimeString() + " to " + curEvent.getEndTimeString());

        //Load the image using glide, a library
        Glide.with(holder.baseView)
             .load(curEvent.getIcon())
             .into(holder.iconImage);
    }

    @Override
    public int getItemCount()
    {
        return mDataSet.size();
    }
}
