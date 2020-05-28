package com.example.iterepi.controller.user;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Item;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.view.user.SectionStoreUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SectionStoreUserController implements View.OnClickListener {

    private SectionStoreUser activity;
    private Seller seller;
    private ArrayList<Place> placesSeller;
    private Place placeSelected;


    public SectionStoreUserController(SectionStoreUser activity) {

        this.activity = activity;

        seller = (Seller) activity.getIntent().getExtras().get("seller");

        activity.getNameStoreTV().setText(seller.getName());

        Glide.with(activity).load(seller.getLogo()).centerCrop().into(activity.getImageLogoStoreIV());

        fillSpinner();

        activity.getSpinnerPlaces().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                activity.getAdapter().getItems().clear();
                activity.getAdapter().notifyDataSetChanged();
                placeSelected = placesSeller.get(position);
                listAllProducts();
                activity.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    public void fillSpinner() {

        HashMap<String, Place> places = seller.getPlaces();
        if (places != null) {

            Collection<Place> listPlaces = places.values();
            placesSeller = new ArrayList<>(listPlaces);
            String[] spinPlaces = new String[placesSeller.size()];
            for (int i = 0; i < placesSeller.size(); i++) {

                spinPlaces[i] = activity.getString(R.string.place) + " " + placesSeller.get(i).getName();

            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, R.layout.spinner_place_seller, spinPlaces);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            activity.getSpinnerPlaces().setAdapter(adapter);


            if (placesSeller != null) {
                if (placesSeller.get(0) != null) {

                    placeSelected = placesSeller.get(0);
                    activity.getSpinnerPlaces().setSelection(0);

                }
            }

            listAllProducts();
        }


    }


    public void listAllProducts() {

        HashMap<String, Category> categories = placeSelected.getCategories();

        if (categories != null) {
            Collection<Category> listCategories = categories.values();
            ArrayList<Category> myCategories = new ArrayList<>(listCategories);

            for (int j = 0; j < myCategories.size(); j++) {

                HashMap<String, Item> items = myCategories.get(j).getItems();
                if (items != null) {
                    Collection<Item> listItems = items.values();
                    ArrayList<Item> myItems = new ArrayList<>(listItems);

                    for (int k = 0; k < myItems.size(); k++) {

                        activity.getAdapter().getItems().add(myItems.get(k));
                        activity.getAdapter().notifyDataSetChanged();

                    }

                }
            }
        }
    }

    public void addItemToCart(Item item) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String id = FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("cart")
                .child("items").push().getKey();

        FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("cart")
                .child("items").child(id).setValue(item);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


        }

    }
}
