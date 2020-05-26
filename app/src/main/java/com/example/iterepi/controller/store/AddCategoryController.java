package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.SeePlaceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class AddCategoryController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private AddCategoryDialog activity;
    private HTTPSWebUtilDomi utilDomi;

    public static final int SEARCH_CALLBACK = 1;
    public static final int SEND_CALLBACK = 2;

    //private Place[]  places = new Place[0];

    public AddCategoryController(AddCategoryDialog activity) {
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        activity.getAddCategoryBtn().setOnClickListener(this);
        activity.getCloseBtn().setOnClickListener(this);
        //loadPlaces();
    }


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.updateDataBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String id = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id).child("places").push().getKey();
                String name = activity.getCategoryNameTF().getEditText().getText().toString();
                //Place place = places[activity.getPlaceSP().getSelectedItemPosition()];
                Place place = activity.getPlace();

                if(id != null){
                    if (name.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.name),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if(place==null){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.place),Toast.LENGTH_LONG).show();
                        break;
                    }

                    else{

                        Category category = new Category();
                        category.setId(id);
                        category.setName(name);


                        new Thread(
                                ()->{
                                    Gson gson = new Gson();
                                    String json = gson.toJson(category);
                                    utilDomi.PUTrequest(SEND_CALLBACK,"https://iterepi.firebaseio.com/sellers/"+user_id
                                            +"/places/"+activity.getPlacePosition()+"/categories/"+activity.getPlace().getCategories().length+".json",json);
                                }

                        ).start();

                        Intent s = new Intent(activity, SeePlaceActivity.class);
                        s.putExtra("placePosition",activity.getPlacePosition());
                        activity.startActivity(s);
                        activity.finish();
                        break;
                    }
                }

            case R.id.closeBtn:
                Intent s = new Intent(activity, SeePlaceActivity.class);
                s.putExtra("place",activity.getPlace());
                s.putExtra("placePosition",activity.getPlacePosition());
                activity.startActivity(s);
                activity.finish();
                break;


        }
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID) {

        }
    }

    public void loadPlaces(){
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id+"/places/.json";
                    utilDomi.GETrequest(SEARCH_CALLBACK,request);
                }
        ).start();
    }
}
