package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.login.SplashScreenActivity;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.HashMap;

public class AddPlaceController implements View.OnClickListener{

    private AddPlaceDialog activity;

    public static final int SEARCH_CALLBACK = 1;
    public static final int SEND_CALLBACK = 2;

    private Seller seller;

    public AddPlaceController(AddPlaceDialog activity) {
        this.activity = activity;
        activity.getAddPlaceBtn().setOnClickListener(this);
        activity.getCloseBtn().setOnClickListener(this);

        loadSeller();
    }

    public void loadSeller(){
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    Query query = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            seller = dataSnapshot.getValue(Seller.class);
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

            case R.id.updateDataBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String id = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id).child("places").push().getKey();;
                String name = activity.getPlaceNameTF().getEditText().getText().toString();
                String location = activity.getPlaceLocationTF().getEditText().getText().toString();

                if(id!=null){

                    if(location.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.location),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if(name.equals("") ){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.name),Toast.LENGTH_LONG).show();
                        break;
                    }

                    else{

                        Place place = new Place();

                        place.setId(id);
                        place.setLocation(location);
                        place.setName(name);

                        FirebaseDatabase.getInstance().getReference()
                                .child("sellers").child(user_id)
                                .child("places").child(place.getId()).setValue(place);

                        Intent s = new Intent(activity, MyPlacesActivity.class);
                        activity.startActivity(s);
                        activity.finish();

                    }
                }
                break;

            case R.id.closeBtn:
                activity.finish();
                break;
        }
    }

}
