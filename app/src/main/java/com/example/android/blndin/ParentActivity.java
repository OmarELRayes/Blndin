package com.example.android.blndin;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.container, new NewsfeedFragment()).commit();
                break;
            case R.id.nav_go:

                break;

            case R.id.nav_mysquads:

                break;
            case R.id.nav_hangouts:

                break;
            case R.id.nav_extras:

                break;
        }
        return true;
    }
}
