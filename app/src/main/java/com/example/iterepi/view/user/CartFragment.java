package com.example.iterepi.view.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.CartItemAdapter;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Cart;
import com.example.iterepi.model.Item;
import com.example.iterepi.model.Transaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment implements View.OnClickListener {

    private View view;
    private CartItemAdapter cartItemAdapter;
    private ArrayList<Item> cartItems;
    private RecyclerView listCartRV;

    private ImageView emptyCartIV;
    private TextView emptyCartTV;
    private Button finishOrderBtn;
    private Buyer current;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        listCartRV = view.findViewById(R.id.listCartRV);
        listCartRV.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyCartIV = view.findViewById(R.id.emptyCart);
        emptyCartTV = view.findViewById(R.id.emptyCartTV);
        emptyCartIV.setVisibility(View.GONE);
        emptyCartTV.setVisibility(View.GONE);
        finishOrderBtn = view.findViewById(R.id.finishOrderBtn);


        cartItems = new ArrayList<>();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("buyers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Buyer b = dataSnapshot.getValue(Buyer.class);
                loadItems(b);
                current = b;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        finishOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransaction(current);
            }
        });
        return view;
    }

    private void addTransaction(Buyer b) {
        Toast.makeText(getContext(), b.getName(), Toast.LENGTH_SHORT).show();

        if (b != null) {

            if (b.getCart() != null) {
                if (b.getTransactions() != null) {

                    Toast.makeText(getContext(),"transactions not null",Toast.LENGTH_SHORT).show();
                } else {
                    //Null transactions
                }
            }
        } else {
            Log.e(">>>>", "CartFragment: Error loading user");
        }
    }

    private void loadItems(Buyer b) {
        if (b != null) {
            if (b.getCart() != null) {
                if (b.getCart().getItems() != null) {
                    if (b.getCart().getItems().size() > 0) {
                        emptyCartIV.setVisibility(View.GONE);
                        emptyCartTV.setVisibility(View.GONE);
                        finishOrderBtn.setVisibility(View.VISIBLE);
                        for (Map.Entry<String, Item> entry : b.getCart().getItems().entrySet()) {
                            Item i = entry.getValue();
                            cartItems.add(i);
                            cartItemAdapter = new CartItemAdapter(cartItems, this);
                            listCartRV.setAdapter(cartItemAdapter);
                        }
                    } else {
                        emptyCartIV.setVisibility(View.VISIBLE);
                        emptyCartTV.setVisibility(View.VISIBLE);
                        finishOrderBtn.setVisibility(View.GONE);
                    }
                } else {
                    emptyCartIV.setVisibility(View.VISIBLE);
                    emptyCartTV.setVisibility(View.VISIBLE);
                    finishOrderBtn.setVisibility(View.GONE);
                }

            } else {
                emptyCartIV.setVisibility(View.VISIBLE);
                emptyCartTV.setVisibility(View.VISIBLE);
                finishOrderBtn.setVisibility(View.GONE);
            }
        } else {
            Log.e(">>>>", "CartFragment: Error loading user");
        }
    }

    @Override
    public void onClick(View v) {

    }
}
