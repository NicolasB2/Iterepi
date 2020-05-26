package com.example.iterepi.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.user.UserFeedController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserFeedActivity extends AppCompatActivity {

    private UserFeedController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.feed);
        controller = new UserFeedController(this);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.feed:
                        break;
                    case R.id.location:
                        startActivity(new Intent(getApplicationContext(),LocationActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // To clear history of previous activities as login activities.
        finishAffinity();
    }
}
