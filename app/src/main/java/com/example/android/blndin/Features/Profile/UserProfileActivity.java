package com.example.android.blndin.Features.Profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.blndin.Features.Profile.Model.ProfileDetailsResponse;
import com.example.android.blndin.Features.Profile.View.ProfileDetailsView;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.Constants;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, TabLayout.OnTabSelectedListener,ProfileDetailsView {

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;
    @BindView(R.id.user_profile_user_image)CircleImageView mProfileImage;
    @BindView(R.id.user_profile_full_name)TextView name;
    @BindView(R.id.user_profile_username)TextView username;
    ProfileDetailsResponse data;
    private int mMaxScrollSize;
    ProfileDetailsPresenterImp presenter;
    public static void start(Context c) {
        c.startActivity(new Intent(c, UserProfileActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        presenter=new ProfileDetailsPresenterImp(this,getApplicationContext());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.user_profile_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.user_profile_viewpager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.user_profile_appbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.user_profile_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        viewPager.setAdapter(new UserProfileTabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_nav_home);
        tabLayout.getTabAt(0).getIcon().setColorFilter(ContextCompat.getColor(this, R.color.light_green), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_about);
        tabLayout.addOnTabSelectedListener(this);
         presenter.getProfileData(SharedPreferencesHelper.retrieveDataFromSharedPref(getApplicationContext(),"token"));
    }
    void get_image(CircleImageView image, String url) {
        Glide.with(getApplicationContext())
                .load(url)
                .into(image);
    }
    void updateUI(ProfileDetailsResponse data) {
        name.setText(data.getPayload().getName());
        username.setText(data.getPayload().getUsername());
        get_image(mProfileImage,data.getPayload().getImage());

    }
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mProfileImage.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();

        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int tabIconColor = ContextCompat.getColor(this, R.color.light_green);
        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int tabIconColor = ContextCompat.getColor(this, R.color.light_grey);
        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void successfullResponse(ProfileDetailsResponse data,String message) {
        this.data=data;
        updateUI(data);
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureResponse(String message) {

    }

    class UserProfileTabsAdapter extends FragmentPagerAdapter {

        public UserProfileTabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new UserProfileWallFragment();
                case 1:
                    UserProfileAboutFragment fragment=new UserProfileAboutFragment();
                    if(data!=null)
                    {
                        Bundle bundle=new Bundle();
                        bundle.putString("bio",data.getPayload().getBio());
                        fragment.setArguments(bundle);
                    }
                    return fragment;
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
