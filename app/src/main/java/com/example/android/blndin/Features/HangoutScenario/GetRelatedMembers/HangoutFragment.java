package com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.blndin.Adapters.HangoutInviteMembersAdapter;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.ActivitiesResponse;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Presenter.RelatedMembersPresenter;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.View.RelatedMembersView;
import com.example.android.blndin.Models.ActivityModel;
import com.example.android.blndin.Models.UserModel;
import com.example.android.blndin.R;
import com.isapanah.awesomespinner.AwesomeSpinner;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.ThreadMode;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutFragment extends Fragment implements RelatedMembersView{

    @BindView(R.id.hangout_layout)RelativeLayout hangoutLayout;
    @BindView(R.id.proceed_layout)LinearLayout proceedLayout;
    @BindView(R.id.btn_hangout_lets_go) FancyButton lets_go_btn;
    @BindView(R.id.proceed_layout_recyclerView)RecyclerView membersRecyclerView;
    @BindView(R.id.spinner_activities)AwesomeSpinner spinner;
    @BindView(R.id.proceed_layout_btn)FancyButton procced_btn;
    @BindView(R.id.et_hangout_sub_activity)EditText sub_activity_et;
    @BindView(R.id.pin_location_layout)RelativeLayout pin_layout;
    @BindView(R.id.pin_location_btn)ImageView pin_btn;
    RelatedMembersPresenter presenter;
    //View components
    View v;
    View mCustomMarkerView;
    ImageView mMarkerImageView;
    RecyclerView.Adapter adapter;
    ArrayList<UserModel> members;
    ArrayList<String>spinner_list;
    RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_hangout, container, false);
        ButterKnife.bind(this,v);
        init();
        spinner_list=new ArrayList<>();
        presenter.buildApiClient();
        presenter.initMap(getChildFragmentManager());
        presenter.getActivities("$2y$10$aOxpZjszXYGAD/pYvGhbe.hGwzJfwTdYCFOkkHcVYRqErVAsSUgMq");
        spinner.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                Toast.makeText(getActivity(),String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
      //  init_map();
        return v;
    }

     void init(){
         mCustomMarkerView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
         mMarkerImageView = (ImageView) mCustomMarkerView.findViewById(R.id.marker_user_image);
         layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
         membersRecyclerView.setLayoutManager(layoutManager);
         members = new ArrayList<>();
         adapter = new HangoutInviteMembersAdapter(getContext(), members);
         membersRecyclerView.setAdapter(adapter);
         lets_go_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                if(!sub_activity_et.getText().toString().trim().isEmpty())
                 presenter.getRelatedMembers("$2y$10$aOxpZjszXYGAD/pYvGhbe.hGwzJfwTdYCFOkkHcVYRqErVAsSUgMq",hangoutLayout,mCustomMarkerView,mMarkerImageView);
                 else
                     Toast.makeText(getActivity(),"Enter Specific activity",Toast.LENGTH_SHORT).show();

             }
         });
         procced_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 pin_layout.setVisibility(View.VISIBLE);
                 presenter.slideDown(proceedLayout);
             }
         });
         pin_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 pin_layout.setVisibility(View.INVISIBLE);
                 presenter.getCenterLocation();
                 presenter.fillHangoutDialog(String.valueOf(spinner.getSelectedItemPosition()),sub_activity_et.getText().toString());
             }
         });
         presenter=new RelatedMembersPresenterImp(this,getActivity(),proceedLayout,adapter,members);
     }


    @Override
    public void successfulResponseRelatedMembers(String status, String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successfulResponseActivites(ActivitiesResponse activitiesResponse) {
         for(ActivityModel model:activitiesResponse.getPayload().getActivities())
           spinner_list.add(model.getTitle());
         ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinner_list);
         spinner.setAdapter(categoriesAdapter);
        if(spinner_list.size()>0)
            spinner.setSelection(0);
        Toast.makeText(getActivity(),"success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureResponse(String status, String message) {
            Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public View findViewById() {
        return  getActivity().findViewById(android.R.id.content);
    }

    @Override
    public void successfulResponseCreateHangout(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successfullResponseCheckHangout(String status) {
        if(status.equals("0"))
            Toast.makeText(getActivity(),"no one accepted yet",Toast.LENGTH_SHORT).show();
        else Toast.makeText(getActivity(),"accepted",Toast.LENGTH_SHORT).show();
    }


//     public void onActivitesListed(EventBusHangout eventBusHangout)
//     {
//            for(ActivityModel model:eventBusHangout.getActivityModels())
//                spinner_list.add(model.getTitle());
//         ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinner_list);
//         spinner.setAdapter(categoriesAdapter);
//     }

    @Override
    public void onStart() {
        super.onStart();

      //  EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
      //  EventBus.getDefault().unregister(this);
    }
    //      void init_map(){
//          SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                  .findFragmentById(R.id.map);
//          mapFragment.getMapAsync(this);
//     }

//    public void slideDown(){
//        TranslateAnimation animate = new TranslateAnimation(
//                0,                 // fromXDelta
//                0,                 // toXDelta
//                0,                 // fromYDelta
//                hangoutLayout.getHeight()); // toYDelta
//        animate.setDuration(500);
//        animate.setFillAfter(true);
//        hangoutLayout.startAnimation(animate);
//    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap=googleMap;
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.046141, 31.365187), 20));
//        mMap.setOnMarkerClickListener(this);
//        //CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(getContext());
//        //mMap.setInfoWindowAdapter(customInfoWindow);
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }
//
//    @Override
//    public boolean onMarkerClick(Marker marker) {
////        Toast.makeText(getContext(), "User id " + marker.getTag(), Toast.LENGTH_SHORT).show();
////        Log.d("Hangout", "onMarkerClick: " + marker.getTag());
////        final Dialog dialog = new Dialog(getActivity());
////        dialog.setContentView(R.layout.marker_dialog);
////        FancyButton btnHang = (FancyButton) dialog.findViewById(R.id.info_window_btn_invite);
////        FancyButton btnPreview = (FancyButton) dialog.findViewById(R.id.info_window_btn_preview);
////        btnHang.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (isHidden) {
////                    proceedLayout.setVisibility(View.VISIBLE);
////                    isHidden = false;
////                }
////                HangoutInviteMemberModel model = new HangoutInviteMemberModel("kappa", "Omar ELRayes", "kappa2");
////                members.add(model);
////                adapter.notifyDataSetChanged();
////                dialog.dismiss();
////            }
////        });
////        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////        dialog.show();
//        return false;
//    }

