package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
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

    private Seller seller;

    public AddCategoryController(AddCategoryDialog activity) {
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        activity.getAddCategoryBtn().setOnClickListener(this);
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
                String id = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id).child("places").push().getKey();
                String name = activity.getCategoryNameTF().getEditText().getText().toString();
                Place place = seller.getPlaces()[activity.getPlaceOfProductSP().getSelectedItemPosition()];


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
                                            +"/places/"+activity.getPlaceOfProductSP().getSelectedItemPosition()+"/categories/"
                                            +place.numCategories()+".json",json);
                                }

                        ).start();

                        Intent s = new Intent(activity, SeePlaceActivity.class);
                        s.putExtra("placePosition",0);
                        activity.startActivity(s);
                        activity.finish();
                        break;
                    }
                }

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

                activity.runOnUiThread(
                        ()->{
                            if(seller!=null){
                                ArrayAdapter<Place> adp1 = new ArrayAdapter<Place>(this.activity, android.R.layout.simple_list_item_1, seller.getPlaces());
                                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                this.activity.getPlaceOfProductSP().setAdapter(adp1);
                            }
                        }
                );

                break;
        }
    }

}
