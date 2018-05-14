package com.example.android.blndin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.blndin.Features.HangoutScenario.Hangout.HangoutFragment;
import com.example.android.blndin.Features.MyHangouts.MyHangoutFragment;
import com.example.android.blndin.Features.MySquads.MySquadsFragment;
import com.example.android.blndin.Features.Newsfeed.NewsfeedFragment;
import com.example.android.blndin.Fragments.ExtrasFragment;

public class ParentActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView navigation;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, new NewsfeedFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.nav_wall:
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, new NewsfeedFragment()).commit();
                break;
            case R.id.nav_go:
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, new HangoutFragment()).commit();
                break;
            case R.id.nav_mysquads:
                manager.beginTransaction().replace(R.id.container, new MySquadsFragment()).commit();
                break;
            case R.id.nav_hangouts:
                manager.beginTransaction().replace(R.id.container, new MyHangoutFragment()).commit();
                break;
            case R.id.nav_extras:
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, new ExtrasFragment()).commit();
                break;
        }
        return true;
    }
}