package com.example.android.blndin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.blndin.Adapters.MyHangoutAdapter;
import com.example.android.blndin.Models.MyHangoutModel;
import com.example.android.blndin.MyHangoutsItemClickListener;
import com.example.android.blndin.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyHangoutFragment extends Fragment implements MyHangoutsItemClickListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    public MyHangoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_hangout, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_myhangout);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MyHangoutModel model;
        ArrayList<MyHangoutModel> models = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            model = new MyHangoutModel(i, "adsaasda", "sadasdad");
            models.add(model);
        }
        adapter = new MyHangoutAdapter(this, models);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onHangoutClickListener(int pos, MyHangoutModel item, ImageView shareImageView) {
        Fragment hangoutProfile = HangoutProfileFragment.newInstance(item, ViewCompat.getTransitionName(shareImageView));
        getFragmentManager()
                .beginTransaction()
                .setTransition(R.transition.change_image_transform)
                .addSharedElement(shareImageView, ViewCompat.getTransitionName(shareImageView))
                .addToBackStack(TAG)
                .replace(R.id.container, hangoutProfile)
                .commit();
    }
}
