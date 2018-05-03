package com.example.android.blndin.Fragments;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.example.android.blndin.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutFragment extends Fragment implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener, GoogleMap.OnMarkerClickListener{

    //View components
    View v;
    RelativeLayout layout;
    FancyButton lets_go_btn;
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
         layout=(RelativeLayout)v.findViewById(R.id.hangout_layout);
         lets_go_btn=(FancyButton)v.findViewById(R.id.btn_hangout_lets_go);
         lets_go_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 slideDown();
             }
         });
     }
    public void slideDown(){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                layout.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        layout.startAnimation(animate);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;


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
        return false;
    }
}
