package com.example.android.blndin.Features.MyHangouts;


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
import com.example.android.blndin.Features.HangoutProfile.HangoutProfileFragment;
import com.example.android.blndin.Features.MyHangouts.Presenter.MyHangoutsPresenter;
import com.example.android.blndin.Features.MyHangouts.View.MyHangoutsView;
import com.example.android.blndin.Models.MyHangoutModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyHangoutFragment extends Fragment implements MyHangoutsItemClickListener, MyHangoutsView {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    MyHangoutsPresenter presenter;
    ArrayList<MyHangoutModel> models;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_hangout, container, false);
        String token = SharedPreferencesHelper.retrieveDataFromSharedPref(getContext(), "token");
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_myhangout);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        models = new ArrayList<>();
        adapter = new MyHangoutAdapter(getContext(), this, models);
        recyclerView.setAdapter(adapter);
        presenter = new MyHangoutsPresenterImp(this);
        presenter.getHangouts(token);
        return view;
    }

    @Override
    public void onHangoutClickListener(int pos, MyHangoutModel item, ImageView shareImageView, String hangout_id, String image_url) {
        Fragment hangoutProfile = HangoutProfileFragment.newInstance(item, ViewCompat.getTransitionName(shareImageView), hangout_id, image_url);
        getFragmentManager()
                .beginTransaction()
                .setTransition(R.transition.change_image_transform)
                .addSharedElement(shareImageView, ViewCompat.getTransitionName(shareImageView))
                .addToBackStack(TAG)
                .replace(R.id.container, hangoutProfile)
                .commit();
    }

    @Override
    public void bindHangouts(ArrayList<MyHangoutModel> hangoutModels) {
        models = hangoutModels;
        adapter = new MyHangoutAdapter(getContext(), this, models);
        recyclerView.setAdapter(adapter);
    }
}
