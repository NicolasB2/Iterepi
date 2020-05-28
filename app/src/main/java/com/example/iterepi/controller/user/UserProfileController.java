package com.example.iterepi.controller.user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.view.user.ChangesPassActivity;
import com.example.iterepi.view.user.UserProfileActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.IOException;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class UserProfileController implements View.OnClickListener {

    public static final int GALLERY_CALLBACK = 1;

    private UserProfileActivity activity;
    private Buyer buyer;
    private boolean checkBirthday;
    private boolean checkName;
    private boolean checkIdentification;
    private boolean checkEmail;
    private boolean checkGender;
    private Uri tempUri;
    private String photo;
    private int gender;


    public UserProfileController(UserProfileActivity activity) {
        this.activity = activity;

        activity.getPhoto().setOnClickListener(this);
        activity.getUpdateDataBtn().setOnClickListener(this);
        activity.getChangePassBtn().setOnClickListener(this);


        Query query = FirebaseDatabase.getInstance().getReference()
                .child("buyers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                buyer = dataSnapshot.getValue(Buyer.class);
                setUserInfo(buyer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listeners();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.updateDataBtn:
                updateInfo();
                break;

            case R.id.changePassBtn:
                Intent i = new Intent(activity, ChangesPassActivity.class);
                activity.startActivity(i);
                break;

            case R.id.photo:
                Intent gal = new Intent(Intent.ACTION_GET_CONTENT);
                gal.setType("image/*");
                activity.startActivityForResult(gal, GALLERY_CALLBACK);
                break;

        }

    }

    private void setUserInfo(Buyer buyer) {

        int gender = buyer.getGender();

        switch (gender) {

            case 0:
                activity.getGender().check(R.id.checkWoman);
                break;

            case 1:
                activity.getGender().check(R.id.checkMan);
                break;

        }

        if (buyer.getIdentification() != null) {
            activity.getIdentificationTF().getEditText().setText(buyer.getIdentification());
        }
        activity.getNameUserTF().getEditText().setText(buyer.getName());
        activity.getUserEmailTF().getEditText().setText(buyer.getEmail());
        if (buyer.getBirthday() != null) {
            activity.getBirthdayTF().getEditText().setText(buyer.getBirthday());
        }
        if (buyer.getPhoto() != null) {

            Glide.with(activity).load(buyer.getPhoto()).centerCrop().into(activity.getPhoto());

        }
    }

    public void openCalendar() {

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        DatePickerDialog dpd = new DatePickerDialog(activity.getUpdateDataBtn().getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                int fixedMonth = month + 1;
                String dateSelected = dayOfMonth + "/" + fixedMonth + "/" + year;
                activity.getBirthdayTF().getEditText().setText(dateSelected);

            }
        }, year, month, day);

        dpd.show();

    }

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
                } else {

                    activity.getBirthdayTF().setHelperText(activity.getString(R.string.empty_field));
                    checkBirthday = false;
                }
            }
        });

        activity.getNameUserTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getNameUserTF().getEditText().getText().toString();
                if (!t.isEmpty()) {
                    removeError(activity.getNameUserTF());
                    checkName = true;
                } else {

                    putError(activity.getNameUserTF(), activity.getString(R.string.empty_field));
                    checkName = false;

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

                } else {

                    putError(activity.getIdentificationTF(), activity.getString(R.string.empty_field));
                    checkIdentification = false;

                }
            }
        });

        activity.getUserEmailTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getUserEmailTF().getEditText().getText().toString();
                if (!t.isEmpty()) {

                    removeError(activity.getUserEmailTF());
                    checkEmail = true;

                } else {

                    putError(activity.getUserEmailTF(), activity.getString(R.string.empty_field));
                    checkEmail = false;
                }
            }
        });


    }


    public void updateInfo() {

        gender = buyer.getGender();

        if (activity.getBirthdayTF().getEditText().getText().toString().equals("")) {

            Snackbar.make(activity.getUpdateDataBtn(), activity.getString(R.string.select_birthday), Snackbar.LENGTH_SHORT).show();

        }


        if (gender == -1) {

            checkGender = false;
            Snackbar.make(activity.getUpdateDataBtn(), activity.getString(R.string.select_gender), Snackbar.LENGTH_SHORT).show();

        } else {

            checkGender = true;

        }


        if (activity.getGender().getCheckedRadioButtonId() == R.id.checkMan) {

            gender = 1;

        } else if (activity.getGender().getCheckedRadioButtonId() == R.id.checkWoman) {

            gender = 0;

        }

        if (checkGender && checkBirthday && checkEmail && checkIdentification && checkName) {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (tempUri != null) {

                FirebaseStorage storage = FirebaseStorage.getInstance();

                storage.getReference().child("buyers").child(user.getUid()).child("photo").putFile(tempUri).addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        storage.getReference().child("buyers").child(user.getUid()).child("photo").getDownloadUrl().addOnSuccessListener(uri -> {

                            String name = activity.getNameUserTF().getEditText().getText().toString();
                            String birthday = activity.getBirthdayTF().getEditText().getText().toString();
                            String identification = activity.getIdentificationTF().getEditText().getText().toString();
                            String email = activity.getUserEmailTF().getEditText().getText().toString();


                            photo = uri.toString();

                            FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("name").setValue(name);
                            FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("birthday").setValue(birthday);
                            FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("identification").setValue(identification);
                            FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("email").setValue(email);
                            FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("photo").setValue(photo);


                            Snackbar.make(activity.getUpdateDataBtn(), activity.getString(R.string.info_updated), Snackbar.LENGTH_SHORT).show();


                        });

                    }

                });


            } else {

                String name = activity.getNameUserTF().getEditText().getText().toString();
                String birthday = activity.getBirthdayTF().getEditText().getText().toString();
                String identification = activity.getIdentificationTF().getEditText().getText().toString();
                String email = activity.getUserEmailTF().getEditText().getText().toString();


                FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("name").setValue(name);
                FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("birthday").setValue(birthday);
                FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("identification").setValue(identification);
                FirebaseDatabase.getInstance().getReference().child("buyers").child(user.getUid()).child("email").setValue(email);


                Snackbar.make(activity.getUpdateDataBtn(), activity.getString(R.string.info_updated), Snackbar.LENGTH_SHORT).show();


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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case GALLERY_CALLBACK:

                    tempUri = data.getData();

                    try {
                        Bitmap image = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), tempUri);
                        activity.getPhoto().setImageBitmap(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;


            }


        }

    }
}
