package com.example.android.blndin.Features.Profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.blndin.R;

import org.apmem.tools.layouts.FlowLayout;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileAboutFragment extends Fragment {

    @BindView(R.id.bio)TextView bio;

    public UserProfileAboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // auth = getArguments().getBoolean("auth");
        if(getArguments()!=null)
        bio.setText(getArguments().getString("bio"));
        FlowLayout interestsSpan = (FlowLayout) view.findViewById(R.id.user_interests_span);
        View interest = LayoutInflater.from(getContext()).inflate(R.layout.item_interests, null, false);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMarginEnd(10);
        interest.setLayoutParams(lp);
        interestsSpan.addView(interest);
        View interest2 = LayoutInflater.from(getContext()).inflate(R.layout.item_interests, null, false);
        ((TextView) interest2.findViewWithTag("interest_txt")).setText("#Cooking");
        interestsSpan.addView(interest2);
    }
}
