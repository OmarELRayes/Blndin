package com.example.android.blndin.Features.HangoutScenario.Hangout;

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
import android.location.Address;
import android.location.Geocoder;
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
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.ActivitiesResponse;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.CheckHangoutResponse;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.CreateHangoutResponse;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.RelatedMembersResponse;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Presenter.HangoutPresenter;
import com.example.android.blndin.Features.HangoutScenario.Hangout.View.RelatedMembersView;
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
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import android.os.Handler;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Luffy on 5/12/2018.
 */

public class HangoutPresenterImp implements HangoutPresenter, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
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
    UserModel user;
     Dialog dialog;
    LatLng centerLatlng;

    public HangoutPresenterImp(RelatedMembersView relatedMembersView, Context context, LinearLayout proceedLayout, RecyclerView.Adapter adapter, ArrayList<UserModel>addedMembers)
    {
        this.relatedMembersView = relatedMembersView;
        this.context = context;
        this.proceedLayout = proceedLayout;
        this.adapter = adapter;
        this.addedMembers=addedMembers;
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
    public void fillHangoutDialog(final String activity_id, final String sub_activity)
    {
         dialog = new Dialog(context);
        dialog.setContentView(R.layout.fill_hangout_dialog);
        final EditText hangout_title=(EditText)dialog.findViewById(R.id.hangout_title_dialog);
        final EditText hangout_message=(EditText)dialog.findViewById(R.id.hangout_message_dialog);
        FancyButton send_invited=(FancyButton)dialog.findViewById(R.id.btn_hangout_send_invites);
        FancyButton cancel=(FancyButton)dialog.findViewById(R.id.btn_hangout_cancel);
        send_invited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp=validate(hangout_title,hangout_message);
                if(temp.equals("ok"))
                {
                    //request create hangout
                    //serialize users array
                    try {
                        createHangout(Constants.TOKEN,hangout_title.getText().toString(),hangout_message.getText().toString()
                                ,activities.get(Integer.valueOf(activity_id)).getId(),sub_activity,list_to_string(addedMembers),getAddress(centerLatlng),String.valueOf(centerLatlng.latitude),String.valueOf(centerLatlng.longitude));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else Toast.makeText(context,temp,Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.show();
    }

    @Override
    public void createHangout(final String token, String title, String message, String activity_id, String sub_activity, String users,String address,String lat,String lng) {
        Log.d("title",title);
        Log.d("message",message);
        Log.d("act_id",activity_id);
        Log.d("sub_act",sub_activity);
        Log.d("users",users);
        Log.d("address",address);
        Log.d("lat,lng",lat+","+lng);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CreateHangoutResponse> call = apiInterface.createHangout(token,title,activity_id,sub_activity,users,message,address,lat,lng);
        call.enqueue(new Callback<CreateHangoutResponse>() {
            @Override
            public void onResponse(Call<CreateHangoutResponse> call, Response<CreateHangoutResponse> response) {
                if(response.body()!=null)
                {
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    {
                        dialog.dismiss();
                        relatedMembersView.successfulResponseCreateHangout(response.body().getPayload().getMessage());
                        Log.d("hangout_id",response.body().getPayload().getHangout_id());
                        checkHangout(token,response.body().getPayload().getHangout_id());
                    }
                    else
                        relatedMembersView.failureResponse(response.body().getStatus(),"Something error");
                }
               else  relatedMembersView.failureResponse(null, "Server Error");
            }

            @Override
            public void onFailure(Call<CreateHangoutResponse> call, Throwable t) {
                relatedMembersView.failureResponse(null, "Server Error");
            }
        });
    }

    @Override
    public void checkHangout(final String token, final String hangout_id) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CheckHangoutResponse> call = apiInterface.checkHangout(token,hangout_id);
        call.enqueue(new Callback<CheckHangoutResponse>() {
            @Override
            public void onResponse(Call<CheckHangoutResponse> call, Response<CheckHangoutResponse> response) {
                if (response.body() != null)
                {
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    {
                        if(response.body().getPayload().getStatus().equals("0"))
                        {
                            relatedMembersView.successfullResponseCheckHangout("0");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    checkHangout(token,hangout_id);
                                }
                            },5000);
                        }
                        else if(response.body().getPayload().getStatus().equals("1")) {
                            //goto HangoutProfile Chat
                            relatedMembersView.successfullResponseCheckHangout("1");
                        }
                    }
                    else relatedMembersView.failureResponse(response.body().getStatus(),"Something error");
                }
              else  relatedMembersView.failureResponse(null, "Server Error");
            }

            @Override
            public void onFailure(Call<CheckHangoutResponse> call, Throwable t) {
                relatedMembersView.failureResponse(null, "Server Error");
            }
        });

    }

    @Override
    public void getCenterLocation() {
        centerLatlng=mMap.getCameraPosition().target;
        isHidden=true;
    }
    String getAddress(LatLng latLng) throws IOException {
        String address="",city="",state="",country="";
        String full_address="";
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        try{
            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
        }
        catch (Exception e)
        {

        }
            if(address!=null)
                full_address+=address;
            if(city!=null)
                full_address+= ", "+city;
            if(state!=null)
                full_address+=", "+state;
            if(country!=null)
                full_address+=", "+country;
       return full_address;
    }
    @Override
    public void getActivities(String token) {
        //request
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ActivitiesResponse> call = apiInterface.getActivities(token);
        call.enqueue(new Callback<ActivitiesResponse>() {
            @Override
            public void onResponse(Call<ActivitiesResponse> call, Response<ActivitiesResponse> response) {
                if(response.body()!=null)
                {
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    {
                        // EventBus.getDefault().post(new EventBusHangout(activities));
                        relatedMembersView.successfulResponseActivites(response.body());
                        activities=new ArrayList<ActivityModel>(response.body().getPayload().getActivities());
                    }

                    else
                    {
                        try{
                            relatedMembersView.failureResponse(response.body().getStatus(), response.body().getMessage());
                        }
                        catch (Exception e)
                        {
                            relatedMembersView.failureResponse(null, "Server Error "+ response.body().getStatus());
                        }
                    }
                }
               else   relatedMembersView.failureResponse(null, "Server Error");

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
                if(response.body()!=null)
                {
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    {
                        loadMarkers(response.body().getPayload().getUsers(),customMarkerView,imageView);
                        slideDown(relativeLayout);
                        relativeLayout.setVisibility(View.GONE);
                        relatedMembersView.successfulResponseRelatedMembers(response.body().getStatus(),"Users Detected");
                    }
                    else {
                        try{
                            relatedMembersView.failureResponse(response.body().getStatus(), response.body().getMessage());
                        }
                        catch (Exception e)
                        {
                            relatedMembersView.failureResponse(null, "Server Error "+ response.body().getStatus());
                        }
                    }

                }
                else   relatedMembersView.failureResponse(null, "Server Error");
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
    public void slideDown(LinearLayout linearLayout) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                linearLayout.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        linearLayout.startAnimation(animate);
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {
        user=markers_users.get(marker);
        if(user!=null)
        {
            Toast.makeText(context, marker.getId() +" "+ user.getUuid(), Toast.LENGTH_SHORT).show();
            Log.d("Hangout", "onMarkerClick: " + marker.getTag());
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.marker_dialog);
            FancyButton btnHang = (FancyButton) dialog.findViewById(R.id.info_window_btn_invite);
            TextView tvName=(TextView)dialog.findViewById(R.id.info_window_username);
            tvName.setText(user.getName());
            FancyButton btnPreview = (FancyButton) dialog.findViewById(R.id.info_window_btn_preview);
            btnHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isHidden) {
                        proceedLayout.setVisibility(View.VISIBLE);
                        isHidden = false;
                    }
                    addedMembers.add(user);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
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


    String validate(EditText title,EditText message){
        if(title.getText().toString().trim().isEmpty())
            return "Enter Hangout Title";
        else if(message.getText().toString().trim().isEmpty())
            return "Enter Cover Letter";
        return "ok";
    }

    String list_to_string(ArrayList<UserModel> users)
    {
        ArrayList<Integer> temp=new ArrayList<>();
        for(UserModel user:users) temp.add(Integer.valueOf(user.getUuid()));
        return new Gson().toJson(temp);
    }
}
