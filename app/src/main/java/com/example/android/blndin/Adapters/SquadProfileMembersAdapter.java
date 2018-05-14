package com.example.android.blndin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.blndin.Features.SquadProfile.Model.SquadProfileDetailsResponse;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * Created by Luffy on 4/19/2018.
 */

public class SquadProfileMembersAdapter extends RecyclerView.Adapter<SquadProfileMembersAdapter.ViewHolder> {
    Context context;
    ArrayList<SquadProfileDetailsResponse.Payload.Squad.Member> models;

    public SquadProfileMembersAdapter(Context context, ArrayList<SquadProfileDetailsResponse.Payload.Squad.Member> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_squadprofilemember,parent,false);
        return new SquadProfileMembersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SquadProfileDetailsResponse.Payload.Squad.Member model = models.get(position);
        Glide.with(context).load(model.getImage()).error(R.drawable.user);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.squadProfile_memberImage);
        }
    }
}
