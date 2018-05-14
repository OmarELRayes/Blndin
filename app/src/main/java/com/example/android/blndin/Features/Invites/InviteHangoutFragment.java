package com.example.android.blndin.Features.Invites;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.blndin.Features.Invites.View.InviteHangoutView;
import com.example.android.blndin.Models.InviteHangoutModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InviteHangoutFragment extends Fragment implements InviteHangoutView {
    @BindView(R.id.invitation_hangouts_recyclerView) RecyclerView recyclerView;

    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    ArrayList<InviteHangoutModel>models;
    InviteHangoutPresenterImp presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_hangout_invitations, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        models=new ArrayList<>();
        adapter=new InvitationHangoutAdapter(getActivity(),models);
        recyclerView.setAdapter(adapter);
        presenter=new InviteHangoutPresenterImp(getActivity(),this,adapter,models);
        presenter.getInvitesHangoutByPage(Constants.TOKEN_1);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    presenter.getInvitesHangoutByPage(Constants.TOKEN_1);
                }
            }
        });
    }

    @Override
    public void successfullResponse(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureResponse(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
