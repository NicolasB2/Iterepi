package com.example.iterepi.controller.store;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.example.iterepi.view.store.SeePlaceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.HashMap;

public class seePlaceController implements View.OnClickListener{

    private SeePlaceActivity activity;

    public seePlaceController(SeePlaceActivity activity) {
        this.activity = activity;

        this.activity.getBackBtn().setOnClickListener(this);
        this.activity.getUpdateDataBtn().setOnClickListener(this);

        if(this.activity.getPlace() == null){
            loadPlace();
        }
    }

    public void loadPlace(){
        String user_id = FirebaseAuth.getInstance().getUid();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("sellers").child(user_id)
                .child("places").child(activity.getPlaceId());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Place place =  dataSnapshot.getValue(Place.class);

                activity.runOnUiThread(
                        ()->{
                            if(place!=null){
                                activity.setPlace(place);
                                activity.getPlaceNameTV().setText(place.getName());
                                activity.getPlaceNameTF().getEditText().setText(place.getName());
                                activity.getPlaceLocationTF().getEditText().setText(place.getLocation());
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
            case R.id.backBtn:
                activity.finish();
                break;

            case R.id.updateDataBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String name = activity.getPlaceNameTF().getEditText().getText().toString();
                String location = activity.getPlaceLocationTF().getEditText().getText().toString();

                if(user_id!=null){

                    if(location.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.location),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if(name.equals("") ){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.name),Toast.LENGTH_LONG).show();
                        break;
                    }

                    else {

                        Place p = new Place();
                        p.setId(activity.getPlaceId());
                        p.setLocation(location);
                        p.setName(name);

                        FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id).child("places").child(p.getId()).setValue(p);

                        Intent s = new Intent(activity, MyPlacesActivity.class);
                        activity.startActivity(s);
                        activity.finish();

                        Toast.makeText(activity,activity.getString(R.string.update_successful),Toast.LENGTH_LONG).show();
                    }

                }
                break;
        }
    }

}
