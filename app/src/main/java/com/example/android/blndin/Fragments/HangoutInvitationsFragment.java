package com.example.android.blndin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blndin.Adapters.InvitationHangoutAdapter;
import com.example.android.blndin.Adapters.NewsfeedAdapter;
import com.example.android.blndin.Models.NewsfeedModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutInvitationsFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_hangout_invitations, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.invitation_hangouts_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new InvitationHangoutAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

}
