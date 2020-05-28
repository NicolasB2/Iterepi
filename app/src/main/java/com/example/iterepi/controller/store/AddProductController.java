package com.example.iterepi.controller.store;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Item;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.view.store.AddProductDialog;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class AddProductController implements View.OnClickListener{

    public static final int GALLERY_CALLBACK = 1;

    private AddProductDialog activity;
    private Seller seller;
    private Uri tempUri;
    private String photo;

    public AddProductController(AddProductDialog activity) {
        this.activity = activity;
        activity.getAddProductBtn().setOnClickListener(this);
        activity.getCloseBtn().setOnClickListener(this);
        activity.getAddImageProductBtn().setOnClickListener(this);

        loadSeller();
        loadPlacesSpinner();
    }

    private void loadPlacesSpinner(){
        activity.getPlaceOfProductSP().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                activity.runOnUiThread(
                        ()->{
                            if (seller != null) {
                                ArrayList<Category> categories = new ArrayList<>();
                                Place p = (Place) activity.getPlaceOfProductSP().getSelectedItem();
                                if (seller.getPlaces().get(p.getId()).getCategories() != null) {
                                    HashMap<String, Category> cs = seller.getPlaces().get(p.getId()).getCategories();
                                    for (String idC : cs.keySet()) {
                                        categories.add(cs.get(idC));
                                    }
                                    ArrayAdapter<Category> adp1 = new ArrayAdapter<Category>(activity, android.R.layout.simple_list_item_1, categories);
                                    adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    activity.getCategoryOfProductSP().setAdapter(adp1);
                                }
                            } else {

                                Toast.makeText(activity.getApplicationContext(), "Aun no hay donde agregarlos!", Toast.LENGTH_SHORT).show();

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

                            if (seller.getPlaces() != null) {
                                HashMap<String, Place> ps = seller.getPlaces();
                                for (String id : ps.keySet()) {
                                    places.add(ps.get(id));
                                }
                                ArrayAdapter<Place> adp1 = new ArrayAdapter<Place>(activity, android.R.layout.simple_list_item_1, (List<Place>) places);
                                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                activity.getPlaceOfProductSP().setAdapter(adp1);
                            } else {

                                Toast.makeText(activity.getApplicationContext(), "Aun no tienes sedes", Toast.LENGTH_SHORT).show();
                                activity.getAddProductBtn().setEnabled(false);

                            }
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
                    } else {

                        if (tempUri != null) {

                            Item item = new Item();

                            String id = FirebaseDatabase.getInstance().getReference()
                                    .child("sellers").child(user_id)
                                    .child("places").child(place.getId())
                                    .child("categories").child(category.getId())
                                    .child("items").push().getKey();

                            FirebaseStorage storage = FirebaseStorage.getInstance();

                            storage.getReference().child("sellers").child(user_id).child("places")
                                    .child(place.getId()).child("categories").child(category.getId())
                                    .child("items").child(id).child("photo").putFile(tempUri).addOnCompleteListener(task -> {

                                Log.e("PRODUCT", "Photo added to storage ");

                                Toast.makeText(activity.getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();

                                if (task.isSuccessful()) {

                                    storage.getReference().child("sellers").child(user_id).child("places")
                                            .child(place.getId()).child("categories").child(category.getId())
                                            .child("items").child(id).child("photo").getDownloadUrl().addOnSuccessListener(uri -> {

                                        photo = uri.toString();
                                        Log.e("PRODUCT", photo);
                                        item.setPhoto(photo);

                                        item.setId(id);
                                        item.setName(name);
                                        item.setDescription(description);
                                        item.setPrice(Double.parseDouble(price));
                                        item.setQuantity(Integer.parseInt(inventory));

                                        FirebaseDatabase.getInstance().getReference()
                                                .child("sellers").child(user_id)
                                                .child("places").child(place.getId())
                                                .child("categories").child(category.getId())
                                                .child("items").child(item.getId()).setValue(item);


                                        Intent s = new Intent(activity, SeeCategoryActivity.class);
                                        s.putExtra("placeId", place.getId());
                                        s.putExtra("categoryId", category.getId());
                                        activity.startActivity(s);
                                        activity.finish();

                                    });
                                }

                            });


                        } else {

                            Snackbar.make(activity.getAddProductBtn(), activity.getString(R.string.add_photo_item), Snackbar.LENGTH_SHORT).show();


                        }
                    }
                }
                break;

            case R.id.closeBtn:
                activity.finish();
                break;

            case R.id.addImageProductBtn:

                Intent gal = new Intent(Intent.ACTION_GET_CONTENT);
                gal.setType("image/*");
                activity.startActivityForResult(gal, GALLERY_CALLBACK);


                break;

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case GALLERY_CALLBACK:
                    tempUri = data.getData();

                    try {
                        Bitmap image = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), tempUri);
//                        activity.getAddImageProductBtn().setImageBitmap(image);
                        Glide.with(activity.getAddProductBtn()).load(tempUri).centerCrop().into(activity.getAddImageProductBtn());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;

            }


        }


    }
}
