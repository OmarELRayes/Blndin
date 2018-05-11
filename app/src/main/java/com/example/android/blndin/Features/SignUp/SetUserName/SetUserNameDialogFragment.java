package com.example.android.blndin.Features.SignUp.SetUserName;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blndin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetUserNameDialogFragment extends Fragment {


    public SetUserNameDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_user_name_dialog, container, false);
    }

}
