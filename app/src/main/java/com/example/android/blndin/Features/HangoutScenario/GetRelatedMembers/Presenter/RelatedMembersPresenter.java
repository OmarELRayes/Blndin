package com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Presenter;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.android.blndin.Models.ActivityModel;
import com.example.android.blndin.Models.UserModel;

import java.util.ArrayList;

/**
 * Created by Luffy on 5/12/2018.
 */

public interface RelatedMembersPresenter {

   void getActivities(String token);

    void getRelatedMembers(String token, RelativeLayout relativeLayout, View customMarkerView, ImageView imageView);

    void loadMarkers(ArrayList<UserModel> members, View customMarkerView, ImageView imageView);

    Bitmap getMarkerBitmapFromView(View view,ImageView imageView,Bitmap bitmap);

    void slideDown(RelativeLayout relativeLayout);

    void initMap(FragmentManager fragmentManager);

     void buildApiClient();

    void getLocation();


}
