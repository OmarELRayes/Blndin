package com.example.android.blndin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.blndin.Adapters.NewsfeedAdapter;
import com.example.android.blndin.Models.NewsfeedModel;

import java.util.ArrayList;

public class test_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<NewsfeedModel> models;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_newsfeed);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(test_activity.this);
        recyclerView.setLayoutManager(layoutManager);
        models=new ArrayList<>();
        for(int i=0;i<5;i++)
            models.add(new NewsfeedModel("Mostafa Bder",true));
        adapter=new NewsfeedAdapter(models,test_activity.this);
        recyclerView.setAdapter(adapter);
    }
}
