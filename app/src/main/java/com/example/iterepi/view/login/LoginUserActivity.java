package com.example.iterepi.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.login.LoginUserController;
import com.example.iterepi.controller.login.RegisterMenuController;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RegisterMenuController.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            controller.handleSignInResult(task);
        }
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
}
