package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.view.login.MainActivity;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.example.iterepi.view.store.SellerNavigationDrawerActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SellerNavigationDrawerController implements NavigationView.OnNavigationItemSelectedListener {

    private SellerNavigationDrawerActivity activity;

    public SellerNavigationDrawerController(SellerNavigationDrawerActivity activity) {

        this.activity = activity;

        activity.getNavigationView().setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch (item.getItemId()) {

            case R.id.store_item_accounts:

                break;

            case R.id.store_item_my_sales:

                break;


            case R.id.store_item_my_places:
                i = new Intent(activity, MyPlacesActivity.class);
                activity.startActivity(i);

                break;

            case R.id.store_item_doubts_frequent:

                break;

            case R.id.store_item_terms_and_conditions:

                break;

            case R.id.store_item_logout:

                FirebaseAuth.getInstance().signOut();
                i = new Intent(activity, MainActivity.class);
                activity.startActivity(i);
                activity.finishAffinity();

                break;

        }

        return false;
    }
}
