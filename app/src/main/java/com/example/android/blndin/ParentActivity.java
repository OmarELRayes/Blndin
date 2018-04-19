package com.example.android.blndin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.blndin.Fragments.NewsfeedFragment;

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
        switch (item.getItemId()) {
            case R.id.nav_wall:
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, new NewsfeedFragment()).commit();
                break;
            case R.id.nav_go:
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, new BlankFragment()).commit();
                break;

            case R.id.nav_mysquads:
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, new BlankFragment()).commit();
                break;
            case R.id.nav_hangouts:
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, new BlankFragment()).commit();
                break;
            case R.id.nav_extras:
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, new BlankFragment()).commit();
                break;
        }
        return true;
    }
}