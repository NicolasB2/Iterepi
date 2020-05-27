package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.login.SplashScreenActivity;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class AddPlaceController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private AddPlaceDialog activity;
    private HTTPSWebUtilDomi utilDomi;

    public static final int SEARCH_CALLBACK = 1;
    public static final int SEND_CALLBACK = 2;

    private Seller seller;

    public AddPlaceController(AddPlaceDialog activity) {
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        this.utilDomi.setListener(this);
        activity.getAddPlaceBtn().setOnClickListener(this);
        activity.getCloseBtn().setOnClickListener(this);

        loadSeller();
    }

    private void loadSeller() {
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id+"/.json";
                    utilDomi.GETrequest(SEARCH_CALLBACK,request);
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

                        new Thread(
                                ()->{
                                    Gson gson = new Gson();
                                    String json = gson.toJson(place);
                                    utilDomi.PUTrequest(SEND_CALLBACK,"https://iterepi.firebaseio.com/sellers/"+user_id+"/places/"
                                            +seller.numPlaces()+".json",json);
                                }

                        ).start();
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

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID){
            case SEND_CALLBACK:
                break;
            case SEARCH_CALLBACK:
                Gson gson = new Gson();
                this.seller = gson.fromJson(response, Seller.class);
                break;
        }
    }
}
