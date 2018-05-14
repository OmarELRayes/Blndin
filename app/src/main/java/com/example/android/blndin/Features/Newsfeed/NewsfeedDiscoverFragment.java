package com.example.android.blndin.Features.Newsfeed;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.blndin.Features.Newsfeed.view.NewsfeedView;
import com.example.android.blndin.Models.NewsfeedModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.Constants;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsfeedDiscoverFragment extends Fragment implements NewsfeedView {


    RecyclerView.Adapter adapter;
    ArrayList<NewsfeedModel> models;
    LinearLayoutManager layoutManager;
    NewsfeedPresenterImp presenter;
    @BindView(R.id.newsfeed_discover_recyclerView) RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newsfeed_discover, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        models=new ArrayList<>();
        adapter = new NewsfeedAdapter(models, getActivity(),false);
        recyclerView.setAdapter(adapter);
        presenter=new NewsfeedPresenterImp(getActivity(),adapter,this,models);
        presenter.getNewsfeedsDiscoverByPage(SharedPreferencesHelper.retrieveDataFromSharedPref(getActivity(),"token"));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    presenter.getNewsfeedsDiscoverByPage(SharedPreferencesHelper.retrieveDataFromSharedPref(getActivity(),"token"));
                }
            }
        });
    }

    @Override
    public void successfulResponse(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureResponse(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
