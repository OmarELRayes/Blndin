package com.example.android.blndin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blndin.Adapters.NewsfeedAdapter;
import com.example.android.blndin.Models.NewsfeedModel;
import com.example.android.blndin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsfeedFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<NewsfeedModel> models;
    public NewsfeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_newsfeed, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.recycler_newsfeed);
        models=new ArrayList<>();
        for(int i=0;i<5;i++)
            models.add(new NewsfeedModel("Mostafa Bder",true));
        adapter=new NewsfeedAdapter(models,getActivity());
        recyclerView.setAdapter(adapter);
        return v;
    }

}
