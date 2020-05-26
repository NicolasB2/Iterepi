package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class AddPlaceController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private AddPlaceDialog activity;
    private HTTPSWebUtilDomi utilDomi;

    public AddPlaceController(AddPlaceDialog activity) {
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        activity.getAddPlaceBtn().setOnClickListener(this);
        activity.getCloseBtn().setOnClickListener(this);
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
                                    utilDomi.PUTrequest(1,"https://iterepi.firebaseio.com/sellers/"+user_id+"/places/"+id+".json",json);
                                }

                        ).start();

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

    }
}
