package com.example.android.blndin.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blndin.Adapters.HangoutMembersAdapter;
import com.example.android.blndin.Models.HangoutMemberModel;
import com.example.android.blndin.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileDetailsFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<HangoutMemberModel> models;
    HangoutMembersAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_hangout_profile_details, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.hangoutDetails_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<HangoutMemberModel> models = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            models.add(new HangoutMemberModel("Omar ELrayes"));
        }
        adapter = new HangoutMembersAdapter(getContext(), models);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }
}