//    public void loadMarkers() {
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(31.046141, 31.365187))
//                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, R.drawable.user))))
//                .setTag("kappa");
//
//
//        // adding a marker with image from URL using glide image loading library
//        /*Glide.with(getApplicationContext()).
//                load(ImageUrl)
//                .asBitmap()
//                .fitCenter()
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                        mGoogleMap.addMarker(new MarkerOptions()
//                                .position(mDummyLatLng)
//                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, bitmap))));
//                        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mDummyLatLng, 13f));
//
//
//                    }
//                });
//*/
//    }

//    private Bitmap getMarkerBitmapFromView(View view, @DrawableRes int resId) {
//        mMarkerImageView.setImageResource(resId);
//        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//        view.buildDrawingCache();
//        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
//                Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(returnedBitmap);
//        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
//        Drawable drawable = view.getBackground();
//        if (drawable != null)
//            drawable.draw(canvas);
//        view.draw(canvas);
//        return returnedBitmap;
//    }
//
//    public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {
//        private Context context;
//
//        public CustomInfoWindowGoogleMap(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public View getInfoWindow(final Marker marker) {
//            View view = ((Activity) context).getLayoutInflater()
//                    .inflate(R.layout.marker_dialog, null);
//            TextView name = (TextView) view.findViewById(R.id.info_window_username);
//            TextView gender = (TextView) view.findViewById(R.id.info_window_gender);
//            FancyButton invite = (FancyButton) view.findViewById(R.id.info_window_btn_invite);
//            FancyButton preview = (FancyButton) view.findViewById(R.id.info_window_btn_preview);
//            final String userId = marker.getTag().toString();
//
//            // Fill data
//            name.setText("Omar ELRayes");
//            gender.setText("Male");
//            invite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, "Invite " + userId, Toast.LENGTH_SHORT).show();
//                }
//            });
//            preview.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, "Preview " + userId, Toast.LENGTH_SHORT).show();
//                }
//            });
//            return view;
//        }
//
//        @Override
//        public View getInfoContents(Marker marker) {
//            return null;
//        }
//    }


}
