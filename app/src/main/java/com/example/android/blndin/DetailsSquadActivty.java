package com.example.android.blndin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.blndin.Adapters.SquadNewsAdapter;
import com.example.android.blndin.Adapters.SquadProfileMembersAdapter;

public class DetailsSquadActivty extends AppCompatActivity {
    RecyclerView recyclerViewMembers,recyclerViewNews;
    RecyclerView.Adapter adapterMembers,adapterNews;
    LinearLayoutManager linearLayoutManagerMembers,linearLayoutManagerNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_squad_activty);
        init();
    }
    void init(){
        //recycler for members
        recyclerViewMembers = (RecyclerView) findViewById(R.id.recycler_squadmembers);
        linearLayoutManagerMembers = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMembers.setLayoutManager(linearLayoutManagerMembers);
        adapterMembers = new SquadProfileMembersAdapter();
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
    }
}
