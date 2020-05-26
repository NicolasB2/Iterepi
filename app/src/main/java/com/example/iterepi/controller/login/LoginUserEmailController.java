package com.example.iterepi.controller.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.login.LoginUserEmailActivity;
import com.example.iterepi.view.user.UserFeedActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUserEmailController implements View.OnClickListener {

    private LoginUserEmailActivity activity;
    private boolean checkEmail;
    private boolean checkPass;

    public LoginUserEmailController(LoginUserEmailActivity activity) {

        this.activity = activity;

        activity.getUpdate_DataBtn().setOnClickListener(this);
        activity.getForgotPassUser().setOnClickListener(this);

        listeners();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.updateDataBtn:

                login();
                break;

            case R.id.forgotPassUser:
                break;
        }
    }

    public void login() {

        String email = activity.getEmailLoginUserTF().getEditText().getText().toString();
        String password = activity.getPassLoginUserTF().getEditText().getText().toString();

        if (email.isEmpty()) {

            putError(activity.getEmailLoginUserTF(), activity.getString(R.string.empty_field));
            checkEmail = false;

        }

        if (password.isEmpty()) {

            putError(activity.getPassLoginUserTF(), activity.getString(R.string.empty_field));
            checkPass = false;

        }


        if (checkEmail && checkPass) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnSuccessListener(s -> {
                String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Intent i = new Intent(activity, UserFeedActivity.class);
                activity.startActivity(i);
                activity.finish();

            }).addOnFailureListener(f -> {

                Log.i("FAIL ON LOGIN BY EMAIL", f.getLocalizedMessage());
                Snackbar.make(activity.getUpdate_DataBtn(), f.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();

            });
        }
    }

    public void putError(TextInputLayout txtLay, String error) {

        txtLay.setError(error);

    }

    public void removeError(TextInputLayout txtLay) {

        txtLay.setError("");
        txtLay.setErrorEnabled(false);

    }

    public void listeners() {

        activity.getEmailLoginUserTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String x = activity.getEmailLoginUserTF().getEditText().getText().toString();

                if (!x.isEmpty()) {

                    removeError(activity.getEmailLoginUserTF());
                    checkEmail = true;

                }


            }
        });

        activity.getPassLoginUserTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String x = activity.getPassLoginUserTF().getEditText().toString();

                if (!x.isEmpty()) {

                    removeError(activity.getPassLoginUserTF());
                    checkPass = true;

                }

            }
        });


    }
}
