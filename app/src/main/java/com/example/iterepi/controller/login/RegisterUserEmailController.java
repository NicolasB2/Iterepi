package com.example.iterepi.controller.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;

import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.view.login.RegisterUserEmailActivity;
import com.example.iterepi.view.user.UserFeedActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegisterUserEmailController implements View.OnClickListener {

    private RegisterUserEmailActivity activity;
    private boolean checkName;
    private boolean checkBirthday;
    private boolean checkEmail;
    private boolean checkPass;
    private boolean checkIdentification;
    private boolean checkGender;


    public RegisterUserEmailController(RegisterUserEmailActivity activity) {

        this.activity = activity;

        activity.getRegisterBtn().setOnClickListener(this);


        listeners();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.registerUserBtn:

                userRegister();

                break;


        }

    }


    public void openCalendar() {

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        DatePickerDialog dpd = new DatePickerDialog(activity.getRegisterBtn().getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                int fixedMonth = month + 1;
                String dateSelected = dayOfMonth + "/" + fixedMonth + "/" + year;
                activity.getBirthdayTF().getEditText().setText(dateSelected);

            }
        }, year, month, day);

        dpd.show();

    }

    public void userRegister() {

        String name = activity.getNameTF().getEditText().getText().toString();
        String birthday = activity.getBirthdayTF().getEditText().getText().toString();
        String email = activity.getEmailTF().getEditText().getText().toString();
        String identification = activity.getIdentificationTF().getEditText().getText().toString();
        String password = checkPassword();
        int gender = -1;

        if (activity.getRadioGroup().getCheckedRadioButtonId() == R.id.checkMan) {
            gender = Buyer.MAN;
            checkGender = true;
        } else if (activity.getRadioGroup().getCheckedRadioButtonId() == R.id.checkWoman) {
            gender = Buyer.WOMAN;
            checkGender = true;
        }

        if (name.isEmpty()) {
            putError(activity.getNameTF(), activity.getText(R.string.empty_field).toString());
            checkName = false;
        }

        if (birthday.isEmpty()) {
            activity.getBirthdayTF().setHelperText(activity.getString(R.string.empty_field));
            Snackbar.make(activity.getRegisterBtn(), activity.getString(R.string.select_birthday), Snackbar.LENGTH_SHORT).show();
            checkBirthday = false;
        }

        if (identification.isEmpty()) {
            putError(activity.getIdentificationTF(), activity.getString(R.string.empty_field));
            checkIdentification = false;
        }

        if (gender == -1) {
            Snackbar.make(activity.getRegisterBtn(), activity.getString(R.string.select_gender), Snackbar.LENGTH_SHORT).show();
            checkGender = false;
        }

        boolean checkTerms;
        if (!activity.getCheckTerms().isChecked()) {

            Snackbar.make(activity.getRegisterBtn(), activity.getText(R.string.must_accept_terms), Snackbar.LENGTH_LONG).show();
            checkTerms = false;
        } else {
            checkTerms = true;
        }

        if (email.isEmpty()) {

            putError(activity.getEmailTF(), activity.getString(R.string.empty_field));
            checkEmail = false;

        }

        // User Register with all data validated.
        if (checkTerms && checkGender && checkIdentification && checkBirthday && checkName && checkPass && checkEmail) {

            String bName = name;
            String bCedula = identification;
            String bEmail = email;
            String bPassword = password;
            String bPhoto = null;
            int bGender = gender;
            String bBirthday = birthday;

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(activity);

            if (acct == null) {
                String finalBPhoto = bPhoto;
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {

                    // Add to database code.

                    String id = FirebaseAuth.getInstance().getUid();
                    Buyer buyer = new Buyer(id, bName, bCedula, email, password, finalBPhoto, bGender, bBirthday, null, null);
                    FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                    // Start UserFeedActivity

                    Snackbar.make(activity.getRegisterBtn(), activity.getString(R.string.welcome), Snackbar.LENGTH_SHORT).show();
                    Intent i = new Intent(activity, UserFeedActivity.class);
                    activity.startActivity(i);
                    activity.finish();


                }).addOnFailureListener(f -> {

                    // If create user fails
                    Snackbar.make(activity.getRegisterBtn(), f.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();

                });

            } else {

                // Add to database code.

                String id = FirebaseAuth.getInstance().getUid();
                bPhoto = acct.getPhotoUrl().toString();
                bPhoto.replace("/s96-c/", "/s800-c/");
                Buyer buyer = new Buyer(id, bName, bCedula, email, password, bPhoto, bGender, bBirthday, null, null);
                FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                // Start UserFeedActivity

                Intent i = new Intent(activity, UserFeedActivity.class);
                activity.startActivity(i);
                activity.finish();


            }

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
        String passTF = activity.getPasswordTF().getEditText().getText().toString();
        String confPassTF = activity.getConfPasswordTF().getEditText().getText().toString();

        if (passTF.length() >= 6) {

            if (passTF.matches(".*\\d.*") && passTF.matches(".*[a-zA-Z].*")) {

                removeError(activity.getPasswordTF());

                if (!passTF.equals(confPassTF)) {

                    putError(activity.getConfPasswordTF(), activity.getString(R.string.pass_dont_match));
                    checkPass = false;

                } else {
                    removeError(activity.getConfPasswordTF());
                    password = activity.getPasswordTF().getEditText().getText().toString();
                    checkPass = true;
                }


            } else {

                putError(activity.getPasswordTF(), activity.getString(R.string.pass_must_contain));
                checkPass = false;

            }
        } else {
            putError(activity.getPasswordTF(), activity.getString(R.string.min_six_characters));
            checkPass = false;
        }

        return password;
    }


    // Listeners of all TextInputLayouts for a responsive interface.
    public void listeners() {

        activity.getBirthdayTF().setEndIconOnClickListener(v -> {

            openCalendar();

        });

        activity.getBirthdayTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getBirthdayTF().getEditText().getText().toString();
                if (!t.isEmpty()) {
                    activity.getBirthdayTF().setHelperText("");
                    checkBirthday = true;
                }
            }
        });

        activity.getNameTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getNameTF().getEditText().getText().toString();
                if (!t.isEmpty()) {
                    removeError(activity.getNameTF());
                    checkName = true;
                }
            }
        });

        activity.getIdentificationTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getIdentificationTF().getEditText().getText().toString();
                if (!t.isEmpty()) {

                    removeError(activity.getIdentificationTF());
                    checkIdentification = true;

                }
            }
        });

        activity.getEmailTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getEmailTF().getEditText().getText().toString();
                if (!t.isEmpty()) {

                    removeError(activity.getEmailTF());
                    checkEmail = true;

                }
            }
        });

        activity.getPasswordTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (activity.getPasswordTF().getEditText().getText().toString().length() >= 6) {
                    removeError(activity.getPasswordTF());
                } else {

                    putError(activity.getPasswordTF(), activity.getString(R.string.min_six_characters));

                }

                String x = activity.getPasswordTF().getEditText().getText().toString();

                if (!x.equals(activity.getConfPasswordTF().getEditText().getText().toString())) {

                    putError(activity.getConfPasswordTF(), activity.getString(R.string.pass_dont_match));

                } else {

                    removeError(activity.getConfPasswordTF());

                }

            }
        });

        activity.getConfPasswordTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String t = activity.getConfPasswordTF().getEditText().getText().toString();

                if (t.equals(activity.getPasswordTF().getEditText().getText().toString())) {

                    removeError(activity.getConfPasswordTF());

                } else {

                    putError(activity.getConfPasswordTF(), activity.getString(R.string.pass_dont_match));

                }

            }


        });


    }
}

