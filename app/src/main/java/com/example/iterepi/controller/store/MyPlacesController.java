package com.example.iterepi.controller.store;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.iterepi.R;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyPlacesController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private MyPlacesActivity activity;
    private HTTPSWebUtilDomi utilDomi;


    public MyPlacesController(MyPlacesActivity activity) {
        this.activity = activity;
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        activity.getAddMethodBtn().setOnClickListener(this);
        activity.getBackBtn().setOnClickListener(this);
        loadPlaces();
    }

    public void loadPlaces(){
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id+"/places/.json";
                    utilDomi.GETrequest(1,request);
                }
        ).start();
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.backBtn:
                Log.e(">>>","close");
                activity.finish();
                break;
            case R.id.addMethodBtn:
                i = new Intent(activity, AddPlaceDialog.class);
                i.putExtra("places", (Serializable) activity.getAdapter().getPlaces());
                activity.startActivity(i);
                break;
        }
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID) {
            case 1:
                Gson gson = new Gson();
                Place[] places = gson.fromJson(response, Place[].class);

                activity.runOnUiThread(
                        ()->{
                            if(places!=null){
                                for (int i = 0;i < places.length;i++){
                                    Place p = places[i];
                                    activity.getAdapter().addPlace(p);
                                }
                            }
                        }
                );
                break;
        }
    }
}
