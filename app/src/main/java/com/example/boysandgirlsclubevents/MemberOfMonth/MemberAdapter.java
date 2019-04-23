package com.example.boysandgirlsclubevents.MemberOfMonth;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.R;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    public MemberMonth member;
    private FirebaseMemberOfTheMonth mFirestoreDB = FirebaseMemberOfTheMonth.getInstance();
    private RecyclerViewClickListener newListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;

        CardView cardView;
        public TextView memberName;
        public TextView memberClubhouse;

        public ViewHolder(View itemLayoutView, RecyclerViewClickListener listener) {
            super(itemLayoutView);
            mListener = listener;
            cardView = itemLayoutView.findViewById(R.id.card_view);
            memberClubhouse = itemLayoutView.findViewById(R.id.memberClubhouse);
            memberName = itemLayoutView.findViewById(R.id.memberName);

            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }

    public List<MemberMonth> mMembers;

    public MemberAdapter(List<MemberMonth> members, RecyclerViewClickListener listener) {
        mMembers = members;
        newListener = listener;
    }

    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView, newListener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MemberAdapter.ViewHolder viewHolder, int position) {
        viewHolder.memberName.setText(mMembers.get(position).getName());
        viewHolder.memberClubhouse.setText(mMembers.get(position).getClubhouse());
    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /*
    public int getItemViewType(int position){
        return (position == mMembers.size()) ? R.layout.fragment_member_month.u
    }

    @Override
    public int getItemViewType(int position){
        return (position == mMembers.size())
    }
    */
}
