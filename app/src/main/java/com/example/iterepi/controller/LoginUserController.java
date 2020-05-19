package com.example.iterepi.controller;

import android.content.Intent;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.login.LoginUserActivity;
import com.example.iterepi.view.login.LoginUserEmailActivity;
import com.example.iterepi.view.login.RegisterMenuActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUserController implements View.OnClickListener {

    private LoginUserActivity activity;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    public final static int RC_SIGN_IN = 1;

    public LoginUserController(LoginUserActivity activity) {
        this.activity = activity;
        activity.getFacebookBtn().setOnClickListener(this);
        activity.getGoogleBtn().setOnClickListener(this);
        activity.getEmailBtn().setOnClickListener(this);
        activity.getRegisterTV().setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.deafault_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.facebookBtn:
                break;

            case R.id.googleBtn:
                signIn();
                break;

            case R.id.emailBtn:
                i = new Intent(activity, LoginUserEmailActivity.class);
                activity.startActivity(i);
                break;
            case R.id.registerTV:
                i = new Intent(activity, RegisterMenuActivity.class);
                activity.startActivity(i);
                break;
        }
    }

    private void signIn() {
        Intent i = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(i, RC_SIGN_IN);

    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }
}
