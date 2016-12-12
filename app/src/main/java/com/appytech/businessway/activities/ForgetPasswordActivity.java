package com.appytech.businessway.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.appytech.businessway.R;
import com.appytech.businessway.tools.ViewHelper;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ViewHelper viewHelper=new ViewHelper(this);
        findViewById(R.id.forget_password_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHelper.validate(R.id.forget_password_email_textView)){
                    forgetPassword(viewHelper.getValue(R.id.forget_password_email_textView));
                }
            }
        });
    }

    private void forgetPassword(String email) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }else return super.onOptionsItemSelected(item);
    }
}
