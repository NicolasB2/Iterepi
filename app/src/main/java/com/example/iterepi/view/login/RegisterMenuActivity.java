package com.example.iterepi.view.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RegisterMenuController.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(RegisterMenuActivity.this, "Signed In Successfully", Toast.LENGTH_SHORT).show();
            FireBaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(RegisterMenuActivity.this, "Signed In Failed", Toast.LENGTH_SHORT).show();
            FireBaseGoogleAuth(null);
        }
    }

    private void FireBaseGoogleAuth(GoogleSignInAccount acc) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
        controller.getmAuth().signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = controller.getmAuth().getCurrentUser();
                    updateUI(user);
                } else {
                    Toast.makeText(RegisterMenuActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser fUser) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {

            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();

            Toast.makeText(RegisterMenuActivity.this, "Welcome " + personName + " !",
                    Toast.LENGTH_SHORT).show();

            //TODO: Update UI with user settings

        }
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
