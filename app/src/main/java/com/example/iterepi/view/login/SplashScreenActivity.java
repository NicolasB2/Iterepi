package com.example.iterepi.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.AddPlaceController;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Seller;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.example.iterepi.view.store.StoreHomeActivity;
import com.example.iterepi.view.user.UserFeedActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                // If user hasn't logged in.
                if (user == null) {

                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();

                // If user has logged in
                } else {

                // Also have to check if user it's a Buyer or a Seller ! ... Verification here.
                    String id = user.getUid();


                    // Checking if it's a buyer
                    Query queryB = FirebaseDatabase.getInstance().getReference().child("buyers").child(id);

                    queryB.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Buyer buyer = dataSnapshot.getValue(Buyer.class);

                            if (buyer != null) {

                                Log.e("BUYER", "IT'S BUYER");
                                Intent b = new Intent(SplashScreenActivity.this, UserFeedActivity.class);
                                startActivity(b);
                                finish();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    // Checking if it's a seller
                    Query queryS = FirebaseDatabase.getInstance().getReference().child("sellers").child(id);

                    queryS.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Log.e("SELLER", "IT'S SELLER");
                                Intent s = new Intent(SplashScreenActivity.this, AddCategoryDialog.class);
                                startActivity(s);
                                finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
