package com.example.buckb.menubottom;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActionBar toolbar;

    FrameLayout frameLayout;
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //frameLayout=(FrameLayout) findViewById(R.id.frame_container);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Shop");
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new FragmentA());

    }
    int idd=0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    if(idd!=R.id.navigation_shop) {
                        toolbar.setTitle("Shop");
                        idd=R.id.navigation_shop;
                        loadFragment(new FragmentA());
                    }
                    else
                    {
                        idd=R.id.navigation_shop;
                    }
                    return true;
                case R.id.navigation_gifts:
                    if(idd!=R.id.navigation_gifts) {
                        idd=R.id.navigation_gifts;
                        loadFragment(new FragmentB());
                        Toast.makeText(MainActivity.this,"aaa",Toast.LENGTH_LONG).show();
                        toolbar.setTitle("My Gifts");
                    }
                    else
                    {
                        idd=R.id.navigation_gifts;
                    }

                    return true;
                case R.id.navigation_cart:
                    if(idd!=R.id.navigation_cart) {
                        idd=R.id.navigation_cart;
                        loadFragment(new FragmentA());
                        toolbar.setTitle("Cart");
                        //FragmentA fragmentA=(FragmentA) getFragmentManager().findFragmentById(R.id.)
                    }
                    else
                    {
                        idd=R.id.navigation_cart;
                    }

                    return true;
                case R.id.navigation_profile:
                    if(idd!=R.id.navigation_profile) {
                        loadFragment(new FragmentB());
                        idd=R.id.navigation_profile;
                        toolbar.setTitle("Profile");

                    }
                    else
                    {
                        idd=R.id.navigation_profile;
                    }

                    return true;
            }

            return false;
        }
    };

    void loadFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getFragmentManager();

        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.FrameContainer,fragment);

        fragmentTransaction.commit();
    }


}