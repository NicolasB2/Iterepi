package com.example.iterepi.view.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.login.LoginStoreController;
import com.google.android.material.textfield.TextInputLayout;

public class LoginStoreActivity extends AppCompatActivity {

    private LoginStoreController controller;
    private Button forgotPassBtn;
    private Button registerHereBtn;
    private TextInputLayout email;
    private TextInputLayout pass;
    private Button updateDataBtn;
    private ImageButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_store);

        forgotPassBtn = findViewById(R.id.forgotPass);
        registerHereBtn = findViewById(R.id.registerHereStore);
        email = findViewById(R.id.emailStore);
        pass = findViewById(R.id.passwordTF);
        updateDataBtn = findViewById(R.id.updateDataBtn);
        backBtn = findViewById(R.id.backBtn2);

        controller = new LoginStoreController(this);


    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public Button getForgotPassBtn() {
        return forgotPassBtn;
    }

    public Button getRegisterHereBtn() {
        return registerHereBtn;
    }

    public TextInputLayout getEmail() {
        return email;
    }

    public TextInputLayout getPass() {
        return pass;
    }

    public Button getUpdateDataBtn() {
        return updateDataBtn;
    }
}
