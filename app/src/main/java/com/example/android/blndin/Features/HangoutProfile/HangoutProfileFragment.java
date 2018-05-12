package com.example.android.blndin.Features.HangoutProfile;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.blndin.Models.MyHangoutModel;
import com.example.android.blndin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private static final String EXTRA_TRANSITION_NAME = "transition_name";
    public static AppBarLayout appBarLayout;
    String hangout_id="15";
    public HangoutProfileFragment() {
        // Required empty public constructor
    }

    public static HangoutProfileFragment newInstance(MyHangoutModel item, String transitionName) {
        HangoutProfileFragment fragment = new HangoutProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TRANSITION_NAME, transitionName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hangout_profile, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String transitionName = getArguments().getString(EXTRA_TRANSITION_NAME);
        ImageView imageView = (ImageView) view.findViewById(R.id.hangoutProfile_Image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(transitionName);
        }
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.hangoutProfile_viewpager);
        PagerAdapter pagerAdapter = new HangoutTabsAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        final TabLayout tableLayout = (TabLayout) view.findViewById(R.id.hangoutProfile_tabs);
        tableLayout.setupWithViewPager(viewPager);
        tableLayout.addOnTabSelectedListener(this);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.hangoutProfile_appbar);
        startPostponedEnterTransition();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getText().equals("Chat")) {
            appBarLayout.setExpanded(false, true);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (tab.getText().equals("Chat")) {
            appBarLayout.setExpanded(true, true);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if (tab.getText().equals("Chat")) {
            appBarLayout.setExpanded(false, true);
        }
    }

    class HangoutTabsAdapter extends FragmentPagerAdapter {

        public HangoutTabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HangoutProfileDetailsFragment();
                case 1:
                    HangoutProfilePostsFragment fragment=new HangoutProfilePostsFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("hangout_id",hangout_id);
                    fragment.setArguments(bundle);
                    return fragment;
                case 2:
                    return new HangoutProfileChatFragment();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Details";
                case 1:
                    return "Posts";
                case 2:
                    return "Chat";
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
