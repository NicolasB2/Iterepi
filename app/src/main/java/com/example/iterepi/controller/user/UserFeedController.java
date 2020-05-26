package com.example.iterepi.controller.user;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.iterepi.view.user.UserFeedActivity;
import com.google.android.material.navigation.NavigationView;

public class UserFeedController implements NavigationView.OnNavigationItemSelectedListener {

    private UserFeedActivity activity;

    public UserFeedController(UserFeedActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Log.e("MENU ITEM", "" + item.toString());

        return false;
    }
}
