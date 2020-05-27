package com.example.iterepi.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.login.RegisterUserEmailController;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterUserEmailActivity extends AppCompatActivity {

    private CircleImageView profileImage;
    private TextInputLayout nameTF;
    private TextInputLayout birthdayTF;
    private TextInputLayout emailTF;
    private TextInputLayout passwordTF;
    private TextInputLayout confPasswordTF;
    private TextInputLayout identificationTF;
    private MaterialCheckBox checkTerms;
    private RadioGroup radioGroup;
    private Button registerBtn;
    private ImageButton backBtn;
    private RegisterUserEmailController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_email);

        profileImage = findViewById(R.id.profileImage2);
        nameTF = findViewById(R.id.nameUserTF);
        birthdayTF = findViewById(R.id.birthdayTF);
        emailTF = findViewById(R.id.userEmailTF);
        passwordTF = findViewById(R.id.userPasswordTF);
        confPasswordTF = findViewById(R.id.confirmPasswordTF);
        identificationTF = findViewById(R.id.identificationTF);
        radioGroup = findViewById(R.id.radioGroup);
        checkTerms = findViewById(R.id.checkTerms);
        registerBtn = findViewById(R.id.registerUserBtn);
        backBtn = findViewById(R.id.backBtn5);

        passwordTF.setHelperText(getString(R.string.helper_pass));

        controller = new RegisterUserEmailController(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        controller.onActivityResult(requestCode, resultCode, data);

    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public CircleImageView getProfileImage() {
        return profileImage;
    }

    public TextInputLayout getNameTF() {
        return nameTF;
    }

    public TextInputLayout getBirthdayTF() {
        return birthdayTF;
    }

    public TextInputLayout getEmailTF() {
        return emailTF;
    }

    public TextInputLayout getPasswordTF() {
        return passwordTF;
    }

    public TextInputLayout getConfPasswordTF() {
        return confPasswordTF;
    }

    public TextInputLayout getIdentificationTF() {
        return identificationTF;
    }

    public MaterialCheckBox getCheckTerms() {
        return checkTerms;
    }

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public Button getRegisterBtn() {
        return registerBtn;
    }
}
