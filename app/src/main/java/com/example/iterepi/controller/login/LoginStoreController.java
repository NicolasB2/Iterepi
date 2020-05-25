package com.example.iterepi.controller.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.login.LoginStoreActivity;
import com.example.iterepi.view.login.RegisterStoreActivity;
import com.example.iterepi.view.store.StoreHomeActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class LoginStoreController implements View.OnClickListener {

    private LoginStoreActivity activity;

    public LoginStoreController(LoginStoreActivity activity) {

        this.activity = activity;

        activity.getUpdateDataBtn().setOnClickListener(this);
        activity.getRegisterHereBtn().setOnClickListener(this);
        activity.getForgotPassBtn().setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.updateDataBtn:

                String email = activity.getEmail().getEditText().getText().toString();
                String password = activity.getPass().getEditText().getText().toString();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnSuccessListener(s -> {

                    Intent x = new Intent(activity, StoreHomeActivity.class);
                    activity.startActivity(x);
                    activity.finish();


                }).addOnFailureListener(f -> {

                    Log.i("FAIL ON LOGIN BY EMAIL", f.getLocalizedMessage());
                    Snackbar.make(activity.getUpdateDataBtn(), f.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();

                });

                break;

            case R.id.registerHereStore:

                i = new Intent(activity, RegisterStoreActivity.class);
                activity.startActivity(i);

                break;

            case R.id.forgotPass:

                break;


        }

    }
}
