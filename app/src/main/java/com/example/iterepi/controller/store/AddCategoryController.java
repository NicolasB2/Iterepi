package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class AddCategoryController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private AddCategoryDialog addCategoryDialog;
    private HTTPSWebUtilDomi utilDomi;

    public AddCategoryController(AddCategoryDialog addCategoryDialog) {
        this.addCategoryDialog = addCategoryDialog;
        this.utilDomi = new HTTPSWebUtilDomi();
        addCategoryDialog.getAddPlaceBtn().setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.updateDataBtn:

                Place place = new Place();

                String user_id = FirebaseAuth.getInstance().getUid();
                String id = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id).child("places").push().getKey();;



                new Thread(
                        ()->{
                            Gson gson = new Gson();
                            String json = gson.toJson(place);
                            utilDomi.POSTrequest(1,"https://iterepi.firebaseio.com/sellers/"+user_id+"/places/.json",json);
                        }

                ).start();

                addCategoryDialog.finish();
                break;


        }
    }

    @Override
    public void onResponse(int callbackID, String response) {

    }
}
