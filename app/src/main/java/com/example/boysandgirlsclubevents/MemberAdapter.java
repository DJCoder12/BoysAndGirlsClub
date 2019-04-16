package com.example.boysandgirlsclubevents;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    public MemberMonth member;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public TextView memberName;
        public TextView memberClubhouse;

        public ViewHolder(View itemLayoutView){
            super(itemLayoutView);
            cardView = itemLayoutView.findViewById(R.id.card_view);
            memberClubhouse = itemLayoutView.findViewById(R.id.memberClubhouse);
            memberName = itemLayoutView.findViewById(R.id.memberName);
        }
    }

    public List<MemberMonth> mMembers;

    public MemberAdapter(List<MemberMonth> members){
        mMembers = members;
    }

    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MemberAdapter.ViewHolder viewHolder, int position){
        viewHolder.memberName.setText(mMembers.get(position).getMemberName());
        viewHolder.memberClubhouse.setText(mMembers.get(position).getMemberClubhouse());
    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
