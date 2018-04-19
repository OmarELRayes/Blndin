package com.example.android.blndin.Fragments;


import android.os.Bundle;
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

import com.example.android.blndin.Adapters.SquadProfileMembersAdapter;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SquadProfileFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_squad_profile, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_squadmembers);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new SquadProfileMembersAdapter();
        recyclerView.setAdapter(adapter);
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.squadProfile_viewpager);
        PagerAdapter pagerAdapter = new SquadProfileAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        TabLayout tableLayout = (TabLayout) v.findViewById(R.id.squadProfile_tabs);
        tableLayout.setupWithViewPager(viewPager);
        return v;
    }
    private class SquadProfileAdapter extends FragmentPagerAdapter {


        public SquadProfileAdapter(FragmentManager fm) {
            super(fm);

        }
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0)
            {
                return new SquadProfileDetailsFragment();
            }
            else {
                return  new SquadProfileDetailsFragment();
            }

        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Details";
                case 1:
                    return "Chat";
                default:
                    return "Blndin";
            }
        }
    }
}
