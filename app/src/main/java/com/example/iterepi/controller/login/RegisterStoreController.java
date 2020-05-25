package com.example.iterepi.controller.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.view.login.RegisterStoreActivity;
import com.example.iterepi.view.store.StoreHomeActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterStoreController implements View.OnClickListener {

    private RegisterStoreActivity activity;
    private boolean checkName;
    private boolean checkConfEmail;
    private boolean checkEmail;
    private boolean checkPass;
    private boolean checkNit;

    public RegisterStoreController(RegisterStoreActivity activity) {

        this.activity = activity;

        activity.getRegisterBtn().setOnClickListener(this);


        listeners();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.registerBtn:
                storeRegister();
                break;


        }


    }


    public void storeRegister() {

        String name = activity.getNameStoreTF().getEditText().getText().toString();
        String confEmail = activity.getConfEmailStoreRegTF().getEditText().getText().toString();
        String email = activity.getEmailStoreRegTF().getEditText().getText().toString();
        String pass = checkPassword();
        String nit = activity.getNitStoreRegTF().getEditText().getText().toString();

        if (name.isEmpty()) {
            putError(activity.getNameStoreTF(), activity.getText(R.string.empty_field).toString());
            checkName = false;
        }

        if (nit.isEmpty()) {
            putError(activity.getNitStoreRegTF(), activity.getString(R.string.empty_field));
            checkNit = false;
        }

        if (email.isEmpty()) {

            putError(activity.getEmailStoreRegTF(), activity.getString(R.string.empty_field));
            checkEmail = false;

        }
        if (confEmail.isEmpty()) {

            putError(activity.getConfEmailStoreRegTF(), activity.getString(R.string.empty_field));
            checkConfEmail = false;

        }

        boolean checkTerms;
        if (!activity.getTermsCB().isChecked()) {

            Snackbar.make(activity.getRegisterBtn(), activity.getText(R.string.must_accept_terms), Snackbar.LENGTH_LONG).show();
            checkTerms = false;
        } else {
            checkTerms = true;
        }

        // Register a store with all data validated.
        if (checkTerms && checkNit && checkName && checkPass && checkEmail && checkConfEmail) {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnSuccessListener(authResult -> {

                // Add to database code.


                // Start StoreHomeActivity

                Intent i = new Intent(activity, StoreHomeActivity.class);
                activity.startActivity(i);
                activity.finish();


            }).addOnFailureListener(f -> {

                // If create user fails
                Snackbar.make(activity.getRegisterBtn(), f.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();

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

    // Checks if password has letters, numbers and if its length is greater than six.
    public String checkPassword() {

        String password = "";
        String passTF = activity.getPasswordStoreRegTF().getEditText().getText().toString();
        String confPassTF = activity.getConfPasswordStoreRegTF().getEditText().getText().toString();

        if (passTF.length() >= 6) {

            if (passTF.matches(".*\\d.*") && passTF.matches(".*[a-zA-Z].*")) {

                removeError(activity.getPasswordStoreRegTF());

                if (!passTF.equals(confPassTF)) {

                    putError(activity.getConfPasswordStoreRegTF(), activity.getString(R.string.pass_dont_match));
                    checkPass = false;

                } else {
                    removeError(activity.getConfPasswordStoreRegTF());
                    password = activity.getPasswordStoreRegTF().getEditText().getText().toString();
                    checkPass = true;
                }


            } else {

                putError(activity.getPasswordStoreRegTF(), activity.getString(R.string.pass_must_contain));
                checkPass = false;

            }
        } else {
            putError(activity.getPasswordStoreRegTF(), activity.getString(R.string.min_six_characters));
            checkPass = false;
        }

        return password;
    }

    // Listeners of all TextInputLayouts for a responsive interface.
    public void listeners() {

        activity.getNameStoreTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getNameStoreTF().getEditText().getText().toString();
                if (!t.isEmpty()) {
                    removeError(activity.getNameStoreTF());
                    checkName = true;
                }
            }
        });

        activity.getNitStoreRegTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getNitStoreRegTF().getEditText().getText().toString();
                if (!t.isEmpty()) {

                    removeError(activity.getNitStoreRegTF());
                    checkNit = true;

                }
            }
        });

        activity.getEmailStoreRegTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getEmailStoreRegTF().getEditText().getText().toString();
                if (!t.isEmpty()) {

                    removeError(activity.getEmailStoreRegTF());
                    checkEmail = true;

                }

                if (!t.equals(activity.getConfEmailStoreRegTF().getEditText().getText().toString())) {

                    putError(activity.getConfEmailStoreRegTF(), activity.getString(R.string.email_dont_match));

                } else {

                    removeError(activity.getConfEmailStoreRegTF());

                }

            }
        });

        activity.getConfEmailStoreRegTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getConfEmailStoreRegTF().getEditText().getText().toString();
                if (!t.isEmpty()) {

                    if (t.equals(activity.getEmailStoreRegTF().getEditText().getText().toString())) {
                        removeError(activity.getConfEmailStoreRegTF());
                        checkConfEmail = true;
                    } else {

                        putError(activity.getConfEmailStoreRegTF(), activity.getString(R.string.email_dont_match));
                        checkConfEmail = false;

                    }
                }
            }
        });

        activity.getPasswordStoreRegTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (activity.getPasswordStoreRegTF().getEditText().getText().toString().length() >= 6) {
                    removeError(activity.getPasswordStoreRegTF());
                }

                String x = activity.getPasswordStoreRegTF().getEditText().getText().toString();

                if (!x.equals(activity.getConfPasswordStoreRegTF().getEditText().getText().toString())) {

                    putError(activity.getConfPasswordStoreRegTF(), activity.getString(R.string.pass_dont_match));

                } else {

                    removeError(activity.getConfPasswordStoreRegTF());

                }
            }
        });

        activity.getConfPasswordStoreRegTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String p = activity.getConfPasswordStoreRegTF().getEditText().getText().toString();

                if (p.equals(activity.getPasswordStoreRegTF().getEditText().getText().toString())) {

                    removeError(activity.getConfPasswordStoreRegTF());

                } else {

                    putError(activity.getConfPasswordStoreRegTF(), activity.getString(R.string.pass_dont_match));

                }

            }
        });

    }


}
