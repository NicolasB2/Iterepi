package com.example.iterepi.controller.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.view.login.CompleteRegisterActivity;
import com.example.iterepi.view.login.LoginUserActivity;
import com.example.iterepi.view.login.LoginUserEmailActivity;
import com.example.iterepi.view.login.RegisterMenuActivity;
import com.example.iterepi.view.user.UserFeedActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class LoginUserController implements View.OnClickListener {

    public static final int RC_SIGN_IN = 1;
    private LoginUserActivity activity;
    private GoogleSignInClient mGoogleSignInClient;


    public LoginUserController(LoginUserActivity activity) {
        this.activity = activity;
        activity.getFacebookBtn().setOnClickListener(this);
        activity.getGoogleBtn().setOnClickListener(this);
        activity.getEmailBtn().setOnClickListener(this);
        activity.getRegisterTV().setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.facebookBtn:
                break;

            case R.id.googleBtn:
                signIn();
                break;
            case R.id.backBtn:
                activity.finish();
                break;
            case R.id.emailBtn:
                i = new Intent(activity, LoginUserEmailActivity.class);
                activity.startActivity(i);
                break;
            case R.id.registerTV:
                i = new Intent(activity, RegisterMenuActivity.class);
                activity.startActivity(i);
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.

            firebaseAuthWithGoogle(account);

            Log.e(">>>>>>>>", account.getEmail());



        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e(">>>>>", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        if (acct != null) {
            AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            Log.d("GOOGLE AUTH", "firebaseAuthWithGoogle:" + acct.getId());
            FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("GOOGLE AUTH", "signInWithCredential:success");
                                boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();

                                if (isNew) {

                                    Log.e("GOOGLE AUTH", "I'm a new user.");
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(activity);

                                    String id = user.getUid();
                                    String name = acct.getDisplayName();
                                    String email = acct.getEmail();
                                    String photo = acct.getPhotoUrl().toString();
                                    photo.replace("/s96-c/", "/s800-c/");

                                    Buyer buyer = new Buyer(id, name, null, email, null, photo, -1, null, null, null);

                                    FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                                    Intent c = new Intent(activity, CompleteRegisterActivity.class);
                                    activity.startActivity(c);


                                } else {

                                    Log.e("GOOGLE AUTH", "I'm an old user.");
                                    Intent c = new Intent(activity, UserFeedActivity.class);
                                    activity.startActivity(c);

                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("GOOGLE AUTH", "signInWithCredential:failure", task.getException());
                                Snackbar.make(activity.getGoogleBtn(), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}
