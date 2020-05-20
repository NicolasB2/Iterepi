package com.example.iterepi.view.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;

public class UserFeedActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
    }

    @Override
    public void onBackPressed() {

        // To clear history of previous activities as login activities.
        finishAffinity();

    }
}
