package com.example.android.blndin;

import android.widget.ImageView;

import com.example.android.blndin.Models.MyHangoutModel;

/**
 * Created by LeGenÐ on 4/21/2018.
 */

public interface MyHangoutsItemClickListener {
    void onHangoutClickListener(int pos, MyHangoutModel item, ImageView shareImageView);
}
