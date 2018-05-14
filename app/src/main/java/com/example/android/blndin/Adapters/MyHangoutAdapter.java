package com.example.android.blndin.Adapters;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.blndin.Features.MyHangouts.MyHangoutsItemClickListener;
import com.example.android.blndin.Models.MyHangoutModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * Created by Luffy on 4/18/2018.
 */

public class MyHangoutAdapter extends RecyclerView.Adapter<MyHangoutAdapter.ViewHolder> {

    private final MyHangoutsItemClickListener listener;
    Context context;
    private ArrayList<MyHangoutModel> models;

    public MyHangoutAdapter(Context context, MyHangoutsItemClickListener listener, ArrayList<MyHangoutModel> models) {
        this.context = context;
        this.listener = listener;
        this.models = models;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myhangouts,parent,false);
        return new MyHangoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MyHangoutModel item = models.get(position);
        Glide.with(context).
                load(item.getImage()).error(R.drawable.kappa2).
                into(holder.imageView);
        ViewCompat.setTransitionName(holder.imageView, item.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHangoutClickListener(holder.getAdapterPosition(), item, holder.imageView, item.getId(), item.getImage());
            }
        });
        holder.address.setText(item.getAddress());
        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView address;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.myhangouts_item_image);
            title = (TextView) itemView.findViewById(R.id.myhangouts_item_title);
            address = (TextView) itemView.findViewById(R.id.myhangouts_item_address);
        }
    }
}
