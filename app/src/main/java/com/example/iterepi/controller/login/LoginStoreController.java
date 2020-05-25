package com.example.iterepi.controller.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.login.LoginStoreActivity;
import com.example.iterepi.view.login.RegisterStoreActivity;
import com.example.iterepi.view.store.StoreHomeActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginStoreController implements View.OnClickListener {

    private LoginStoreActivity activity;
    private boolean checkEmail;
    private boolean checkPass;

    public LoginStoreController(LoginStoreActivity activity) {

        this.activity = activity;

        activity.getUpdateDataBtn().setOnClickListener(this);
        activity.getRegisterHereBtn().setOnClickListener(this);
        activity.getForgotPassBtn().setOnClickListener(this);

        listeners();

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.updateDataBtn:

                login();

                break;

            case R.id.registerHereStore:

                i = new Intent(activity, RegisterStoreActivity.class);
                activity.startActivity(i);

                break;

            case R.id.forgotPass:

                break;


        }

    }

    public void login() {

        String email = activity.getEmail().getEditText().getText().toString();
        String password = activity.getPass().getEditText().getText().toString();


        if (email.isEmpty()) {

            putError(activity.getEmail(), activity.getString(R.string.empty_field));
            checkEmail = false;

        }

        if (password.isEmpty()) {

            putError(activity.getPass(), activity.getString(R.string.empty_field));
            checkPass = false;

        }


        if (checkEmail && checkPass) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnSuccessListener(s -> {

                Intent x = new Intent(activity, StoreHomeActivity.class);
                activity.startActivity(x);
                activity.finish();


            }).addOnFailureListener(f -> {

                Log.i("FAIL ON LOGIN BY EMAIL", f.getLocalizedMessage());
                Snackbar.make(activity.getUpdateDataBtn(), f.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();

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

        activity.getEmail().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String x = activity.getEmail().getEditText().getText().toString();

                if (!x.isEmpty()) {

                    removeError(activity.getEmail());
                    checkEmail = true;

                }


            }
        });

        activity.getPass().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String x = activity.getPass().getEditText().toString();

                if (!x.isEmpty()) {

                    removeError(activity.getPass());
                    checkPass = true;

                }

            }
        });


    }

}
