package com.example.android.blndin.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.blndin.Features.Auth.Login.LoginActivity;
import com.example.android.blndin.Features.Invites.InvitationsFragment;
import com.example.android.blndin.Features.Profile.UserProfileActivity;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.SharedPreferencesHelper;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtrasFragment extends Fragment {

    RelativeLayout profile;
    RelativeLayout notifications;
    RelativeLayout invites;
    RelativeLayout settings;
    RelativeLayout logout;
    RelativeLayout places;
    TextView name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the hangoutLayout for this fragment
        return inflater.inflate(R.layout.fragment_extras, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profile = (RelativeLayout) view.findViewById(R.id.extras_user_profile);
        notifications = (RelativeLayout) view.findViewById(R.id.extras_notifications);
        invites = (RelativeLayout) view.findViewById(R.id.extras_invites);
        settings = (RelativeLayout) view.findViewById(R.id.extras_settings);
        logout = (RelativeLayout) view.findViewById(R.id.extras_logout);
        places = (RelativeLayout) view.findViewById(R.id.extras_places);
        name=(TextView)view.findViewById(R.id.extras_user_name);
        name.setText(SharedPreferencesHelper.retrieveDataFromSharedPref(getActivity(),"username"));
        setNav();
    }

    public void setNav() {
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationFragment fragment = new NotificationFragment();
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(TAG)
                        .add(R.id.container, fragment)
                        .commit();
            }
        });

        invites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvitationsFragment fragment = new InvitationsFragment();
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(TAG)
                        .add(R.id.container, fragment)
                        .commit();
            }
        });

        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacesFragment fragment = new PlacesFragment();
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(TAG)
                        .add(R.id.container, fragment)
                        .commit();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : Open Settings
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferencesHelper.clearAllSavedSharedData(getContext());
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
