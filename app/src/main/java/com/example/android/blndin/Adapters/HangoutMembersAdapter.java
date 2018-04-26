package com.example.android.blndin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.blndin.Models.HangoutMemberModel;
import com.example.android.blndin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 4/26/2018.
 */

public class HangoutMembersAdapter extends RecyclerView.Adapter<HangoutMembersAdapter.ViewHolder> {

    Context context;
    List<HangoutMemberModel> hangoutModels;

    public HangoutMembersAdapter(Context context, List<HangoutMemberModel> hangoutModels) {
        this.context = context;
        this.hangoutModels = hangoutModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hangout_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HangoutMembersAdapter.ViewHolder holder, int position) {
        HangoutMemberModel model = hangoutModels.get(position);
        holder.avatar.setImageResource(R.drawable.user);
        holder.userName.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return hangoutModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView close;
        CircleImageView avatar;
        TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);
            close = (ImageView) itemView.findViewById(R.id.hangoutDetails_member_close);
            avatar = (CircleImageView) itemView.findViewById(R.id.hangoutDetails_member_img);
            userName = (TextView) itemView.findViewById(R.id.hangoutDetails_member_name);
        }
    }
}
