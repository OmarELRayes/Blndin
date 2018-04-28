package com.example.android.blndin.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blndin.Adapters.NewsfeedAdapter;
import com.example.android.blndin.Models.NewsfeedModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsfeedWallFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<NewsfeedModel> models;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newsfeed_wall, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.newsfeed_wall_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        models = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            models.add(new NewsfeedModel("Omar ELRayes", false));
        adapter = new NewsfeedAdapter(models, getActivity());
        recyclerView.setAdapter(adapter);
    }
}
