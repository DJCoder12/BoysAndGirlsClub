package com.example.boysandgirlsclubevents;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter {
    private String[] mDataset1;
    private int[] mDataset2;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mItemText;
        public ImageView mItemImage;

        public ViewHolder(View itemLayoutView){
            super(itemLayoutView);
            mItemText = itemLayoutView.findViewById(R.id.itemText);
            mItemImage = itemLayoutView.findViewById(R.id.itemImage);
        }
    }

    public ListAdapter(String[] myDataset1, int[] myDataset2){
        mDataset1 = myDataset1;
        mDataset2 = myDataset2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemLayoutView);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        ViewHolder newHolder = (ViewHolder) viewHolder;

        newHolder.mItemText.setText(mDataset1[position]);
        newHolder.mItemImage.setImageResource(mDataset2[position]);

    }

    @Override
    public int getItemCount() {
        return mDataset1.length;
    }

}
