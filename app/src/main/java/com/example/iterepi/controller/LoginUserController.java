package com.example.iterepi.controller;

import android.content.Intent;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.LoginUserActivity;
import com.example.iterepi.view.LoginUserEmailActivity;

public class LoginUserController implements View.OnClickListener {

    private LoginUserActivity activity;

    public LoginUserController(LoginUserActivity activity) {
        this.activity = activity;
        activity.getFacebookBtn().setOnClickListener(this);
        activity.getGoogleBtn().setOnClickListener(this);
        activity.getEmailBtn().setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.facebookBtn:
                break;

            case R.id.googleBtn:

                break;

            case R.id.emailBtn:
                i = new Intent(activity, LoginUserEmailActivity.class);
                activity.startActivity(i);
                break;


        }
    }
}
