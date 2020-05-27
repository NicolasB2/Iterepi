package com.example.iterepi.controller.store;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;

public class MyPlacesController implements View.OnClickListener{

    private MyPlacesActivity activity;
    private HTTPSWebUtilDomi utilDomi;


    public MyPlacesController(MyPlacesActivity activity) {
        this.activity = activity;
        utilDomi = new HTTPSWebUtilDomi();
        activity.getAddMethodBtn().setOnClickListener(this);
        activity.getBackBtn().setOnClickListener(this);
        loadPlaces();
    }

    public void loadPlaces(){
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    Query query = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Seller seller = dataSnapshot.getValue(Seller.class);
                            HashMap<String,Place> places = seller.getPlaces();
                            activity.runOnUiThread(
                                    ()->{
                                        if(places!=null){
                                            for(String id : places.keySet()){
                                                Place p = places.get(id);
                                                activity.getAdapter().addPlace(p);
                                            }
                                        }
                                    }
                            );
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
        ).start();
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.backBtn:
                activity.finish();
                break;
            case R.id.addMethodBtn:
                i = new Intent(activity, AddPlaceDialog.class);
                activity.startActivity(i);
                activity.finish();
                break;
        }
    }

}
