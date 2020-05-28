package com.example.iterepi.controller.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Cart;
import com.example.iterepi.model.Transaction;
import com.example.iterepi.view.login.RegisterUserEmailActivity;
import com.example.iterepi.view.user.UserFeedActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class RegisterUserEmailController implements View.OnClickListener {

    public static final int GALLERY_CALLBACK = 2;


    private RegisterUserEmailActivity activity;
    private boolean checkName;
    private boolean checkBirthday;
    private boolean checkEmail;
    private boolean checkPass;
    private boolean checkIdentification;
    private boolean checkGender;
    private Uri tempUri;
    private String photo;

    public RegisterUserEmailController(RegisterUserEmailActivity activity) {

        this.activity = activity;

        activity.getRegisterBtn().setOnClickListener(this);
        activity.getBackBtn().setOnClickListener(this);
        activity.getProfileImage().setOnClickListener(this);


        listeners();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.registerUserBtn:
                userRegister();

                break;

            case R.id.backBtn5:
                activity.onBackPressed();
                break;

            case R.id.profileImage2:
                Intent gal = new Intent(Intent.ACTION_GET_CONTENT);
                gal.setType("image/*");
                activity.startActivityForResult(gal, GALLERY_CALLBACK);
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
            int bGender = gender;
            String bBirthday = birthday;


            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (activity.getIntent().hasExtra("PROVIDER")) {

                String provider = (String)activity.getIntent().getExtras().get("PROVIDER");

                Log.e("PROVIDER",provider);

                if (provider.equals("GOOGLE")) {

                    // Add to database code.
                    String id = user.getUid();
                    if (tempUri != null) {

                        FirebaseStorage storage = FirebaseStorage.getInstance();

                        storage.getReference().child("buyers").child(id).child("photo").putFile(tempUri).addOnCompleteListener(task -> {

                            if (task.isSuccessful()) {

                                storage.getReference().child("buyers").child(id).child("photo").getDownloadUrl().addOnSuccessListener(uri -> {
                                    photo = uri.toString();
                                    Buyer buyer = new Buyer(id, bName, bCedula, email, photo, bGender, bBirthday, null, null, null);
                                    FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                                    goToFeed();

                                });
                            }
                        });
                    } else {
                        photo = user.getPhotoUrl().toString();
                        photo.replace("/s96-c/", "/s800-c/");

                        String transactionId = FirebaseDatabase.getInstance().getReference()
                                .child(id)
                                .push().getKey();
                        HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();

                        Buyer buyer = new Buyer(id, bName, bCedula, email, photo, bGender, bBirthday, transactions,new Cart("cart", null), null);
                        FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                        goToFeed();
                    }

                } else if (provider.equals("FACEBOOK")) {

                    // Add to database code.

                    String id = user.getUid();


                    if (tempUri != null) {

                        FirebaseStorage storage = FirebaseStorage.getInstance();

                        storage.getReference().child("buyers").child(id).child("photo").putFile(tempUri).addOnCompleteListener(task -> {

                            if (task.isSuccessful()) {

                                storage.getReference().child("buyers").child(id).child("photo").getDownloadUrl().addOnSuccessListener(uri -> {

                                    photo = uri.toString();

                                    String transactionId = FirebaseDatabase.getInstance().getReference()
                                            .child(id)
                                            .push().getKey();
                                    HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();

                                    Buyer buyer = new Buyer(id, bName, bCedula, email, photo, bGender, bBirthday, transactions, new Cart("cart", null), null);
                                    FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                                    goToFeed();

                                });
                            }

                        });

                    } else {
                        photo = user.getPhotoUrl().toString() + "?height=500";
                        String transactionId = FirebaseDatabase.getInstance().getReference()
                                .child(id)
                                .push().toString();
                        HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();


                        Buyer buyer = new Buyer(id, bName, bCedula, email, photo, bGender, bBirthday, transactions,  new Cart("cart", null), null);
                        FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                        goToFeed();
                    }
                }
            } else {

                // With email and password way
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {

                    // Add to database code.

                    String id = FirebaseAuth.getInstance().getUid();

                    if (tempUri != null) {

                        FirebaseStorage storage = FirebaseStorage.getInstance();

                        storage.getReference().child("buyers").child(id).child("photo").putFile(tempUri).addOnCompleteListener(task -> {

                            if (task.isSuccessful()) {

                                storage.getReference().child("buyers").child(id).child("photo").getDownloadUrl().addOnSuccessListener(uri -> {

                                    photo = uri.toString();

                                    String transactionId = FirebaseDatabase.getInstance().getReference()
                                            .child(id)
                                            .push().toString();
                                    HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();
                                    Buyer buyer = new Buyer(id, bName, bCedula, email, photo, bGender, bBirthday, transactions,  new Cart("cart", null), null);
                                    FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                                    goToFeed();

                                });

                            }

                        });

                    } else {

                        String transactionId = FirebaseDatabase.getInstance().getReference()
                                .child(id)
                                .push().toString();
                        HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();

                        Buyer buyer = new Buyer(id, bName, bCedula, email, photo, bGender, bBirthday, transactions, new Cart("cart", null), null);
                        FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);
                        goToFeed();

                    }
                }).addOnFailureListener(f -> {
                    // If create user fails
                    Snackbar.make(activity.getRegisterBtn(), f.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
                });
            }
        }
    }


    public void goToFeed() {

        Intent i = new Intent(activity, UserFeedActivity.class);
        activity.startActivity(i);
        activity.finishAffinity();

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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case GALLERY_CALLBACK:
                    tempUri = data.getData();

                    try {
                        Bitmap image = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), tempUri);
                        Glide.with(activity.getRegisterBtn()).load(tempUri).centerCrop().into(activity.getProfileImage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;

            }


        }


    }
}

