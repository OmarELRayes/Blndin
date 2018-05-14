package com.example.android.blndin.Features.MySquads;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.blndin.Adapters.MySquadsAdapter;
import com.example.android.blndin.Features.MySquads.Model.MySquadsModel;
import com.example.android.blndin.Features.MySquads.Presenter.MySquadsPresenter;
import com.example.android.blndin.Features.MySquads.View.MySquadsView;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySquadsFragment extends Fragment implements MySquadsView {
    @BindView(R.id.recycler_mysquads)
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    GridLayoutManager layoutManager;
    ArrayList<MySquadsModel> models;
    @BindView(R.id.mysquads_create_squad)
    FloatingActionButton fab;
    String token;
    MySquadsPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_squads, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        token = SharedPreferencesHelper.retrieveDataFromSharedPref(getContext(), "token");
        presenter = new MySquadsPresenterImp(this);
        layoutManager =new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        models = new ArrayList<>();
        adapter = new MySquadsAdapter(getContext(), models);
        recyclerView.setAdapter(adapter);
        fab = (FloatingActionButton) fab.findViewById(R.id.mysquads_create_squad);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        presenter.getMySquads(token);
    }

    @Override
    public void bindMySquads(ArrayList<MySquadsModel> models) {
        this.models = models;
        adapter = new MySquadsAdapter(getContext(), this.models);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void success(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
