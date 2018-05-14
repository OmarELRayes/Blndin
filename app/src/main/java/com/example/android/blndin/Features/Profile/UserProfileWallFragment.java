package com.example.android.blndin.Features.Profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blndin.Features.Newsfeed.NewsfeedAdapter;
import com.example.android.blndin.Features.Profile.View.ProfilePostsView;
import com.example.android.blndin.Models.NewsfeedModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileWallFragment extends Fragment implements ProfilePostsView {
    @BindView(R.id.user_profile_wall_recyclerview)RecyclerView recyclerView;

    RecyclerView.Adapter adapter;
    ArrayList<NewsfeedModel> models;
    LinearLayoutManager layoutManager;
    ProfilePostsPresenterImp presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile_wall, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        models = new ArrayList<>();
        adapter = new NewsfeedAdapter(models, getActivity(), false);
        recyclerView.setAdapter(adapter);
        presenter=new ProfilePostsPresenterImp(this,adapter,models,getActivity());
        presenter.getPostsByPage(Constants.TOKEN_1);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    presenter.getPostsByPage(Constants.TOKEN);
                }
            }
        });
    }

    @Override
    public void successfullResponse(String message) {

    }

    @Override
    public void failureResponse(String message) {

    }
}
