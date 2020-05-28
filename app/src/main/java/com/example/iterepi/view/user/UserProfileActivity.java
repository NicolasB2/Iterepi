package com.example.iterepi.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.user.UserProfileController;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity {

    private CircleImageView photo;
    private TextInputLayout nameUserTF;
    private TextInputLayout birthdayTF;
    private TextInputLayout userEmailTF;
    private TextInputLayout identificationTF;
    private RadioGroup gender;
    private Button changePassBtn;
    private Button updateDataBtn;

    private UserProfileController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        photo = findViewById(R.id.photo);
        nameUserTF = findViewById(R.id.nameUserTF);
        birthdayTF = findViewById(R.id.birthdayTF);
        userEmailTF = findViewById(R.id.userEmailTF);
        identificationTF = findViewById(R.id.identificationTF);
        gender = findViewById(R.id.radioGroup);
        changePassBtn = findViewById(R.id.changePassBtn);
        updateDataBtn = findViewById(R.id.updateDataBtn);

        controller = new UserProfileController(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        controller.onActivityResult(requestCode, resultCode, data);

    }

    public CircleImageView getPhoto() {
        return photo;
    }

    public TextInputLayout getNameUserTF() {
        return nameUserTF;
    }

    public TextInputLayout getBirthdayTF() {
        return birthdayTF;
    }

    public TextInputLayout getUserEmailTF() {
        return userEmailTF;
    }

    public TextInputLayout getIdentificationTF() {
        return identificationTF;
    }

    public RadioGroup getGender() {
        return gender;
    }

    public Button getChangePassBtn() {
        return changePassBtn;
    }

    public Button getUpdateDataBtn() {
        return updateDataBtn;
    }
}
