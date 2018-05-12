package com.example.android.blndin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.blndin.Models.UserModel;
import com.example.android.blndin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 5/8/2018.
 */

public class HangoutInviteMembersAdapter extends RecyclerView.Adapter<HangoutInviteMembersAdapter.ViewHolder> {
    Context context;
    List<UserModel> members;


    public HangoutInviteMembersAdapter(Context context, List<UserModel> members) {
        this.context = context;
        this.members = members;
    }

    @Override
    public HangoutInviteMembersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_map_member, parent, false);
        return new HangoutInviteMembersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HangoutInviteMembersAdapter.ViewHolder holder, final int position) {

        holder.userName.setText(members.get(position).getUsername());
        holder.avatar.setImageResource(R.drawable.user);
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                members.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, members.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView close;
        CircleImageView avatar;
        TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);
            close = (ImageView) itemView.findViewById(R.id.item_map_member_cancel);
            avatar = (CircleImageView) itemView.findViewById(R.id.item_map_member_image);
            userName = (TextView) itemView.findViewById(R.id.item_map_member_name);
        }
    }

}
