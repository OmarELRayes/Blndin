package com.example.android.blndin.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blndin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvitationsFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_invitations, container, false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.invitations_viewpager);
        PagerAdapter pagerAdapter = new InvitationsTabAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tableLayout = (TabLayout) rootView.findViewById(R.id.invitations_tab);
        tableLayout.setupWithViewPager(viewPager);
        return rootView;

    }
    class InvitationsTabAdapter extends FragmentPagerAdapter {

        public InvitationsTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HangoutInvitationsFragment();
                case 1:
                    return new SquadInvitationsFragment();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Hangouts";
                case 1:
                    return "Squads";
                default:
                    return "BlndIn";
            }
        }

    }
}
