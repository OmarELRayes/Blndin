package com.example.android.blndin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.android.blndin.Models.SquadChatModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * Created by Luffy on 4/19/2018.
 */

public class SquadProfileChatAdapter extends BaseAdapter {
    ArrayList<SquadChatModel> models;
    Context context;

    public SquadProfileChatAdapter(ArrayList<SquadChatModel> models, Context context) {
        this.models = models;
        this.context = context;
    }


    public void add_chat_item(SquadChatModel message) {
        this.models.add(message);
        notifyDataSetChanged(); // to render the list we need to notify
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        SquadChatModel message = models.get(position);
        if(message.getOwner())
        {
            convertView = messageInflater.inflate(R.layout.item_chat_bubble_me, null);
            holder.messagetv = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messagetv.setText(message.getText());
        }
        else
        {
            convertView = messageInflater.inflate(R.layout.item_chat_bubble_others, null);
            holder.messagetv = (TextView) convertView.findViewById(R.id.message_body);
            holder.nametv=(TextView)convertView.findViewById(R.id.message_name_others);
            convertView.setTag(holder);
            holder.messagetv.setText(message.getText());
            holder.nametv.setText(message.getName());
        }
        return convertView;
    }

     class ViewHolder  {
         TextView messagetv;
        TextView nametv;
    }
}
