package com.example.iterepi.controller.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Cart;
import com.example.iterepi.model.Transaction;
import com.example.iterepi.view.login.CompleteRegisterActivity;
import com.example.iterepi.view.login.LoginUserActivity;
import com.example.iterepi.view.login.LoginUserEmailActivity;
import com.example.iterepi.view.login.RegisterMenuActivity;
import com.example.iterepi.view.user.UserFeedActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
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
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;

public class LoginUserController implements View.OnClickListener {

    public static final int RC_SIGN_IN = 1;
    private LoginUserActivity activity;
    private GoogleSignInClient mGoogleSignInClient;

    private CallbackManager mCallbackManager;

    public LoginUserController(LoginUserActivity activity) {
        this.activity = activity;
        activity.getFacebookBtn().setOnClickListener(this);
        activity.getGoogleBtn().setOnClickListener(this);
        activity.getEmailBtn().setOnClickListener(this);
        activity.getRegisterTV().setOnClickListener(this);
        activity.getBackBtn().setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        mCallbackManager = CallbackManager.Factory.create();
    }

    public CallbackManager getmCallbackManager() {
        return mCallbackManager;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.facebookBtn:
                signInFb();
                break;

            case R.id.googleBtn:
                signIn();
                break;
            case R.id.backBtn4:
                activity.onBackPressed();
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

    public void signInFb(){

        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email","public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                handleFbToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

                Log.e("FACEBOOK", ""+error);

            }
        });

    }

    private void handleFbToken(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Log.e("FACEBOOK", "SUCCESSFUL");
                    boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(isNew) {

                        Log.e("FACEBOOK","I'm a new user");
                        String id = user.getUid();
                        String name = user.getDisplayName();
                        String photo = user.getPhotoUrl().toString();
                        String email = user.getEmail();

                        String transactionId = FirebaseDatabase.getInstance().getReference()
                                .child(id)
                                .push().toString();
                        HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();

                        Buyer buyer = new Buyer(id, name, null, email, photo, -1, null, transactions, new Cart("cart", null), null);

                        FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                        Intent c = new Intent(activity, CompleteRegisterActivity.class);
                        c.putExtra("PROVIDER", "FACEBOOK");
                        activity.startActivity(c);
                    }else{
                        Log.e("FACEBOOK", "I'm a old user");
                        Intent c = new Intent(activity, UserFeedActivity.class);
                        activity.startActivity(c);
                        activity.finishAffinity();


                    }


                }else{

                    Log.e("FACEBOOK","ERROR:" + task.getException());

                }

            }
        });

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

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                if (isNew) {

                                    Log.e("GOOGLE AUTH", "I'm a new user.");
                                    GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(activity);

                                    String id = user.getUid();
                                    String name = acct.getDisplayName();
                                    String email = acct.getEmail();
                                    String photo = acct.getPhotoUrl().toString();
                                    photo.replace("/s96-c/", "/s800-c/");

                                    String transactionId = FirebaseDatabase.getInstance().getReference()
                                            .child(id)
                                            .push().getKey();
                                    HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();
                                    Buyer buyer = new Buyer(id, name, null, email, photo, -1, null, transactions, new Cart("cart", null), null);

                                    FirebaseDatabase.getInstance().getReference().child("buyers").child(id).setValue(buyer);

                                    Intent c = new Intent(activity, CompleteRegisterActivity.class);
                                    c.putExtra("PROVIDER","GOOGLE");
                                    activity.startActivity(c);


                                } else {

                                    Log.e("GOOGLE AUTH", "I'm an old user.");
                                    Intent c = new Intent(activity, UserFeedActivity.class);
                                    activity.startActivity(c);
                                    activity.finishAffinity();

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
