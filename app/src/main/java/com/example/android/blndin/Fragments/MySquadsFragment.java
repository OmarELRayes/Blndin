package com.example.android.blndin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blndin.Adapters.MyHangoutAdapter;
import com.example.android.blndin.Adapters.MySquadsAdapter;
import com.example.android.blndin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySquadsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    GridLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_my_squads, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.recycler_mysquads);
        layoutManager =new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MySquadsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        return v;
    }

}
