package com.example.android.blndin.Features.HangoutProfile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.blndin.Adapters.HangoutMembersAdapter;
import com.example.android.blndin.Features.HangoutProfile.Presenter.HangoutDetailsPresenter;
import com.example.android.blndin.Features.HangoutProfile.View.HangoutDetailsView;
import com.example.android.blndin.Models.HangoutMemberModel;
import com.example.android.blndin.Models.HangoutModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileDetailsFragment extends Fragment implements HangoutDetailsView {


    HangoutMembersAdapter adapter;
    LinearLayoutManager layoutManager;
    @BindView(R.id.hangoutDetails_activity)
    TextView activity;
    @BindView(R.id.hangoutDetails_desc)
    TextView desc;
    @BindView(R.id.hangoutDetails_time)
    TextView time;
    @BindView(R.id.hangoutDetails_locationName)
    TextView location;
    @BindView(R.id.hangoutDetails_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.create_squad_iv)ImageView create_squad_iv;
    HangoutDetailsPresenter presenter;
    String hangout_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_hangout_profile_details, container, false);
        ButterKnife.bind(this, root);
        hangout_id=getArguments().getString("hangout_id");
        presenter = new HangoutDetailsPresenterImp(this);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.getHangoutDetails(hangout_id, getContext());
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<HangoutMemberModel> models = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            models.add(new HangoutMemberModel("Omar ELrayes"));
        }
        adapter = new HangoutMembersAdapter(getContext(), models);
        recyclerView.setAdapter(adapter);
        create_squad_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CreateSquadActivity.class);
                intent.putExtra("hangout_id",hangout_id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void updateUi(final HangoutModel hangout) {
        activity.setText(hangout.getActivity());
        desc.setText(hangout.getTitle());
        time.setText(hangout.getTime());
        location.setText(hangout.getLocation());

    }

    @Override
    public void failure(String message) {

    }


}
