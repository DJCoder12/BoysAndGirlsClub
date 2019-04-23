package com.teamshark.boysandgirlsclubevents.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.teamshark.boysandgirlsclubevents.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>
{

    public static final String TAG = "EventAdapter";

    private List<Event> mDataSet;
    private Context mContext;
    private Size mSize;

    public enum Size
    {
        large,
        medium,
        small
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View baseView;
        public TextView titleText;
        public TextView timeText;
        public TextView ageText;
        public ImageView iconImage;

        public ViewHolder(View view)
        {
            super(view);
            baseView = view;
            titleText = view.findViewById(R.id.tv_title_eventItem);
            timeText = view.findViewById(R.id.tv_time_eventItem);
            ageText = view.findViewById(R.id.tv_ageGroup_eventItem);
            iconImage = view.findViewById(R.id.iv_icon_eventItem);
        }
    }

    public EventAdapter(List<Event> dataSet, Size size, Context context)
    {
        mDataSet = dataSet;
        mContext = context;
        mSize = size;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View eventView;

        if (mSize == Size.large)
        {
            eventView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item_large, parent, false);
        }
        else if (mSize == Size.medium)
        {
            eventView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item_medium, parent, false);
        }
        else
        {
            eventView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item_small, parent, false);
        }

        return new ViewHolder(eventView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        if (mDataSet != null)
        {
            //get event at this position and populate with event specific data
            final Event curEvent = mDataSet.get(position);
            holder.titleText.setText(curEvent.getTitle());

            String timeString = mContext.getResources().getString(
                    R.string.view_daily_time,
                    curEvent.getStartTimeString(),
                    curEvent.getEndTimeString());
            holder.timeText.setText(timeString);

            if (mSize == Size.large)
            {
                String ageString = mContext.getResources().getString(
                        R.string.event_age_group,
                        curEvent.getLowerAge(),
                        curEvent.getUpperAge());
                holder.ageText.setText(ageString);
            }

            //Load the image using glide, a library
            Glide.with(holder.baseView)
                    .load(curEvent.getIconUrl())
                    .into(holder.iconImage);

            Resources res = mContext.getResources();

            switch (curEvent.getColor())
            {
                case Red:
                    holder.baseView.setBackgroundColor(res.getColor(R.color.eventRed));
                    break;
                case Green:
                    holder.baseView.setBackgroundColor(res.getColor(R.color.eventGreen));
                    break;
                case Yellow:
                    holder.baseView.setBackgroundColor(res.getColor(R.color.eventYellow));
                    break;
                case Blue:
                    holder.baseView.setBackgroundColor(res.getColor(R.color.eventBlue));
                    break;
                case Orange:
                    holder.baseView.setBackgroundColor(res.getColor(R.color.eventOrange));
                    break;
                case Purple:
                    holder.baseView.setBackgroundColor(res.getColor(R.color.eventPurple));
                    break;
            }

            holder.baseView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    CalendarSettings.setSelectedEvent(curEvent);
                    Intent detailIntent = new Intent(mContext, EventDetailActivity.class);
                    mContext.startActivity(detailIntent);
                }
            });

            holder.baseView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Delete Event")
                            .setMessage("Are you sure you want to delete this event?")
                            .setPositiveButton(R.string.delete_yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                        ClubCalendar.deleteEvent(curEvent);
                                        Log.d(TAG, "Event deleted.");
                                    } else {
                                        Toast.makeText(mContext, "You must be logged in to modify events.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).setNegativeButton(R.string.delete_no, null).show();
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        if (mDataSet == null)
        {
            return 0;
        }

        return mDataSet.size();
    }
}
