package com.example.android.blndin.Features.Auth.SignUp.SetUserName;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.blndin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetUserNameDialogFragment extends DialogFragment implements SetUserNameView {

    @BindView(R.id.set_username_et)
    EditText username;
    @BindView(R.id.set_username_submit)
    FancyButton submit;
    SetUserNamePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_set_user_name_dialog, container, false);
        ButterKnife.bind(this, root);
        presenter = new SetUserNamePresenterImp(this);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.submit(username.getText().toString(), getActivity());
            }
        });
    }

    @Override
    public void success() {
        onSubmitClicked listner = (onSubmitClicked) getActivity();
        listner.getBack();
        dismiss();
    }

    @Override
    public void failed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


}
