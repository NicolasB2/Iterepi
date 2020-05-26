package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.example.iterepi.view.store.SeePlaceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.Serializable;

public class seePlaceController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private SeePlaceActivity activity;
    private HTTPSWebUtilDomi utilDomi;

    public seePlaceController(SeePlaceActivity activity) {
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        this.utilDomi.setListener(this);
        activity.getAddMethodBtn().setOnClickListener(this);
        activity.getBackBtn().setOnClickListener(this);
        activity.getUpdateDataBtn().setOnClickListener(this);

        if(activity.getPlace() == null){
            loadPlace();
        }
    }

    private void loadPlace() {
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id+"/places/"+activity.getPlacePosition()+".json";
                    utilDomi.GETrequest(1,request);
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
                i = new Intent(activity, AddCategoryDialog.class);
                i.putExtra("place", (Serializable) activity.getPlace());
                i.putExtra("placePosition",activity.getPlacePosition());
                activity.startActivity(i);
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

                        activity.getPlace().setLocation(location);
                        activity.getPlace().setName(name);

                        new Thread(
                                ()->{
                                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id+"/places/"+activity.getPlacePosition()+".json";
                                    Gson gson = new Gson();
                                    String json = gson.toJson(activity.getPlace());
                                    utilDomi.PUTrequest(1,request,json);
                                }
                        ).start();

                        Toast.makeText(activity,activity.getString(R.string.update_successful),Toast.LENGTH_LONG).show();
                        Intent s = new Intent(activity, MyPlacesActivity.class);
                        activity.startActivity(s);
                        activity.finish();
                    }

                }
                break;
        }
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID) {
            case 1:
                Gson gson = new Gson();
                Place place = gson.fromJson(response, Place.class);

                activity.runOnUiThread(
                        ()->{
                            if(place!=null){
                                activity.setPlace(place);
                                activity.getPlaceNameTV().setText(place.getName());
                                activity.getPlaceNameTF().getEditText().setText(place.getName());
                                activity.getPlaceLocationTF().getEditText().setText(place.getLocation());
                            }
                        }
                );
                break;
        }
    }

}
