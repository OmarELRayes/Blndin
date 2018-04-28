package com.example.android.blndin.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.android.blndin.R;
import com.example.android.blndin.UserProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtrasFragment extends Fragment {

    RelativeLayout profile;
    RelativeLayout notifications;
    RelativeLayout invites;
    RelativeLayout settings;
    RelativeLayout logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        setNav();
    }

    public void setNav() {
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : Open profile
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : Open Notifications
            }
        });

        invites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : Open Invites
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
                //TODO : Logout from app
            }
        });
    }
}
