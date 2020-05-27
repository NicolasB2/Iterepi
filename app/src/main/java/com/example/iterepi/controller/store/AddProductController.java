package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Item;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddProductDialog;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeePlaceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddProductController implements View.OnClickListener{

    private AddProductDialog activity;
    private Seller seller;

    public AddProductController(AddProductDialog activity) {
        this.activity = activity;
        activity.getAddProductBtn().setOnClickListener(this);
        activity.getCloseBtn().setOnClickListener(this);

        loadSeller();
        loadPlacesSpinner();
    }

    private void loadPlacesSpinner(){
        activity.getPlaceOfProductSP().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                activity.runOnUiThread(
                        ()->{
                            if(seller!=null){
                                ArrayList<Category> categories = new ArrayList<>();
                                Place p = (Place)activity.getPlaceOfProductSP().getSelectedItem();
                                HashMap<String,Category> cs = seller.getPlaces().get(p.getId()).getCategories();
                                for (String idC:cs.keySet()){
                                    categories.add(cs.get(idC));
                                }
                                ArrayAdapter<Category> adp1 = new ArrayAdapter<Category>(activity, android.R.layout.simple_list_item_1, categories);
                                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                activity.getCategoryOfProductSP().setAdapter(adp1);
                            }
                        }
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }
    private void loadSeller() {
        String user_id = FirebaseAuth.getInstance().getUid();

        Query query = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                seller = dataSnapshot.getValue(Seller.class);

                activity.runOnUiThread(
                        ()->{
                            ArrayList<Place> places = new ArrayList<>();
                            HashMap<String,Place> ps = seller.getPlaces();
                            for (String id:ps.keySet()){
                                places.add(ps.get(id));
                            }
                            ArrayAdapter<Place> adp1 = new ArrayAdapter<Place>(activity, android.R.layout.simple_list_item_1, (List<Place>) places);
                            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            activity.getPlaceOfProductSP().setAdapter(adp1);
                        });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.addProductBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String name = activity.getNameProductTF().getEditText().getText().toString();
                String price = activity.getPriceProductTF().getEditText().getText().toString();
                String inventory = activity.getInventoryQualityTF().getEditText().getText().toString();
                String description = activity.getDescriptionProductTF().getEditText().getText().toString();
                Place place = (Place) activity.getPlaceOfProductSP().getSelectedItem();
                Category category = (Category) activity.getCategoryOfProductSP().getSelectedItem();

                if(user_id != null){
                    if (name.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.name),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if (price.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.price),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if (inventory.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.inventory_quantity),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if (description.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.description),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if(place==null){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.place),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if(category==null){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.category),Toast.LENGTH_LONG).show();
                        break;
                    }

                    else{

                        String id = FirebaseDatabase.getInstance().getReference()
                                .child("sellers").child(user_id)
                                .child("places").child(place.getId())
                                .child("categories").child(category.getId())
                                .child("items").push().getKey();

                        Item item = new Item();
                        item.setId(id);
                        item.setName(name);
                        item.setDescription(description);

                        try {
                            item.setPrice(Double.parseDouble(price));
                        }catch (Exception e){
                            Toast.makeText(activity,"Error: "+activity.getString(R.string.price),Toast.LENGTH_LONG).show();
                            break;
                        }

                        try {
                            item.setQuantity(Integer.parseInt(inventory));
                        }catch (Exception e){
                            Toast.makeText(activity,"Error: "+activity.getString(R.string.price),Toast.LENGTH_LONG).show();
                            break;
                        }

                        FirebaseDatabase.getInstance().getReference()
                                .child("sellers").child(user_id)
                                .child("places").child(place.getId())
                                .child("categories").child(category.getId())
                                .child("items").child(item.getId()).setValue(item);

                        Intent s = new Intent(activity, SeeCategoryActivity.class);
                        s.putExtra("placeId",place.getId());
                        s.putExtra("categoryId",category.getId());
                        activity.startActivity(s);
                        activity.finish();
                        break;
                    }
                }

            case R.id.closeBtn:
                activity.finish();
                break;
        }
    }
}
