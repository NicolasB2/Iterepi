package com.example.iterepi.view;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.LoginUserController;

public class LoginUserActivity extends AppCompatActivity {

    private LoginUserController controller;
    private ImageButton facebookBtn;
    private ImageButton googleBtn;
    private ImageButton emailBtn;

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

    public ImageButton getFacebookBtn() {
        return facebookBtn;
    }

    public ImageButton getGoogleBtn() {
        return googleBtn;
    }

    public ImageButton getEmailBtn() {
        return emailBtn;
    }

}
