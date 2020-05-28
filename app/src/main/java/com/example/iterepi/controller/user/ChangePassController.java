package com.example.iterepi.controller.user;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.view.user.ChangesPassActivity;
import com.example.iterepi.view.user.UserProfileActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassController implements View.OnClickListener {

    private ChangesPassActivity activity;
    private boolean checkCurrent;
    private boolean checkNewPass;
    private boolean checkConfNewPass;

    public ChangePassController(ChangesPassActivity activity) {

        this.activity = activity;

        activity.getChangePassBtn().setOnClickListener(this);
        activity.getBackBtn().setOnClickListener(this);

        listeners();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.changePassBtn:

                changePass();

                break;

            case R.id.backBtn6:
                activity.onBackPressed();
                break;


        }

    }


    public void changePass() {

        String current = activity.getCurrentPass().getEditText().getText().toString();
        String newPass = activity.getNewPass().getEditText().getText().toString();
        String confNewPass = activity.getConfNewPass().getEditText().getText().toString();

        if (current.isEmpty()) {

            putError(activity.getCurrentPass(), activity.getString(R.string.empty_field));
            checkCurrent = false;

        } else {

            checkCurrent = true;

        }

        if (newPass.isEmpty()) {

            putError(activity.getNewPass(), activity.getString(R.string.empty_field));
            checkNewPass = false;

        } else {

            checkNewPass = true;

        }

        if (confNewPass.isEmpty()) {

            putError(activity.getConfNewPass(), activity.getString(R.string.empty_field));
            checkConfNewPass = false;
        } else {

            checkConfNewPass = true;
        }


        String newOne = checkPassword();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if (checkCurrent && checkConfNewPass && checkNewPass) {

            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), current);

            user.reauthenticate(credential).addOnSuccessListener(s -> {

                user.updatePassword(newOne).addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        Toast.makeText(activity.getApplicationContext(), activity.getString(R.string.pass_changed), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(activity, UserProfileActivity.class);
                        activity.startActivity(i);
                        activity.finishAffinity();


                    }

                });


            }).addOnFailureListener(f -> {

                Snackbar.make(activity.getChangePassBtn(), activity.getString(R.string.error_change_pass), Snackbar.LENGTH_SHORT).show();


            });


        }


    }


    // Checks if password has letters, numbers and if its length is greater than six.
    public String checkPassword() {

        String password = "";
        String passTF = activity.getNewPass().getEditText().getText().toString();
        String confPassTF = activity.getConfNewPass().getEditText().getText().toString();

        if (passTF.length() >= 6) {

            if (passTF.matches(".*\\d.*") && passTF.matches(".*[a-zA-Z].*")) {

                removeError(activity.getNewPass());

                if (!passTF.equals(confPassTF)) {

                    putError(activity.getConfNewPass(), activity.getString(R.string.pass_dont_match));
                    checkNewPass = false;

                } else {
                    removeError(activity.getConfNewPass());
                    password = activity.getNewPass().getEditText().getText().toString();
                    checkNewPass = true;
                }


            } else {

                putError(activity.getNewPass(), activity.getString(R.string.pass_must_contain));
                checkNewPass = false;

            }
        } else {
            putError(activity.getNewPass(), activity.getString(R.string.min_six_characters));
            checkNewPass = false;
        }

        return password;
    }


    public void putError(TextInputLayout txtLay, String error) {

        txtLay.setError(error);

    }

    public void removeError(TextInputLayout txtLay) {

        txtLay.setError("");
        txtLay.setErrorEnabled(false);

    }


    public void listeners() {


        activity.getCurrentPass().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String t = activity.getCurrentPass().getEditText().getText().toString();

                if (!t.isEmpty()) {

                    removeError(activity.getCurrentPass());
                    checkCurrent = true;

                } else {

                    putError(activity.getCurrentPass(), activity.getString(R.string.empty_field));
                    checkCurrent = false;

                }


            }
        });


        activity.getNewPass().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (activity.getNewPass().getEditText().getText().toString().length() >= 6) {
                    removeError(activity.getNewPass());
                } else {

                    putError(activity.getNewPass(), activity.getString(R.string.min_six_characters));

                }

                String x = activity.getNewPass().getEditText().getText().toString();

                if (!x.equals(activity.getConfNewPass().getEditText().getText().toString())) {

                    putError(activity.getConfNewPass(), activity.getString(R.string.pass_dont_match));

                } else {

                    removeError(activity.getConfNewPass());

                }

            }
        });

        activity.getConfNewPass().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String t = activity.getConfNewPass().getEditText().getText().toString();

                if (t.equals(activity.getNewPass().getEditText().getText().toString())) {

                    removeError(activity.getConfNewPass());

                } else {

                    putError(activity.getConfNewPass(), activity.getString(R.string.pass_dont_match));

                }

            }


        });


    }

}
