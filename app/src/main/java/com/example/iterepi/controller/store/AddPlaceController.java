package com.example.iterepi.controller.store;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class AddPlaceController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private AddPlaceDialog addPlaceDialog;
    private HTTPSWebUtilDomi utilDomi;

    public AddPlaceController(AddPlaceDialog addPlaceDialog) {
        this.addPlaceDialog = addPlaceDialog;
        this.utilDomi = new HTTPSWebUtilDomi();
        addPlaceDialog.getAddPlaceBtn().setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.updateDataBtn:

                Place place = new Place();

                String user_id = FirebaseAuth.getInstance().getUid();
                String id = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id).child("places").push().getKey();;
                String name = addPlaceDialog.getPlaceNameTF().getEditText().getText().toString();
                String location = addPlaceDialog.getPlaceLocationTF().getEditText().getText().toString();

                place.setId(id);
                place.setLocation(location);
                place.setName(name);

                new Thread(
                        ()->{
                            Gson gson = new Gson();
                            String json = gson.toJson(place);
                            utilDomi.POSTrequest(1,"https://iterepi.firebaseio.com/sellers/"+user_id+"/places/.json",json);
                        }

                ).start();

                addPlaceDialog.finish();
                break;


        }
    }

    @Override
    public void onResponse(int callbackID, String response) {

    }
}
