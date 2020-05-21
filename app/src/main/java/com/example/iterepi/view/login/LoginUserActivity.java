package com.example.iterepi.view.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.LoginUserController;
import com.example.iterepi.controller.RegisterMenuController;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

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
