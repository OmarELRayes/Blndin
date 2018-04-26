package com.example.android.blndin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android.blndin.ChatSquadActivity;
import com.example.android.blndin.R;

/**
 * Created by Luffy on 4/19/2018.
 */

public class MySquadsAdapter extends RecyclerView.Adapter<MySquadsAdapter.ViewHolder> {

    Context context;

    public MySquadsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_squads,parent,false);
        return new MySquadsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_squad_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment fragment = new SquadProfileChatFragment();
//                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.container, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                context.startActivity(new Intent(context, ChatSquadActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item_squad_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            item_squad_layout=(LinearLayout)itemView.findViewById(R.id.item_squad_layout);
        }
    }
}
