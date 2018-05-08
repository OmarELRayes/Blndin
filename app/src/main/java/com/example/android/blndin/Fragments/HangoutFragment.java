package com.example.android.blndin.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blndin.Adapters.HangoutInviteMembersAdapter;
import com.example.android.blndin.Models.HangoutInviteMemberModel;
import com.example.android.blndin.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutFragment extends Fragment implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener, GoogleMap.OnMarkerClickListener{

    //View components
    View v;
    RelativeLayout hangoutLayout;
    LinearLayout proceedLayout;
    FancyButton lets_go_btn;
    View mCustomMarkerView;
    ImageView mMarkerImageView;
    boolean isHidden = true;
    RecyclerView membersRecyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<HangoutInviteMemberModel> members;
    RecyclerView.LayoutManager layoutManager;
    //map components
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_hangout, container, false);
        init_view();
        init_map();
        return v;
    }
      void init_map(){
          SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                  .findFragmentById(R.id.map);
          mapFragment.getMapAsync(this);
     }
     void init_view(){
         hangoutLayout = (RelativeLayout) v.findViewById(R.id.hangout_layout);
         proceedLayout = (LinearLayout) v.findViewById(R.id.proceed_layout);
         lets_go_btn=(FancyButton)v.findViewById(R.id.btn_hangout_lets_go);
         lets_go_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 slideDown();
                 hangoutLayout.setVisibility(View.GONE);
                 loadMarkers();
             }
         });
         mCustomMarkerView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
         mMarkerImageView = (ImageView) mCustomMarkerView.findViewById(R.id.marker_user_image);
         membersRecyclerView = (RecyclerView) v.findViewById(R.id.proceed_layout_recyclerView);
         layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
         membersRecyclerView.setLayoutManager(layoutManager);
         members = new ArrayList<>();
         adapter = new HangoutInviteMembersAdapter(getContext(), members);
         membersRecyclerView.setAdapter(adapter);
     }
    public void slideDown(){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                hangoutLayout.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        hangoutLayout.startAnimation(animate);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.046141, 31.365187), 20));
        mMap.setOnMarkerClickListener(this);
        //CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(getContext());
        //mMap.setInfoWindowAdapter(customInfoWindow);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(), "User id " + marker.getTag(), Toast.LENGTH_SHORT).show();
        Log.d("Hangout", "onMarkerClick: " + marker.getTag());
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.marker_dialog);
        FancyButton btnHang = (FancyButton) dialog.findViewById(R.id.info_window_btn_invite);
        FancyButton btnPreview = (FancyButton) dialog.findViewById(R.id.info_window_btn_preview);
        btnHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHidden) {
                    proceedLayout.setVisibility(View.VISIBLE);
                    isHidden = false;
                }
                HangoutInviteMemberModel model = new HangoutInviteMemberModel("kappa", "Omar ELRayes", "kappa2");
                members.add(model);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        return false;
    }

    public void loadMarkers() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(31.046141, 31.365187))
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, R.drawable.user))))
                .setTag("kappa");


        // adding a marker with image from URL using glide image loading library
        /*Glide.with(getApplicationContext()).
                load(ImageUrl)
                .asBitmap()
                .fitCenter()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        mGoogleMap.addMarker(new MarkerOptions()
                                .position(mDummyLatLng)
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, bitmap))));
                        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mDummyLatLng, 13f));


                    }
                });
*/
    }

    private Bitmap getMarkerBitmapFromView(View view, @DrawableRes int resId) {
        mMarkerImageView.setImageResource(resId);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = view.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        view.draw(canvas);
        return returnedBitmap;
    }

    public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {
        private Context context;

        public CustomInfoWindowGoogleMap(Context context) {
            this.context = context;
        }

        @Override
        public View getInfoWindow(final Marker marker) {
            View view = ((Activity) context).getLayoutInflater()
                    .inflate(R.layout.marker_dialog, null);
            TextView name = (TextView) view.findViewById(R.id.info_window_username);
            TextView gender = (TextView) view.findViewById(R.id.info_window_gender);
            FancyButton invite = (FancyButton) view.findViewById(R.id.info_window_btn_invite);
            FancyButton preview = (FancyButton) view.findViewById(R.id.info_window_btn_preview);
            final String userId = marker.getTag().toString();

            // Fill data
            name.setText("Omar ELRayes");
            gender.setText("Male");
            invite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Invite " + userId, Toast.LENGTH_SHORT).show();
                }
            });
            preview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Preview " + userId, Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

}
