package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.MyPlacesActivity;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeePlaceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.io.Serializable;

public class seeCategoryController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener {

    private SeeCategoryActivity activity;
    private HTTPSWebUtilDomi utilDomi;


    public seeCategoryController(SeeCategoryActivity activity) {
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        this.utilDomi.setListener(this);
        this.activity.getBackBtn().setOnClickListener(this);
        this.activity.getUpdateDataBtn().setOnClickListener(this);

        if(this.activity.getCategory() == null){
            loadCategory();
        }
    }

    private void loadCategory() {
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id
                            +"/places/"+activity.getPlacePosition()+"/categories/"+activity.getCategoryPosition()+".json";
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

            case R.id.updateDataBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String name = activity.getCategoryNameTF().getEditText().getText().toString();

                if(user_id!=null){

                    if(name.equals("") ){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.name),Toast.LENGTH_LONG).show();
                        break;
                    }

                    else {

                        activity.getCategory().setName(name);

                        new Thread(
                                ()->{
                                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id
                                            +"/places/"+activity.getPlacePosition()+"/categories/"+activity.getCategoryPosition()+".json";
                                    Gson gson = new Gson();
                                    String json = gson.toJson(activity.getCategory());
                                    utilDomi.PUTrequest(1,request,json);
                                }
                        ).start();

                        Toast.makeText(activity,activity.getString(R.string.update_successful),Toast.LENGTH_LONG).show();
                        Intent s = new Intent(activity, SeePlaceActivity.class);
                        s.putExtra("placePosition",activity.getPlacePosition());
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
                Category category = gson.fromJson(response, Category.class);

                activity.runOnUiThread(
                        ()->{
                            if(category!=null){
                                activity.setCategory(category);
                                activity.getCategoryNameTV().setText(category.getName());
                                activity.getCategoryNameTF().getEditText().setText(category.getName());
                            }
                        }
                );
                break;
        }
    }
}
