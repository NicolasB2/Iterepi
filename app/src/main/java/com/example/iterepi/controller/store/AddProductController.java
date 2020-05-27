package com.example.iterepi.controller.store;

import android.view.View;

import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddProductDialog;

public class AddProductController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener {

    private AddProductDialog activity;

    public AddProductController(AddProductDialog activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResponse(int callbackID, String response) {

    }
}
