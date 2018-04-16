package com.example.android.blndin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.blndin.Models.NewsfeedModel;
import com.example.android.blndin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luffy on 4/16/2018.
 */

public class NewsfeedAdapter  extends RecyclerView.Adapter<NewsfeedAdapter.ViewHolder>{
    ArrayList<NewsfeedModel> newsfeedItemsList;
     Context context;
    public NewsfeedAdapter(ArrayList<NewsfeedModel> newsfeedItemsList, Context context) {
        this.newsfeedItemsList = newsfeedItemsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newsfeed,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
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

    }

    @Override
    public int getItemCount() {
        return newsfeedItemsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView like_iv;
        public ViewHolder(View itemView) {
            super(itemView);
            like_iv=(ImageView)itemView.findViewById(R.id.like_iv);
        }
    }
}
