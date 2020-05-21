package com.example.iterepi.controller;

import android.content.Intent;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.login.LoginStoreActivity;
import com.example.iterepi.view.store.StoreHomeActivity;

public class LoginStoreController implements View.OnClickListener {

    private LoginStoreActivity activity;

    public LoginStoreController(LoginStoreActivity activity) {

        this.activity = activity;

        activity.getUpdateDataBtn().setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.updateDataBtn:

                i = new Intent(activity, StoreHomeActivity.class);
                activity.startActivity(i);

                break;


        }

    }
}
