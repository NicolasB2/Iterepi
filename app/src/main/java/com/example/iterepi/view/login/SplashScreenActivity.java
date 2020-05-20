package com.example.iterepi.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                // If user hasn't logged in.
//                if(user == null) {

                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();

                // If user has logged in
//                }else{

                // Also have to check if user it's a Buyer or a Seller ! ... Verification here.


                // If User it's a Buyer...  Keep commented until finish all log in processes.
//                    Intent i = new Intent(SplashScreenActivity.this, UserFeedActivity.class);
//                    startActivity(i);
//                    finish();
//                }
            }
        }, SPLASH_TIME_OUT);
    }
}
