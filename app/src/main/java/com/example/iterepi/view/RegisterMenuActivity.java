package com.example.iterepi.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.RegisterMenuController;

public class RegisterMenuActivity extends AppCompatActivity {

    private Button facebookBtn;
    private Button googleBtn;
    private Button emailBtn;
    private Button loginBtn;
    private RegisterMenuController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);

        facebookBtn = findViewById(R.id.facebookBtn);
        googleBtn = findViewById(R.id.googleBtn);
        emailBtn = findViewById(R.id.emailBtn);
        loginBtn = findViewById(R.id.loginBtn);

        controller = new RegisterMenuController(this);
    }

    public Button getFacebookBtn() {
        return facebookBtn;
    }

    public Button getGoogleBtn() {
        return googleBtn;
    }

    public Button getEmailBtn() {
        return emailBtn;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }
}
