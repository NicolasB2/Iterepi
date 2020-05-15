package com.example.iterepi.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.LoginUserController;

public class LoginUserActivity extends AppCompatActivity {

    private LoginUserController controller;
    private Button facebookBtn;
    private Button googleBtn;
    private Button emailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        facebookBtn = findViewById(R.id.facebookBtn);
        googleBtn = findViewById(R.id.googleBtn);
        emailBtn = findViewById(R.id.emailBtn);

        controller = new LoginUserController(this);
    }

    public LoginUserController getController() {
        return controller;
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
}
