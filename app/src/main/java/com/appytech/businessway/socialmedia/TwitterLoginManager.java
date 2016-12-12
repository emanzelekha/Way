package com.appytech.businessway.socialmedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;

/**
 * Created by eng_m on 12/7/2016.
 */

public class TwitterLoginManager {

    private static final String TWITTER_KEY = "XFOWeHL5IgGeyXVHfS3akZvEo";
    private static final String TWITTER_SECRET = "CFiKnFOmHe53jlDMA4Jchjr195mk1p4S2ntMlhbsCzICr07hjN";

    /*
    <com.twitter.sdk.android.core.identity.TwitterLoginButton
        android:id="@+id/twitter_login_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
     */

    private static final String TAG = "TwitterManager";

    private AppCompatActivity activity;
    private TwitterLoginButton twitterLoginButton;

    public TwitterLoginManager(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * Call this method before setContentView
     */
    public TwitterLoginManager init() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(activity, new Crashlytics(), new Twitter(authConfig));
        return this;
    }

    public void setup(View twitterButton, final TwitterResponseListener twitterResponseListener) {
        twitterLoginButton = (TwitterLoginButton) twitterButton;
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // with your app's user model
                Log.e(TAG, "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")");
                Call<User> userResult = Twitter.getApiClient(session).getAccountService().verifyCredentials(true, false);
                userResult.enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        User user = result.data;
                        String username = user.name;
                        String photoURL = user.profileImageUrl.replace("_normal.jpg", ".jpg");
                        String token = user.idStr;
                        Log.e(TAG, username + " - " + photoURL);
                        twitterResponseListener.onSuccess(token, username, photoURL, user);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.e(TAG, "Login with Twitter failure", exception);
                        twitterResponseListener.onFailed();
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                Log.e(TAG, "Login with Twitter failure", exception);
                twitterResponseListener.onFailed();
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    public void setCustomButton(View customButton){
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitterLoginButton.performClick();
            }
        });
    }

    public interface TwitterResponseListener {
        void onSuccess(String token, String name, String photoURL, User user);

        void onFailed();
    }
}
