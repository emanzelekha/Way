package com.appytech.businessway.socialmedia;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by eng_m on 12/7/2016.
 */

public class FacebookLoginManager {
    //https://developers.facebook.com/docs/facebook-login/android/

    /* app gradle
    compile 'com.facebook.android:facebook-android-sdk:4.16.0'
     */

    /* Button View:
    <com.facebook.login.widget.LoginButton
        android:id="@+id/login_facebook_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
     */

    /* Manifests
    <meta-data
        android:name="com.facebook.sdk.ApplicationId"
        android:value="@string/facebook_app_id" />

    <activity
        android:name="com.facebook.FacebookActivity"
        android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
        android:label="@string/app_name"
        android:theme="@android:style/Theme.Translucent.NoTitleBar"
        tools:replace="android:theme" />

    <activity
        android:name="com.facebook.CustomTabActivity"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.VIEW" />

            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="android.intent.category.BROWSABLE" />

            <data android:scheme="@string/fb_login_protocol_scheme" />
        </intent-filter>
    </activity>
     */

    /* values->facebook_values
    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <string name="facebook_app_id">717531875064750</string>
        <string name="fb_login_protocol_scheme">fbAPP_ID</string>
    </resources>
     */

    private static final String TAG="FacebookManager";
    private Profile currentFacebookProfile;
    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;
    private FacebookResponseListener facebookResponseListener;
    private Context context;

    public FacebookLoginManager(Context context){
        this.context=context;
    }

    /**
     * Call this method before setContentView
     */
    public FacebookLoginManager init(){
        FacebookSdk.sdkInitialize(context);
        AppEventsLogger.activateApp(context);
        return this;
    }

    public static String printKeyHash(Context context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            String packageName = context.getApplicationContext().getPackageName();
            packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Log.e(TAG, "Package Name="+context.getApplicationContext().getPackageName());

            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e(TAG, "Key Hash="+key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e(TAG, "Name not found: "+e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "No such an algorithm: "+e.toString());
        } catch (Exception e) {
            Log.e(TAG, "Exception: "+e.toString());
        }
        return key;
    }

    public void setup(View facebookButton, final FacebookResponseListener facebookResponseListener){
        this. facebookResponseListener=facebookResponseListener;
        ProfileTracker profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if(oldProfile!=null)currentFacebookProfile=oldProfile;
                if(currentProfile!=null)currentFacebookProfile=currentProfile;
            }
        };
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton = (LoginButton) facebookButton;
        facebookLoginButton.setReadPermissions("email", "public_profile");
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                Log.e(TAG, "facebook:onCancel");
                facebookResponseListener.onFailed();
            }
            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "facebook:onError", error);
                facebookResponseListener.onFailed();
            }
        });
    }

    private void handleFacebookAccessToken(final AccessToken accessToken) {
        if(currentFacebookProfile==null){
            if(isLoggedIn())logout();
            facebookLoginButton.performClick();
        }else {
            String userID = accessToken.getUserId();
            final String photoURL = "http://graph.facebook.com/" + userID + "/picture?type=large";
            String username = currentFacebookProfile == null ? "No Name" : currentFacebookProfile.getName();
            String token = userID;
            facebookResponseListener.onSuccess(token, username, photoURL, Profile.getCurrentProfile());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        try{callbackManager.onActivityResult(requestCode, resultCode, data);}catch (Exception e){}
    }

    public void setCustomButton(View customButton){
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "is logged in:"+isLoggedIn());
                if(isLoggedIn())logout();
                facebookLoginButton.performClick();
            }
        });
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public void logout(){
        LoginManager.getInstance().logOut();
    }

    public interface FacebookResponseListener{
        void onSuccess(String token, String name, String photoURL, Profile userProfile);
        void onFailed();
    }
}
