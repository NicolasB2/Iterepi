package com.example.iterepi.controller.login;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.view.login.LoginUserActivity;
import com.example.iterepi.view.login.RegisterMenuActivity;
import com.example.iterepi.view.login.RegisterUserEmailActivity;
import com.example.iterepi.view.user.UserFeedActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterMenuController implements View.OnClickListener {

    private RegisterMenuActivity activity;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    public final static int RC_SIGN_IN = 1;

    public RegisterMenuController(RegisterMenuActivity activity) {

        this.activity = activity;

        activity.getFacebookBtn().setOnClickListener(this);
        activity.getEmailBtn().setOnClickListener(this);
        activity.getGoogleBtn().setOnClickListener(this);
        activity.getLoginBtn().setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.deafault_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {

            case R.id.emailBtn:
                i = new Intent(activity, RegisterUserEmailActivity.class);
                activity.startActivity(i);
                break;

            case R.id.facebookBtn:
                break;

            case R.id.googleBtn:
                signIn();
                break;

            case R.id.loginBtn:
                i = new Intent(activity, LoginUserActivity.class);
                activity.startActivity(i);
                break;

        }
    }

    private void signIn() {
        Intent i = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(i, RC_SIGN_IN);
    }

    public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(activity, "Signed In Successfully", Toast.LENGTH_SHORT).show();
            FireBaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(activity, "Signed In Failed", Toast.LENGTH_SHORT).show();
            FireBaseGoogleAuth(null);
        }
    }

    private void FireBaseGoogleAuth(GoogleSignInAccount acc) {
        if(acc != null){
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        }
    }

    private void updateUI(FirebaseUser fUser) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity.getApplicationContext());
        if (account != null) {

            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();

            Toast.makeText(activity, "Welcome " + personName + " !",
                    Toast.LENGTH_SHORT).show();

            Intent i = new Intent(activity, UserFeedActivity.class);
            activity.startActivity(i);

            //TODO: Update UI with user settings

        }
    }


    public FirebaseAuth getmAuth() {
        return mAuth;
    }
}
