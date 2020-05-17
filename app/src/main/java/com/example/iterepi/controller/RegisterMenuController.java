package com.example.iterepi.controller;

import android.content.Intent;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.LoginUserActivity;
import com.example.iterepi.view.RegisterMenuActivity;
import com.example.iterepi.view.RegisterUserEmailActivity;

public class RegisterMenuController implements View.OnClickListener {

    private RegisterMenuActivity activity;

    public RegisterMenuController(RegisterMenuActivity activity) {

        this.activity = activity;

        activity.getFacebookBtn().setOnClickListener(this);
        activity.getEmailBtn().setOnClickListener(this);
        activity.getGoogleBtn().setOnClickListener(this);
        activity.getLoginBtn().setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()) {

            case R.id.emailBtn:
                i = new Intent(activity, RegisterUserEmailActivity.class);
                activity.startActivity(i);
                break;

            case R.id.facebookBtn:
                break;

            case R.id.googleBtn:
                break;


            case R.id.loginBtn:
                i = new Intent(activity, LoginUserActivity.class);
                activity.startActivity(i);
                break;

        }

    }
}
