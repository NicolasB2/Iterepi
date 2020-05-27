package com.example.iterepi.view.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.controller.user.UserFeedController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserFeedActivity extends NavigationViewActivity {

    private UserFeedController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_user_feed, null, false);
        getDrawerLayout().addView(contentView, 0);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.feed);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.feed:
                        break;
                    case R.id.location:
                        startActivity(new Intent(getApplicationContext(),CartActivity.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                        overridePendingTransition(0,0);
                        break;
                }
            }
        });


        controller = new UserFeedController(this);

    }

}
