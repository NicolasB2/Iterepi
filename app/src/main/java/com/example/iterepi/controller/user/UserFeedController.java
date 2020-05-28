package com.example.iterepi.controller.user;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Cart;
import com.example.iterepi.model.Item;
import com.example.iterepi.model.Seller;
import com.example.iterepi.view.user.CartActivity;
import com.example.iterepi.view.user.LocationActivity;
import com.example.iterepi.view.user.SectionStoreUser;
import com.example.iterepi.view.user.UserFeedActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UserFeedController implements View.OnClickListener {

    private UserFeedActivity activity;
    private Buyer buyer;

    public UserFeedController(UserFeedActivity activity) {
        this.activity = activity;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                buyer = dataSnapshot.getValue(Buyer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateSellers();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.feed);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.location:
                        activity.startActivity(new Intent(activity.getApplicationContext(), LocationActivity.class));
                        activity.overridePendingTransition(0, 0);
                        return true;

                    case R.id.feed:
                        activity.startActivity(new Intent(activity.getApplicationContext(), UserFeedActivity.class));
                        activity.overridePendingTransition(0, 0);
                        return true;

                    case R.id.cart:
                        activity.startActivity(new Intent(activity.getApplicationContext(), CartActivity.class));
                        activity.overridePendingTransition(0, 0);
                        return true;
                }


                return false;
            }
        });





    }

    private void updateSellers() {

        Query query = FirebaseDatabase.getInstance().getReference().child("sellers");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Seller seller = child.getValue(Seller.class);

                    activity.getAdapter().getSellers().add(seller);

                    activity.getAdapter().notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void createCart() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String id = FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid())
                .child("cart").push().getKey();

        HashMap<String, Item> items = new HashMap<String, Item>();

        Cart cart = new Cart(id, items);

        FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid())
                .child("cart").setValue(cart);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            default:

                Intent i = new Intent(activity, SectionStoreUser.class);
                i.putExtra("seller", activity.getAdapter().getSellers().get(activity.getSellersList().getChildAdapterPosition(v)));
                activity.startActivity(i);


                break;


        }

    }
}
