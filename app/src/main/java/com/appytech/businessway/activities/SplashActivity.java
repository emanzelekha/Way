package com.appytech.businessway.activities;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.appytech.businessway.R;
import com.appytech.businessway.models.LoginModel;
import com.appytech.businessway.tools.APIManager;
import com.appytech.businessway.tools.AnimationManager;
import com.appytech.businessway.tools.JSONHelper;
import com.appytech.businessway.tools.LogManager;
import com.appytech.businessway.tools.ViewHelper;

import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 2000;

    private static final int MODE_SPLASH = 1;
    private static final int MODE_ENTRY_LOGIN = 2;
    private static final int MODE_LOGIN = 3;
    private static final int MODE_REGISTER = 4;

    private View mainLayout;
    private int mode, registerFrom;
    private ViewHelper viewHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mainLayout = findViewById(R.id.activity_splash);
        viewHelper = new ViewHelper(this);

        showSplash();

        new CountDownTimer(SPLASH_TIME, SPLASH_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                showLoginEntry();
            }
        }.start();

        findViewById(R.id.login_entry_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.login_entry_login_button).setOnClickListener(null);
                showLogin();
            }
        });

        addRegisterButtonsListener();

        findViewById(R.id.login_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHelper.validate(R.id.login_email_editText, R.id.login_password_editText)) {
                    login(viewHelper.getValue(R.id.login_email_editText), viewHelper.getValue(R.id.login_password_editText));
                }
            }
        });
    }


    private void login(String identity, String password) {
        APIManager.login(this, identity, password, new APIManager.ResponseListener<LoginModel>() {

            @Override
            public void done(LoginModel dataModel) {
                LogManager.e(dataModel);
            }

            @Override
            public void failed(boolean fromConnection, int statusCode, String errorBody) {
                if (!fromConnection) {
                    //if(statusCode==400){
                    JSONObject errorJsonObject = JSONHelper.getJsonObject(errorBody);
                    LogManager.e(errorJsonObject.toString());
//                }
                }
            }
        });
    }


    private void addRegisterButtonsListener() {
        findViewById(R.id.login_entry_register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.login_entry_register_button).setOnClickListener(null);
                registerFrom = MODE_ENTRY_LOGIN;
                showRegister();
            }
        });

        findViewById(R.id.login_register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.login_register_button).setOnClickListener(null);
                registerFrom = MODE_LOGIN;
                showRegister();
            }
        });
    }

    private void showSplash() {
        mode = MODE_SPLASH;
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        //Remove the listener before proceeding
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            mainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            mainLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }

                        int entryDuration = 1000;
                        int fadeIn = 5000;
                        AnimationManager.slideFromDown(findViewById(R.id.splash_arms_imageView), entryDuration, 0);
                        AnimationManager.fadeIn(findViewById(R.id.splash_logo_imageView), fadeIn);
                    }
                });
    }

    private void showLoginEntry() {
        mode = MODE_ENTRY_LOGIN;
        int entryDuration = 1000;
        AnimationManager.transform(findViewById(R.id.splash_logo_imageView), findViewById(R.id.splash_logo2_imageView), 0, entryDuration, 0);
        AnimationManager.transform(findViewById(R.id.splash_arms_imageView), findViewById(R.id.splash_arms2_imageView), 0, entryDuration, 0);

        AnimationManager.slideFromDown(findViewById(R.id.login_entry_bottom1_layout), entryDuration, 0);
        AnimationManager.slideFromDownWithDistance(findViewById(R.id.login_entry_bottom2_layout), findViewById(R.id.login_entry_bottom1_layout).getHeight(), entryDuration, 0);
    }

    private void showLogin() {
        mode = MODE_LOGIN;
        int armsDuration = 1000;
        int fadeDuration = 500;
        int duration = 1000;
        findViewById(R.id.login_register_button).setVisibility(View.VISIBLE);
        AnimationManager.slideDownWithDistance(findViewById(R.id.splash_arms_imageView), findViewById(R.id.splash_arms_imageView).getHeight() * 2, armsDuration, 0);
//        AnimationManager.fadeOut(findViewById(R.id.splash_arms_imageView), armsDuration);
        AnimationManager.fadeOut(findViewById(R.id.login_entry_welcome_textView), 500);
//        AnimationManager.transform(findViewById(R.id.login_entry_bottom_layout), findViewById(R.id.login_entry_main_layout), 0, 0, 0);
        AnimationManager.transform(findViewById(R.id.login_entry_login_button), findViewById(R.id.login_login_button), 0, duration, 0);
        AnimationManager.slideFromDownWithDistance(findViewById(R.id.login_social_media_layout), findViewById(R.id.login_social_media_layout).getHeight() * 2, duration, 0);
        AnimationManager.fadeIn(findViewById(R.id.login_top_layout), duration * 2);

        new CountDownTimer(duration, duration) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                findViewById(R.id.login_login_button).setVisibility(View.VISIBLE);
                findViewById(R.id.login_entry_login_button).setVisibility(View.GONE);
            }
        }.start();
    }

    private void showRegister() {
        mode = MODE_REGISTER;
        final int duration = 1000;
        final int fadeDuration = 700;
        findViewById(R.id.register_main_layout).setVisibility(View.VISIBLE);
        if (registerFrom == MODE_ENTRY_LOGIN)
            AnimationManager.transform(findViewById(R.id.login_entry_register_button), findViewById(R.id.register_toolbar), 0, duration, 0);
        else
            AnimationManager.transform(findViewById(R.id.login_register_button), findViewById(R.id.register_toolbar), 0, duration, 0);
        AnimationManager.slideFromDown(findViewById(R.id.register_scrollView), duration, 0);
        new CountDownTimer(duration, duration) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if (registerFrom == MODE_ENTRY_LOGIN)
                    AnimationManager.fadeOut(findViewById(R.id.login_entry_register_button), fadeDuration);
                else
                    AnimationManager.fadeOut(findViewById(R.id.login_register_button), fadeDuration);
                AnimationManager.fadeIn(findViewById(R.id.register_toolbar_layout), fadeDuration);
                initToolbar();
            }
        }.start();
    }

    private void hideRegister() {
        mode = registerFrom;
        final int duration = 1000;
        final int fadeDuration = 700;
        View registerButton;
        if (registerFrom == MODE_ENTRY_LOGIN)
            registerButton = findViewById(R.id.login_entry_register_button);
        else registerButton = findViewById(R.id.login_register_button);

        registerButton.setVisibility(View.VISIBLE);
        findViewById(R.id.register_toolbar_layout).setVisibility(View.INVISIBLE);
//        AnimationManager.fadeIn(topRegister, fadeDuration);
//        AnimationManager.fadeOut(findViewById(R.id.register_toolbar_layout), fadeDuration);

        AnimationManager.transform(registerButton, findViewById(R.id.register_toolbar), 0, duration, 0);
        AnimationManager.slideDown(findViewById(R.id.register_scrollView), duration, 0);
        new CountDownTimer(duration, duration) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                findViewById(R.id.register_main_layout).setVisibility(View.GONE);
                addRegisterButtonsListener();
            }
        }.start();
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        mToolbar.setTitle(R.string.label_register);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        if (mode == MODE_REGISTER) {
            hideRegister();
        } else super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else return super.onOptionsItemSelected(item);
    }
}
