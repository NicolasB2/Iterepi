package com.example.iterepi.view.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.RegisterUserEmailController;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterUserEmailActivity extends AppCompatActivity {

    private ImageView profileImage;
    private ImageButton setImageBtn;
    private TextInputLayout nameTF;
    private TextInputLayout birthdayTF;
    private TextInputLayout emailTF;
    private TextInputLayout passwordTF;
    private TextInputLayout confPasswordTF;
    private TextInputLayout identificationTF;
    private MaterialCheckBox checkTerms;
    private RadioGroup radioGroup;
    private Button registerBtn;
    private RegisterUserEmailController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_email);

        profileImage = findViewById(R.id.profileImage2);
        setImageBtn = findViewById(R.id.setImageBtn2);
        nameTF = findViewById(R.id.nameUserTF);
        birthdayTF = findViewById(R.id.birthdayTF);
        emailTF = findViewById(R.id.userEmailTF);
        passwordTF = findViewById(R.id.userPasswordTF);
        confPasswordTF = findViewById(R.id.confirmPasswordTF);
        identificationTF = findViewById(R.id.identificationTF);
        radioGroup = findViewById(R.id.radioGroup);
        checkTerms = findViewById(R.id.checkTerms);
        registerBtn = findViewById(R.id.registerUserBtn);

        controller = new RegisterUserEmailController(this);

    }

    public ImageView getProfileImage() {
        return profileImage;
    }

    public ImageButton getSetImageBtn() {
        return setImageBtn;
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
