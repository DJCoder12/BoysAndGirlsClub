package com.teamshark.boysandgirlsclubevents.MemberOfMonth;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamshark.boysandgirlsclubevents.R;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    public List<MemberMonth> mMembers;

    public MemberAdapter(List<MemberMonth> members) {
        mMembers = members;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView memberName;
        public TextView memberClubhouse;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            cardView = itemLayoutView.findViewById(R.id.card_view);
            memberClubhouse = itemLayoutView.findViewById(R.id.memberClubhouse);
            memberName = itemLayoutView.findViewById(R.id.memberName);
        }
    }

    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.member_month_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MemberAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.memberName.setText(mMembers.get(position).getName());
        viewHolder.memberClubhouse.setText(mMembers.get(position).getClubhouse());
    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    public void removeItem(int position){
        mMembers.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void clear()
    {
        final int size = mMembers.size();
        mMembers.clear();
        notifyItemRangeRemoved(0, size);
    }

}
