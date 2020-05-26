package com.example.iterepi.controller.user;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.iterepi.model.Buyer;
import com.example.iterepi.view.user.UserFeedActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
