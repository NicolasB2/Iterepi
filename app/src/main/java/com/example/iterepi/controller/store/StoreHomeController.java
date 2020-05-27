package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddCategoryDialog;
import com.example.iterepi.view.store.AddPlaceDialog;
import com.example.iterepi.view.store.AddProductDialog;
import com.example.iterepi.view.store.StoreHomeActivity;

import java.io.Serializable;

public class StoreHomeController implements View.OnClickListener{

    private StoreHomeActivity activity;

    public StoreHomeController(StoreHomeActivity activity) {
        this.activity = activity;
        this.activity.getAddPlaceBtn().setOnClickListener(this);
        this.activity.getAddCategoryBtn().setOnClickListener(this);
        this.activity.getAddProductBtn().setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.addPlaceBtn:
                i = new Intent(activity, AddPlaceDialog.class);
                activity.startActivity(i);
                break;
            case R.id.addCategoryBtn:
                i = new Intent(activity, AddCategoryDialog.class);
                activity.startActivity(i);
                break;
            case R.id.addProductBtn:
                i = new Intent(activity, AddProductDialog.class);
                activity.startActivity(i);
                break;
        }
    }

}
