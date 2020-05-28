package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Seller;
import com.example.iterepi.view.login.MainActivity;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.example.iterepi.view.store.SellerNavigationDrawerActivity;
import com.example.iterepi.view.user.PaymentMethodsActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class SellerNavigationDrawerController implements NavigationView.OnNavigationItemSelectedListener {

    private SellerNavigationDrawerActivity activity;
    private View header;
    private CircleImageView photo;
    private TextView name;

    public SellerNavigationDrawerController(SellerNavigationDrawerActivity activity) {

        this.activity = activity;

        header = activity.getNavigationView().getHeaderView(0);
        photo = header.findViewById(R.id.photo);
        name = header.findViewById(R.id.name);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase.getInstance().getReference().child("sellers").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Seller seller = dataSnapshot.getValue(Seller.class);

                if (seller.getLogo() != null) {

                    Glide.with(activity).load(seller.getLogo()).centerCrop().into(photo);

                }

                name.setText(seller.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        activity.getNavigationView().setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch (item.getItemId()) {

            case R.id.store_item_accounts:
                i = new Intent(activity, PaymentMethodsActivity.class);
                i.putExtra("type",PaymentMethodsActivity.SELLER);
                activity.startActivity(i);
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
