package com.example.iterepi.controller;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        loadPlaces();
    }

    private void loadPlaces(){
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

    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID) {
            case 1:
                Gson g = new Gson();
                Type type = new TypeToken<HashMap<String, Place>>(){}.getType();
                HashMap<String,Place> myPlace = g.fromJson(response,type);

                activity.runOnUiThread(
                        ()->{
                            for (String key: myPlace.keySet()){
                                Place p = myPlace.get(key);

                                activity.getAdapter().addPlace(p);
                            }
                        }
                );
                break;
        }
    }
}
