package com.example.iterepi.controller.user;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.view.login.MainActivity;
import com.example.iterepi.view.user.NavigationViewActivity;
import com.example.iterepi.view.user.PaymentMethodsActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationViewController implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationViewActivity activity;

    public NavigationViewController(NavigationViewActivity activity) {

        this.activity = activity;

        activity.getNavigationView().setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent i;
        switch (item.getItemId()) {

            case R.id.op_payment_methods:
                i = new Intent(activity, PaymentMethodsActivity.class);
                activity.startActivity(i);

                break;

            case R.id.op_my_purchases:

                break;


            case R.id.op_recommend:

                break;

            case R.id.op_doubts:

                break;

            case R.id.op_terms:

                break;

            case R.id.op_logout:

                FirebaseAuth.getInstance().signOut();
                i = new Intent(activity, MainActivity.class);
                activity.startActivity(i);
                activity.finishAffinity();

                break;

        }

        return false;
    }
}
