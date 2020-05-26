package com.example.iterepi.controller.store;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddCategoryController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private AddCategoryDialog activity;
    private HTTPSWebUtilDomi utilDomi;

    public static final int SEARCH_CALLBACK = 1;
    public static final int SEND_CALLBACK = 2;

    private List<Place> places = new ArrayList<Place>();

    public AddCategoryController(AddCategoryDialog activity) {
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        activity.getAddCategoryBtn().setOnClickListener(this);
        activity.getCloseBtn().setOnClickListener(this);
        loadPlaces();
    }


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.updateDataBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String id = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id).child("places").push().getKey();
                String name = activity.getCategoryNameTF().getEditText().getText().toString();
                Place place = places.get(activity.getPlaceSP().getSelectedItemPosition());


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
                                            +"/places/"+place.getId()+"/categories/"+(place.getCategories().length-1)+".json",json);
                                }

                        ).start();
                        activity.finish();

                        break;
                    }
                }

            case R.id.closeBtn:
                Log.e(">>>","close");
                activity.finish();
                break;


        }
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID) {
            case SEARCH_CALLBACK:
                Gson g = new Gson();
                Type type = new TypeToken<HashMap<String,Place>>(){}.getType();
                HashMap<String,Place> myPlace = g.fromJson(response,type);


                for (String key: myPlace.keySet()){
                    Place p = myPlace.get(key);
                    places.add(p);
                }

                activity.runOnUiThread(
                        ()->{
                            ArrayAdapter<Place> adp1 = new ArrayAdapter<Place>(activity, android.R.layout.simple_spinner_item, places);
                            activity.getPlaceSP().setAdapter(adp1);
                        }
                );


                break;
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
