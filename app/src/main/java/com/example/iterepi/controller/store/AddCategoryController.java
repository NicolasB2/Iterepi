package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.SeePlaceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AddCategoryController implements View.OnClickListener{

    private AddCategoryDialog activity;

    public static final int SEARCH_CALLBACK = 1;
    public static final int SEND_CALLBACK = 2;

    private Seller seller;

    public AddCategoryController(AddCategoryDialog activity) {
        this.activity = activity;
        activity.getAddCategoryBtn().setOnClickListener(this);
        activity.getCloseBtn().setOnClickListener(this);

        loadSeller();
    }

    public void loadSeller(){
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

            case R.id.updateDataBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String name = activity.getCategoryNameTF().getEditText().getText().toString();
                Place place = (Place) activity.getPlaceOfProductSP().getSelectedItem();


                if(user_id != null){
                    if (name.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.name),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if(place==null){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.place),Toast.LENGTH_LONG).show();
                        break;
                    }

                    else{

                        String id = FirebaseDatabase.getInstance().getReference()
                                .child("sellers").child(user_id)
                                .child("places").child(place.getId())
                                .child("categories").push().getKey();

                        Category category = new Category();
                        category.setId(id);
                        category.setName(name);


                        FirebaseDatabase.getInstance().getReference()
                                .child("sellers").child(user_id)
                                .child("places").child(place.getId())
                                .child("categories").child(category.getId()).setValue(category);

                        Intent s = new Intent(activity, SeePlaceActivity.class);
                        s.putExtra("placeId",place.getId());
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
