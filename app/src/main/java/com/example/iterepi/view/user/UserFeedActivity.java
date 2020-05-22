package com.example.iterepi.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserFeedActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.feed);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.feed:

                    case R.id.location:
                        startActivity(new Intent(getApplicationContext(),LocationActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;
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
