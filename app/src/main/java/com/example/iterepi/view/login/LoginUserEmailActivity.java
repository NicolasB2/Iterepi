package com.example.iterepi.view.login;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.LoginUserEmailController;
import com.google.android.material.textfield.TextInputLayout;

public class LoginUserEmailActivity extends AppCompatActivity {

    private TextInputLayout passLoginUserTF;
    private TextInputLayout emailLoginUserTF;
    private Button update_DataBtn;
    private Button forgotPassUser;
    private LoginUserEmailController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_email);

        passLoginUserTF = findViewById(R.id.passLoginUserTF);
        emailLoginUserTF = findViewById(R.id.emailLoginUserTF);
        update_DataBtn = findViewById(R.id.updateDataBtn);
        forgotPassUser = findViewById(R.id.forgotPassUser);

        controller = new LoginUserEmailController(this);

    }

    public TextInputLayout getPassLoginUserTF() {
        return passLoginUserTF;
    }

    public TextInputLayout getEmailLoginUserTF() {
        return emailLoginUserTF;
    }

    public Button getUpdate_DataBtn() {
        return update_DataBtn;
    }

    public Button getForgotPassUser() {
        return forgotPassUser;
    }
}
