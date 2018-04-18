package com.example.richard.w6_p2;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        MainFragment showFragment = (MainFragment) fragmentManager.findFragmentById(R.id.sharedpreference_fragment);
        if (showFragment == null) {
            showFragment = new MainFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.sharedpreference_fragment, showFragment, "MainFragment")
                    .commit();
        }

    }
}
