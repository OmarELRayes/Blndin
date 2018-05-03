package com.example.android.blndin.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.blndin.Models.PlaceModel;
import com.example.android.blndin.R;

import java.util.List;

/**
 * Created by LeGenÐ on 5/2/2018.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    Context context;
    List<PlaceModel> placeModels;


    public PlacesAdapter(Context context, List<PlaceModel> placeModels) {
        this.context = context;
        this.placeModels = placeModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlacesAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(R.drawable.kappa2);
    }

    @Override
    public int getItemCount() {
        return placeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.places_img);
            cardView = (CardView) itemView.findViewById(R.id.places_offer);
            cardView.setPreventCornerOverlap(false);
        }
    }

}
