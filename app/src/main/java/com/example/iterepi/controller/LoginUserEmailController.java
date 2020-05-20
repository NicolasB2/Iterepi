package com.example.iterepi.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.login.LoginUserEmailActivity;
import com.example.iterepi.view.user.UserFeedActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUserEmailController implements View.OnClickListener {

    private LoginUserEmailActivity activity;

    public LoginUserEmailController(LoginUserEmailActivity activity) {

        this.activity = activity;

        activity.getUpdate_DataBtn().setOnClickListener(this);
        activity.getForgotPassUser().setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.update_dataBtn:

                String email = activity.getEmailLoginUserTF().getEditText().getText().toString();
                String password = activity.getPassLoginUserTF().getEditText().getText().toString();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnSuccessListener(s -> {


                    Intent i = new Intent(activity, UserFeedActivity.class);
                    activity.startActivity(i);
                    activity.finish();


                }).addOnFailureListener(f -> {

                    Log.i("FAIL ON LOGIN BY EMAIL", f.getLocalizedMessage());
                    Snackbar.make(activity.getUpdate_DataBtn(), f.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();

                });

                break;

            case R.id.forgotPassUser:


                break;

        }

    }
}
