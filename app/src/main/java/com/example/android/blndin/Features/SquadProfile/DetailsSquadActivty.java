package com.example.android.blndin.Features.SquadProfile;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.blndin.Adapters.SquadNewsAdapter;
import com.example.android.blndin.Adapters.SquadProfileMembersAdapter;
import com.example.android.blndin.Features.SquadProfile.Model.SquadProfileDetailsResponse;
import com.example.android.blndin.Features.SquadProfile.Presenter.SquadProfileDetailsPresenter;
import com.example.android.blndin.Features.SquadProfile.View.SquadDetailsView;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsSquadActivty extends AppCompatActivity implements SquadDetailsView {
    RecyclerView recyclerViewMembers,recyclerViewNews;
    RecyclerView.Adapter adapterMembers,adapterNews;
    ArrayList<SquadProfileDetailsResponse.Payload.Squad.Member> squadMemberModels;
    LinearLayoutManager linearLayoutManagerMembers,linearLayoutManagerNews;
    String squad_id, token;

    @BindView(R.id.squadProfile_CollapsingToolbar)
    CollapsingToolbarLayout toolbarLayout;

    @BindView(R.id.squadProfile_image)
    ImageView image;

    @BindView(R.id.squadProfile_admin)
    TextView admin;

    @BindView(R.id.squadProfile_Description)
    TextView desc;

    @BindView(R.id.squadProfile_created_at)
    TextView createdAt;

    SquadProfileDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_squad_activty);
        ButterKnife.bind(this);
        squad_id = getIntent().getExtras().getString("squad_id");
        token = SharedPreferencesHelper.retrieveDataFromSharedPref(this, "token");
        presenter = new SquadProfileDetailsPresenterImp(this);
        init();
    }
    void init(){
        //recycler for members
        recyclerViewMembers = (RecyclerView) findViewById(R.id.recycler_squadmembers);
        linearLayoutManagerMembers = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMembers.setLayoutManager(linearLayoutManagerMembers);
        squadMemberModels = new ArrayList<>();
        adapterMembers = new SquadProfileMembersAdapter(this, squadMemberModels);
        recyclerViewMembers.setAdapter(adapterMembers);
        //recycler for news
        recyclerViewNews = (RecyclerView) findViewById(R.id.recycler_squad_profile_news);
        linearLayoutManagerNews= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewNews.setLayoutManager(linearLayoutManagerNews);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewNews.getContext(),
                linearLayoutManagerNews.getOrientation());
        recyclerViewNews.addItemDecoration(dividerItemDecoration);
        adapterNews=new SquadNewsAdapter();
        recyclerViewNews.setAdapter(adapterNews);
        presenter.getDetails(token, squad_id);
    }

    @Override
    public void bindMembers(ArrayList<SquadProfileDetailsResponse.Payload.Squad.Member> members) {
        squadMemberModels = members;
        adapterMembers = new SquadProfileMembersAdapter(this, squadMemberModels);
        recyclerViewMembers.setAdapter(adapterMembers);
    }

    @Override
    public void bindDetails(String image, String title, String desc, String admin, String createdAt) {
        toolbarLayout.setTitle(title);
        Glide.with(this)
                .load(image).error(R.drawable.kappa2)
                .into(this.image);
        this.admin.setText(admin);
        this.desc.setText(desc);
        this.createdAt.setText(createdAt);
    }

    @Override
    public void bindNews() {

    }
}
