package com.example.iterepi.controller;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.iterepi.R;
import com.example.iterepi.view.LoginUserActivity;
import com.example.iterepi.view.RegisterMenuActivity;
import com.example.iterepi.view.RegisterUserEmailActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterMenuController implements View.OnClickListener {

    private RegisterMenuActivity activity;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    public final static int RC_SIGN_IN = 1;

    public RegisterMenuController(RegisterMenuActivity activity) {

        this.activity = activity;

        activity.getFacebookBtn().setOnClickListener(this);
        activity.getEmailBtn().setOnClickListener(this);
        activity.getGoogleBtn().setOnClickListener(this);
        activity.getLoginBtn().setOnClickListener(this);

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

            case R.id.emailBtn:
                i = new Intent(activity, RegisterUserEmailActivity.class);
                activity.startActivity(i);
                break;

            case R.id.facebookBtn:
                break;

            case R.id.googleBtn:
                signIn();
                break;

            case R.id.loginBtn:
                i = new Intent(activity, LoginUserActivity.class);
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
