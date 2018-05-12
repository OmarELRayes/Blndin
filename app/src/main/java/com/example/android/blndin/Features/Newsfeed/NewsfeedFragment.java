package com.example.android.blndin.Features.Newsfeed;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blndin.CreatePostActivity;
import com.example.android.blndin.Models.NewsfeedModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsfeedFragment extends Fragment implements FloatingActionButton.OnClickListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<NewsfeedModel> models;
    LinearLayoutManager layoutManager;
    FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_newsfeed, container, false);


        fab = (FloatingActionButton) v.findViewById(R.id.newsfeed_fab);
        fab.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.newsfeed_viewpager);
        PagerAdapter pagerAdapter = new NewsfeedTabsAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        final TabLayout tableLayout = (TabLayout) view.findViewById(R.id.newsfeed_tab);
        tableLayout.setupWithViewPager(viewPager);
        //tableLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), CreatePostActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    class NewsfeedTabsAdapter extends FragmentPagerAdapter {

        public NewsfeedTabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NewsfeedWallFragment();
                case 1:
                    return new NewsfeedDiscoverFragment();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Wall";
                case 1:
                    return "Discover";
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
