package com.example.iterepi.controller.login;

import android.content.Intent;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.login.LoginStoreActivity;
import com.example.iterepi.view.login.LoginUserActivity;
import com.example.iterepi.view.login.MainActivity;

public class MainController implements View.OnClickListener {

    private MainActivity activity;

    public MainController(MainActivity activity) {
        this.activity = activity;
        activity.getBuyerBtn().setOnClickListener(this);
        activity.getStoreBtn().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.buyerBtn:
                i = new Intent(activity, LoginUserActivity.class);
                activity.startActivity(i);
                break;
            default:
                i = new Intent(activity, LoginStoreActivity.class);
                activity.startActivity(i);
                break;
        }

    }
}
