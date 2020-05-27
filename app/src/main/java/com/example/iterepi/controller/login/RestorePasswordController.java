package com.example.iterepi.controller.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.view.login.RestorePasswordActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class RestorePasswordController implements View.OnClickListener {

    private RestorePasswordActivity activity;
    private boolean validRestore;


    public RestorePasswordController(RestorePasswordActivity activity) {

        this.activity = activity;

        activity.getRestoreBtn().setOnClickListener(this);
        activity.getBackBtn().setOnClickListener(this);

        listeners();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.restorePassBtn:
                restorePass();
                break;

            case R.id.backBtn:
                activity.onBackPressed();
                break;

        }

    }

    public void restorePass() {

        String email = activity.getEmailTF().getEditText().getText().toString();

        if (email.isEmpty()) {

            putError(activity.getEmailTF(), activity.getString(R.string.empty_field));

        }


        if (validRestore) {

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Snackbar.make(activity.getRestoreBtn(), activity.getString(R.string.restore_pass_sent), Snackbar.LENGTH_SHORT).show();
                    } else {

                        Snackbar.make(activity.getRestoreBtn(), task.getException().getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();

                    }
                }
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

        activity.getEmailTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String x = activity.getEmailTF().getEditText().getText().toString();

                if (!x.isEmpty()) {

                    validRestore = true;
                    removeError(activity.getEmailTF());

                } else {

                    validRestore = false;
                    putError(activity.getEmailTF(), activity.getString(R.string.empty_field));

                }

            }
        });

    }
}
