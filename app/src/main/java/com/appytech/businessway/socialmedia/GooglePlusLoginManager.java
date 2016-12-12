package com.appytech.businessway.socialmedia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by eng_m on 12/7/2016.
 */

public class GooglePlusLoginManager {

    //use Web client (auto created by Google Service) client ID from Google Console
    public static final String TAG_GOOGLE_SERVER_CLIENT_ID = "152962564371-g3s4rraefeutmvbpmn5igv0jjf4uhgiq.apps.googleusercontent.com";
//    public static final String TAG_GOOGLE_SERVER_CLIENT_ID = "152962564371-4kbp5ug1eb6gsi3p2qui40a8uqeaiqb1.apps.googleusercontent.com";

    /*app gradle
    compile 'com.google.android.gms:play-services-auth:9.2.1'

    at end:
    apply plugin: 'com.google.gms.google-services'
     */

    /*project gradle
    classpath 'com.google.gms:google-services:3.0.0'
     */

    /*
    com.google.android.gms.common.SignInButton
        android:id="@+id/login_google_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
     */

    private static final String TAG="GooglePlusLoginManager";
    private static final int REQUEST_CODE= 5374;
    private GoogleApiClient mGoogleApiClient;
    private AppCompatActivity activity;
    private GoogleResponseListener googleResponseListener;

    public GooglePlusLoginManager(AppCompatActivity activity) {
        this.activity =activity;
    }

    public void setup(View googlePlusButton, GoogleResponseListener googleResponseListener){
        this.googleResponseListener=googleResponseListener;
        // Configure sign-in to request the user's ID, city address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().requestIdToken(TAG_GOOGLE_SERVER_CLIENT_ID.toString())
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
        // Customize sign-in button. The sign-in button can be displayed in
        // multiple sizes and color schemes. It can also be contextually
        // rendered based on the requested scopes. For example. a red button may
        // be displayed when Google+ scopes are requested, but a white button
        // may be displayed when only basic profile is requested. Try adding the
        // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
        // difference.
        final SignInButton googleLoginButton = (SignInButton) googlePlusButton;
        googleLoginButton.setSize(SignInButton.SIZE_STANDARD);
        googleLoginButton.setScopes(googleSignInOptions.getScopeArray());
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });
    }

    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, REQUEST_CODE);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.e(TAG, "handleSignInResult:" + result.isSuccess()+" - "+result.getStatus());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount signInAccount = result.getSignInAccount();
            String username=signInAccount.getDisplayName();
            String photoURL=signInAccount.getPhotoUrl().toString();
            String token=signInAccount.getIdToken();

            googleResponseListener.onSuccess(token, username, photoURL, signInAccount);
        }else{
            googleResponseListener.onFailed();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    public void setCustomButton(View customButton){
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });
    }

    public interface GoogleResponseListener{
        void onSuccess(String token, String name, String photoURL, GoogleSignInAccount userProfile);
        void onFailed();
    }
}
