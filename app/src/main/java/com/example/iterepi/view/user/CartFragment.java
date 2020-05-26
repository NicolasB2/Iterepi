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

public class CartFragment extends Fragment implements View.OnClickListener{

    private View view;
    private CartItemAdapter cartItemAdapter;
    private ArrayList<Item> cartItems;
    private RecyclerView listCartRV;
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
    }

    @Override
    public void onClick(View v) {
        
    }
}
