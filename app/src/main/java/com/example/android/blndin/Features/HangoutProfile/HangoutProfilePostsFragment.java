package com.example.android.blndin.Features.HangoutProfile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.blndin.Features.HangoutProfile.View.HangoutPostsView;
import com.example.android.blndin.Models.PostModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.Constants;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfilePostsFragment extends Fragment implements HangoutPostsView {

    @BindView(R.id.hangoutProfilePosts_recyclerView)RecyclerView recyclerView;

    RecyclerView.Adapter adapter;
    ArrayList<PostModel> models;
    LinearLayoutManager layoutManager;
    HangoutPostsPresenterImp presenter;
    String hangout_id;
    boolean isBottomReached;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_hangout_profile_posts, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hangout_id=getArguments().getString("hangout_id");
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        models = new ArrayList<>();
        adapter = new HangoutPostsAdapter(models, getActivity());
        recyclerView.setAdapter(adapter);
        presenter =new HangoutPostsPresenterImp(getActivity(),this,adapter,models);
        presenter.getPostsByPage(SharedPreferencesHelper.retrieveDataFromSharedPref(getActivity(),"token"),hangout_id);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1))
                {
                    presenter.getPostsByPage(SharedPreferencesHelper.retrieveDataFromSharedPref(getActivity(),"token"),hangout_id);
                }

//                isBottomReached=recyclerView.canScrollVertically(1);
//                if(isBottomReached) Toast.makeText(getActivity(),"Bottom",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void successfulResponsePosts(String message) {

    }

    @Override
    public void failureResponsePosts(String message) {

    }
}
