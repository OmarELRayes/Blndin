package com.example.android.blndin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.blndin.Models.NewsfeedModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * Created by Luffy on 4/16/2018.
 */

public class NewsfeedAdapter  extends RecyclerView.Adapter<NewsfeedAdapter.ViewHolder>{

    ArrayList<NewsfeedModel> newsfeedItemsList;
    Context context;
    boolean foreigner = true;

    public NewsfeedAdapter(ArrayList<NewsfeedModel> newsfeedItemsList, Context context) {
        this.newsfeedItemsList = newsfeedItemsList;
        this.context = context;
    }

    public NewsfeedAdapter(ArrayList<NewsfeedModel> newsfeedItemsList, Context context, boolean foreigner) {
        this.newsfeedItemsList = newsfeedItemsList;
        this.context = context;
        this.foreigner = foreigner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newsfeed,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        NewsfeedModel item = newsfeedItemsList.get(position);
        holder.like_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newsfeedItemsList.get(position).getLiked())
                {
                    holder.like_iv.setImageResource(R.drawable.like);
                    newsfeedItemsList.get(position).setLiked(false);
                }
                else{
                    holder.like_iv.setImageResource(R.drawable.fill_like);
                    newsfeedItemsList.get(position).setLiked(true);
                }
            }
        });
        if (!foreigner) {
            holder.hangoutSpan.setVisibility(View.GONE);
        }
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return newsfeedItemsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView like_iv;
        TextView name;
        TextView location;
        LinearLayout hangoutSpan;
        public ViewHolder(View itemView) {
            super(itemView);
            like_iv=(ImageView)itemView.findViewById(R.id.like_iv);
            name = (TextView) itemView.findViewById(R.id.item_newsfeed_username);
            location = (TextView) itemView.findViewById(R.id.item_newsfeed_location);
            hangoutSpan = (LinearLayout) itemView.findViewById(R.id.item_newsfeed_hangout_span);
        }
    }
}
