package com.example.iterepi.view.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.CartItemAdapter;
import com.example.iterepi.controller.user.CartController;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private View view;
    private CartItemAdapter cartItemAdapter;
    private ArrayList<Item> cartItems;
    private RecyclerView listCartRV;
    private CartController controller;
    private ImageView emptyCartIV;
    private TextView emptyCartTV;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        listCartRV = view.findViewById(R.id.listCartRV);
        listCartRV.setLayoutManager(new LinearLayoutManager(getContext()));
        cartItems = new ArrayList<>();
        emptyCartIV = view.findViewById(R.id.emptyCart);
        emptyCartTV = view.findViewById(R.id.emptyCartTV);

        Query query = FirebaseDatabase.getInstance().getReference()
                .child("buyers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Buyer b = dataSnapshot.getValue(Buyer.class);
                loadItems(b);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cartItemAdapter = new CartItemAdapter(cartItems);
        listCartRV.setAdapter(cartItemAdapter);
        return view;
    }

    public void loadItems(Buyer b) {

        if (b != null) {
            if (b.getCart() != null) {
                emptyCartIV.setVisibility(View.GONE);
                emptyCartTV.setVisibility(View.GONE);

                for (Item i : b.getCart().getItems()) {
                    cartItems.add(i);
                }
            } else {
                emptyCartIV.setVisibility(View.VISIBLE);
                emptyCartTV.setVisibility(View.VISIBLE);
            }
        } else {
        }

        /* For testing
        cartItems.add(new Item("id1", "name", "description", 10.00, 1, "photo"));
        cartItems.add(new Item("id2", "name", "description", 20.00, 2, "photo"));
        cartItems.add(new Item("id3", "name", "description", 30.00, 3, "photo"));
        cartItems.add(new Item("id4", "name", "description", 40.00, 4, "photo"));
        cartItems.add(new Item("id5", "name", "description", 50.00, 5, "photo"));
        cartItems.add(new Item("id6", "name", "description", 60.00, 6, "photo"));*/
    }
}
