package com.example.iterepi.controller.user;

import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Card;
import com.example.iterepi.model.Cart;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Item;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.view.user.MakePurchaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class MakePurchaseController implements View.OnClickListener {

    private MakePurchaseActivity activity;
    private Cart cart;
    private Seller mySeller;
    private ArrayList<Card> cards;
    private Buyer buyer;


    public MakePurchaseController(MakePurchaseActivity activity) {

        this.activity = activity;
        cards = new ArrayList<>();

        activity.getPayBtn().setOnClickListener(this);

        findSeller();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.payBtn:

                pay();

                break;


        }

    }

    private void pay() {

        String id = FirebaseDatabase.getInstance().getReference().child("transactions").push().getKey();

        DatabaseReference trans = FirebaseDatabase.getInstance().getReference().child("transactions").child(id);

        trans.child("id").setValue(id);
        trans.child("value").setValue(cart.getTotalCart());

        Date now = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(now);

        trans.child("purchaseDate").setValue(date);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                buyer = dataSnapshot.getValue(Buyer.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        trans.child("buyerName").setValue(user.getDisplayName());
        trans.child("buyerID").setValue("");
        trans.child("sellerName").setValue(mySeller.getName());
        trans.child("sellerID").setValue(mySeller.getId());
        trans.child("state").setValue("to_pick");
        trans.child("cart").setValue(cart);
        trans.child("buyerPhoto").setValue(user.getPhotoUrl());

        FirebaseDatabase.getInstance().getReference().child("buyers").child(buyer.getId()).child("purchasesID").child(id).child(id);
        FirebaseDatabase.getInstance().getReference().child("sellers").child(mySeller.getId()).child("salesID").child(id).child(id);


    }


    public Seller findSeller() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        Query query = FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("cart");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                cart = dataSnapshot.getValue(Cart.class);

                HashMap<String, Item> items = cart.getItems();
                Collection<Item> listItems = items.values();
                ArrayList<Item> myItems = new ArrayList<>(listItems);

                String idItem = myItems.get(0).getId();

                findByItem(idItem);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return null;

    }

    public void findByItem(String idItem) {

        Query query = FirebaseDatabase.getInstance().getReference().child("sellers");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Seller seller = child.getValue(Seller.class);

                    ArrayList<Place> places = new ArrayList<>(seller.getPlaces().values());

                    for (int i = 0; i < places.size(); i++) {

                        ArrayList<Category> categories = new ArrayList<Category>(places.get(i).getCategories().values());

                        for (int j = 0; j < categories.size(); j++) {

                            ArrayList<Item> items = new ArrayList<>(categories.get(j).getItems().values());

                            for (int k = 0; k < items.size(); k++) {

                                if (items.get(k).getId().equals(idItem)) {

                                    mySeller = seller;
                                    setInfoOnPurchase();
                                    break;

                                }


                            }

                        }


                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void setInfoOnPurchase() {

        activity.getStoreNameTV().setText(mySeller.getName());
        Glide.with(activity.getApplicationContext()).load(mySeller.getLogo()).centerCrop().into(activity.getImageStoreIV());
        activity.getPurchaseValue().setText("$" + cart.getTotalCart() + "");

        HashMap<String, Place> places = mySeller.getPlaces();
        Collection<Place> listPlaces = places.values();
        ArrayList<Place> myPlaces = new ArrayList<>(listPlaces);

        String[] placesSpinner = new String[myPlaces.size()];

        for (int i = 0; i < myPlaces.size(); i++) {

            placesSpinner[i] = activity.getString(R.string.place) + " " + myPlaces.get(i).getName();

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, R.layout.spinner_place_seller, placesSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.getPickUpSP().setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Query query = FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("cards");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Card card = child.getValue(Card.class);

                    cards.add(card);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        String[] cardsSpinner = new String[cards.size()];

        for (int i = 0; i < cardsSpinner.length; i++) {

            cardsSpinner[i] = cards.get(i).getCardNumber();

        }

        ArrayAdapter<String> adapterCard = new ArrayAdapter<>(activity, R.layout.spinner_place_seller, cardsSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.getPaymentMethodsSP().setAdapter(adapterCard);


    }


}
