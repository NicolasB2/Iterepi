package com.example.iterepi.controller.user;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.view.login.MainActivity;
import com.example.iterepi.view.user.NavigationViewActivity;
import com.example.iterepi.view.user.PaymentMethodsActivity;
import com.example.iterepi.view.user.UserProfileActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationViewController implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationViewActivity activity;
    private View header;
    private CircleImageView photo;
    private TextView name;

    public NavigationViewController(NavigationViewActivity activity) {

        this.activity = activity;

        activity.getNavigationView().setNavigationItemSelectedListener(this);

        header = activity.getNavigationView().getHeaderView(0);

        photo = header.findViewById(R.id.photo);
        name = header.findViewById(R.id.name);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase.getInstance().getReference("buyers").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Buyer buyer = dataSnapshot.getValue(Buyer.class);

                if (buyer.getPhoto() != null) {
                    Glide.with(activity).load(buyer.getPhoto()).centerCrop().into(photo);
                }
                name.setText(buyer.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        header.setOnClickListener(c -> {

            Intent i = new Intent(activity, UserProfileActivity.class);
            activity.startActivity(i);

        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent i;
        switch (item.getItemId()) {

            case R.id.op_payment_methods:
                i = new Intent(activity, PaymentMethodsActivity.class);
                i.putExtra("type",PaymentMethodsActivity.BUYER);
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
