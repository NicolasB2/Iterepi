package com.example.iterepi.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.login.LoginUserController;

public class LoginUserActivity extends AppCompatActivity {

    private LoginUserController controller;
    private ImageButton facebookBtn;
    private ImageButton googleBtn;
    private ImageButton emailBtn;
    private ImageButton backBtn;
    private TextView registerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        facebookBtn = findViewById(R.id.facebookBtn);
        googleBtn = findViewById(R.id.googleBtn);
        emailBtn = findViewById(R.id.emailBtn);
        registerTV = findViewById(R.id.registerTV);
        backBtn = findViewById(R.id.backBtn);

        controller = new LoginUserController(this);
    }
    public LoginUserController getController() {
        return controller;
    }

    public ImageButton getFacebookBtn() {
        return facebookBtn;
    }

    public ImageButton getGoogleBtn() {
        return googleBtn;
    }

    public ImageButton getEmailBtn() {
        return emailBtn;
    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public TextView getRegisterTV() {
        return registerTV;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.onActivityResult(requestCode, resultCode, data);
    }
}
