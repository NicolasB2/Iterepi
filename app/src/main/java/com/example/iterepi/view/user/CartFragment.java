package com.example.iterepi.view.user;

import android.os.Bundle;
import android.util.Log;
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
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Cart;
import com.example.iterepi.model.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartFragment extends Fragment implements View.OnClickListener {

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
        emptyCartIV = view.findViewById(R.id.emptyCart);
        emptyCartTV = view.findViewById(R.id.emptyCartTV);
        emptyCartIV.setVisibility(View.GONE);
        emptyCartTV.setVisibility(View.GONE);

        cartItems = new ArrayList<>();
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


        return view;
    }

    public void loadItems(Buyer b) {

        if (b != null) {
            //For testing
            /*Item[] /*testItems = null;
            testItems = new Item[]{
                    new Item("id1", "name", "description", 10, 1, ""),
                    new Item("id2", "name2", "description2", 20, 2, "")};
           b.setCart(new Cart("cartId",testItems));*/
            if (b.getCart() != null) {
                if (b.getCart().getItems() != null) {
                    if (b.getCart().getItems().length > 0) {
                        emptyCartIV.setVisibility(View.GONE);
                        emptyCartTV.setVisibility(View.GONE);
                        for (Item i : b.getCart().getItems()) {
                            cartItems.add(i);
                            cartItemAdapter = new CartItemAdapter(cartItems);
                            listCartRV.setAdapter(cartItemAdapter);
                        }
                    } else {
                        emptyCartIV.setVisibility(View.VISIBLE);
                        emptyCartTV.setVisibility(View.VISIBLE);
                    }
                } else {
                    emptyCartIV.setVisibility(View.VISIBLE);
                    emptyCartTV.setVisibility(View.VISIBLE);
                }

            } else {
                Buyer bCart = b;
                b.setCart(new Cart("cartId",null));
                FirebaseDatabase.getInstance().getReference()
                        .child("buyers")
                        .child(b.getId())
                        .setValue(bCart);

                emptyCartIV.setVisibility(View.VISIBLE);
                emptyCartTV.setVisibility(View.VISIBLE);
            }
        } else {
            Log.e(">>>>", "CartFragment: Error loading user");
        }
    }

    @Override
    public void onClick(View v) {

    }
}
