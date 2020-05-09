package com.example.iterepi.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.LoginStoreActivity;
import com.example.iterepi.view.MainActivity;
import com.example.iterepi.view.LoginUserActivity;

public class MainController implements View.OnClickListener {

    private MainActivity activity;

    public MainController(MainActivity activity) {
        this.activity = activity;
        activity.getBuyerBtn().setOnClickListener(this);
        activity.getStoreBtn().setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i(">>>>>>>>>>>", "onClick: 1");
        Intent i;
        switch (view.getId()) {
            case R.id.buyerBtn:
                Log.i(">>>>>>>>>>>", "onClick: 2");
                i = new Intent(activity, LoginUserActivity.class);
                activity.startActivity(i);
                break;
            default:
                Log.i(">>>>>>>>>>>", "onClick: 3");
                i = new Intent(activity, LoginStoreActivity.class);
                activity.startActivity(i);
                break;
        }

    }
}
