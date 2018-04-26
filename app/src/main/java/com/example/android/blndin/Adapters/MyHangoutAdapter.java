package com.example.android.blndin.Adapters;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.blndin.Models.MyHangoutModel;
import com.example.android.blndin.MyHangoutsItemClickListener;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * Created by Luffy on 4/18/2018.
 */

public class MyHangoutAdapter extends RecyclerView.Adapter<MyHangoutAdapter.ViewHolder> {

    private final MyHangoutsItemClickListener listener;
    private ArrayList<MyHangoutModel> models;

    public MyHangoutAdapter(MyHangoutsItemClickListener listener, ArrayList<MyHangoutModel> models) {
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

        holder.imageView.setImageResource(R.drawable.kappa2);
        ViewCompat.setTransitionName(holder.imageView, Integer.toString(item.getId()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHangoutClickListener(holder.getAdapterPosition(), item, holder.imageView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.myhangouts_item_image);
        }
    }
}
