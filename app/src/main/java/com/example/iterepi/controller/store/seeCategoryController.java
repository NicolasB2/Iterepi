package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeePlaceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class seeCategoryController implements View.OnClickListener{

    private SeeCategoryActivity activity;


    public seeCategoryController(SeeCategoryActivity activity) {
        this.activity = activity;
        this.activity.getBackBtn().setOnClickListener(this);
        this.activity.getUpdateDataBtn().setOnClickListener(this);

        if(this.activity.getCategory() == null){
            loadCategory();
        }
    }

    private void loadCategory() {
        String user_id = FirebaseAuth.getInstance().getUid();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("sellers").child(user_id)
                .child("places").child(activity.getPlaceId())
                .child("categories").child(activity.getCategoryId());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Category category =  dataSnapshot.getValue(Category.class);

                activity.runOnUiThread(
                        ()->{
                            if(category!=null){
                                activity.setCategory(category);
                                activity.getCategoryNameTV().setText(category.getName());
                                activity.getCategoryNameTF().getEditText().setText(category.getName());
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
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

                        FirebaseDatabase.getInstance().getReference()
                                .child("sellers").child(user_id)
                                .child("places").child(activity.getPlaceId())
                                .child("categories").child(activity.getCategoryId()).setValue(activity.getCategory());

                        Toast.makeText(activity,activity.getString(R.string.update_successful),Toast.LENGTH_LONG).show();
                        Intent s = new Intent(activity, SeePlaceActivity.class);
                        s.putExtra("placeId",activity.getPlaceId());
                        activity.startActivity(s);
                        activity.finish();
                    }
                }
                break;
        }
    }

}
