package com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.ActivitiesResponse;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.RelatedMembersResponse;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Presenter.RelatedMembersPresenter;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.View.RelatedMembersView;
import com.example.android.blndin.Models.ActivityModel;
import com.example.android.blndin.Models.UserModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.Constants;
import com.example.android.blndin.Util.SampleMultiplePermissionListener;
import com.example.android.blndin.Util.SingleShotLocationProvider;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Luffy on 5/12/2018.
 */

public class RelatedMembersPresenterImp implements RelatedMembersPresenter, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMarkerClickListener {

    RelatedMembersView relatedMembersView;
    Context context;
    LinearLayout proceedLayout;
    RecyclerView.Adapter adapter;
    Map<Marker, UserModel> markers_users = new HashMap<>();
    ArrayList<ActivityModel> activities;
    ArrayList<UserModel> addedMembers;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    Marker m;
    private boolean isHidden = true;
    private MultiplePermissionsListener allPermissionsListener;
    Location lastLocation;
    LocationManager mLocationManager;

    public RelatedMembersPresenterImp(RelatedMembersView relatedMembersView, Context context, LinearLayout proceedLayout, RecyclerView.Adapter adapter) {
        this.relatedMembersView = relatedMembersView;
        this.context = context;
        this.proceedLayout = proceedLayout;
        this.adapter = adapter;
    }

    @Override
    public void initMap(FragmentManager fragmentManager) {
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        create_permission_listner();
        check_permission();
        getLocation();
        getDamnLocation();
        getFuckingDamnLocation();
        if (lastLocation == null)
            lastLocation = getLastKnownLocation();
    }

    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                break;
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l != null)
                Log.e("provider:, location:", provider + "  " + l.getLatitude() + "  " + l.getLongitude());
            else
                Log.e("provider:, location:", provider + "  " + null + "  " + null);
            if (l == null) {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy()) {
                Log.e(" best last location: %s", l.getLatitude() + "  " + l.getLongitude());
                bestLocation = l;
            }
        }
        if (bestLocation == null) {
            return null;
        }
        return bestLocation;
    }

    private void getDamnLocation() {
        LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                if(location!=null)
                    lastLocation=location;
                if(lastLocation!=null)
                    Toast.makeText(context, String.valueOf(lastLocation.getLatitude()) + " " + String.valueOf(lastLocation.getLongitude()), Toast.LENGTH_SHORT).show();
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
        };
        mLocationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10,
                10, mLocationListener);

    }
    private void getFuckingDamnLocation(){
        SingleShotLocationProvider.requestSingleUpdate(context,
                new SingleShotLocationProvider.LocationCallback() {
                    @Override public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                        Log.d("Location", "my location is " + location.toString());
                    }
                });
    }
    @Override
    public void buildApiClient() {
        buildGoogleApiClient();
    }

    @Override
    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if(lastLocation!=null)
            Toast.makeText(context, String.valueOf(lastLocation.getLatitude()) + " " + String.valueOf(lastLocation.getLongitude()), Toast.LENGTH_SHORT).show();
     //   else Toast.makeText(context,"null",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getActivities(String token) {
        //request
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ActivitiesResponse> call = apiInterface.getActivities(token);
        call.enqueue(new Callback<ActivitiesResponse>() {
            @Override
            public void onResponse(Call<ActivitiesResponse> call, Response<ActivitiesResponse> response) {
                if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    // EventBus.getDefault().post(new EventBusHangout(activities));
                    relatedMembersView.successfulResponseActivites(response.body());
                else
                    relatedMembersView.failureResponse(response.body().getStatus(), response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ActivitiesResponse> call, Throwable t) {
                relatedMembersView.failureResponse(null, "Server Error");
            }
        });

    }

    @Override
    public void getRelatedMembers(String token, final RelativeLayout relativeLayout, final View customMarkerView, final ImageView imageView) {
        //request
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<RelatedMembersResponse> call = apiInterface.getRelatedMembers(token,"11.115","11.11");
        call.enqueue(new Callback<RelatedMembersResponse>() {
            @Override
            public void onResponse(Call<RelatedMembersResponse> call, Response<RelatedMembersResponse> response) {
                if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                {
                    loadMarkers(response.body().getPayload().getUsers(),customMarkerView,imageView);
                    slideDown(relativeLayout);
                    relativeLayout.setVisibility(View.GONE);
                }
                else
                    relatedMembersView.failureResponse(response.body().getStatus(), response.body().getMessage());
            }

            @Override
            public void onFailure(Call<RelatedMembersResponse> call, Throwable t) {
                relatedMembersView.failureResponse(null, "Server Error");
            }
        });


    }

    @Override
    public void loadMarkers(ArrayList<UserModel> members, final View customMarkerView, final ImageView imageView) {
        for (final UserModel u : members) {
            Glide.with(context).
                    load(u.getImage())
                    .asBitmap()
                    .fitCenter()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                            m = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.valueOf(u.getLat()), Double.valueOf(u.getLng())))
                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(customMarkerView, imageView, bitmap))));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(u.getLat()), Double.valueOf(u.getLng())), 13f));
                            markers_users.put(m, u);
                        }
                    });

        }
    }

    @Override
    public Bitmap getMarkerBitmapFromView(View customMarkerView, ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public void slideDown(RelativeLayout relativeLayout) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                relativeLayout.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        relativeLayout.startAnimation(animate);
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {
        Toast.makeText(context, "User id " + marker.getTag(), Toast.LENGTH_SHORT).show();
        Log.d("Hangout", "onMarkerClick: " + marker.getTag());
        final Dialog dialog = new Dialog(context);
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
//                HangoutInviteMemberModel model = new HangoutInviteMemberModel("kappa", "Omar ELRayes", "kappa2");
                addedMembers.add(markers_users.get(marker));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.046141, 31.365187), 20));
        mMap.setOnMarkerClickListener(this);
        check_permission();
        getLocation();
        getDamnLocation();
        getFuckingDamnLocation();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            lastLocation=location;
            Toast.makeText(context, String.valueOf(lastLocation.getLatitude()) + " " + String.valueOf(lastLocation.getLongitude()), Toast.LENGTH_SHORT).show();
        }
        else
            lastLocation = getLastKnownLocation();
    }

    public  void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    private void check_permission(){
        Dexter.withActivity((Activity)context)
                .withPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(allPermissionsListener)
                .check();
    }
    private void create_permission_listner(){
        MultiplePermissionsListener feedbackViewMultiplePermissionListener =new SampleMultiplePermissionListener(context);

        allPermissionsListener =
                new CompositeMultiplePermissionsListener(feedbackViewMultiplePermissionListener,
                        SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.with(relatedMembersView.findViewById(),
                                "Denied")
                                .withOpenSettingsButton("SETTINGS")
                                .build());
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
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



}
