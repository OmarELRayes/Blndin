package com.example.android.blndin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.blndin.Adapters.MyHangoutAdapter;
import com.example.android.blndin.Adapters.SquadProfileChatAdapter;
import com.example.android.blndin.Models.SquadChatModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SquadProfileChatFragment extends Fragment {
    LinearLayout writing_layout;
    LinearLayout standing_layout;
    EditText writing_et,standing_et;
    String temp;
    TextView send;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    ArrayList<SquadChatModel> models;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_squad_profile_chat, container, false);
        init(v);
        return v;
    }
    void init(View v)
    {
        models=new ArrayList<>();
        send=(TextView)v.findViewById(R.id.tv_squad_chat_send);
        writing_layout=(LinearLayout)v.findViewById(R.id.squadchat_writing_layout);
        standing_layout=(LinearLayout)v.findViewById(R.id.squadchat_standing_layout);
        writing_et=(EditText)v.findViewById(R.id.et_writing_squad);
        standing_et=(EditText)v.findViewById(R.id.et_standing_squad);
        standing_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0)
                {
                    temp=s.toString();
                    writing_layout.setVisibility(View.VISIBLE);
                    standing_layout.setVisibility(View.GONE);
                    writing_et.setText(temp);
                   // Selection.setSelection((Editable) writing_et.getText(),writing_et.getSelectionEnd());
                    writing_et.requestFocus();
                    writing_et.setSelection(writing_et.getText().length());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        writing_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0)
                {
                    temp="";
                    writing_layout.setVisibility(View.GONE);
                    standing_layout.setVisibility(View.VISIBLE);
                    standing_et.setText(temp);
                   // Selection.setSelection((Editable) standing_et.getText(),standing_et.getSelectionStart());
                    standing_et.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        recyclerView=(RecyclerView)v.findViewById(R.id.recycler_squad_profile_chat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        for(int i=0;i<10;i++)
            models.add(new SquadChatModel("hollaaaaa",false));
        adapter=new SquadProfileChatAdapter(models,getContext());
        recyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                models.add(new SquadChatModel(writing_et.getText().toString(),true));
                adapter.notifyItemInserted(models.size()-1);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
