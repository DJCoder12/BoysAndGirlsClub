package com.example.boysandgirlsclubevents.Calendar.DailyView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.Calendar.Event;
import com.example.boysandgirlsclubevents.R;

import java.util.List;

public class DailyViewAdapter extends RecyclerView.Adapter<DailyViewAdapter.MyViewHolder>
{
    private List<Event> mDataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v)
        {
            super(v);
            textView = v;
        }
    }

    public DailyViewAdapter(List<Event> dataSet)
    {
        mDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DailyViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        // - get element from your data set at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataSet.get(position).getTitle());

    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mDataSet.size();
    }
}
