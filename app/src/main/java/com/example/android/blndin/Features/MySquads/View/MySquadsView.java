package com.example.android.blndin.Features.MySquads.View;

import com.example.android.blndin.Features.MySquads.Model.MySquadsModel;

import java.util.ArrayList;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public interface MySquadsView {
    void bindMySquads(ArrayList<MySquadsModel> models);

    void success(String message);

    void failure(String message);
}
