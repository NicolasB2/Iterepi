package com.example.iterepi.controller.store;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.io.Serializable;

class MyCategoriesController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private MyCategoriesActivity activity;
    private HTTPSWebUtilDomi utilDomi;

    public MyCategoriesController(MyCategoriesActivity activity) {
        this.activity = activity;
        activity.getAddMethodBtn().setOnClickListener(this);
        activity.getBackBtn().setOnClickListener(this);
        this.utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        loadCategories();
    }

    private void loadCategories() {
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id+"/places/"+activity.getPlacePosition()+"/categories/.json";
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
                i.putExtra("categories", (Serializable) activity.getAdapter().getCategories());
                i.putExtra("placePosition",activity.getPlacePosition());
                activity.startActivity(i);
                activity.finish();
                break;
        }
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID) {
            case 1:
                Gson gson = new Gson();
                Category[] categories = gson.fromJson(response, Category[].class);

                activity.runOnUiThread(
                        ()->{
                            if(categories!=null){
                                for (int i = 0;i < categories.length;i++){
                                    Category category = categories[i];
                                    activity.getAdapter().addCategory(category);
                                }
                            }
                        }
                );
                break;
        }
    }
}
