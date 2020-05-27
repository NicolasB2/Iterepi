package com.example.iterepi.view.login;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CompleteRegisterActivity extends RegisterUserEmailActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);


        if (acct != null) {

            getNameTF().getEditText().setText("" + acct.getDisplayName());
            getEmailTF().getEditText().setText("" + acct.getEmail());
            getEmailTF().setEnabled(false);


            if (acct.getPhotoUrl() != null) {
                String url = acct.getPhotoUrl().toString();
                url.replace("/s96-c/", "/s800-c/");
                Glide.with(this).load(url).into(getProfileImage());
            }
        }else{


            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            getNameTF().getEditText().setText("" + user.getDisplayName());
            getEmailTF().getEditText().setText("" + user.getEmail());
            getEmailTF().setEnabled(false);

            String url = user.getPhotoUrl().toString() + "?height=500";
            Glide.with(this).load(url).into(getProfileImage());


        }

    }
}
