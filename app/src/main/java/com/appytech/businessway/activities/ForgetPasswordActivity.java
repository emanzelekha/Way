package com.appytech.businessway.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.appytech.businessway.R;
import com.appytech.businessway.models.PasswordResetModel;
import com.appytech.businessway.tools.APIManager;
import com.appytech.businessway.tools.APIManagerExtra;
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
                    passwordReset(viewHelper.getValue(R.id.forget_password_email_textView));
                }
            }
        });
    }

    private void passwordReset(String email){
        APIManager.passwordReset(this, email, true, new APIManager.ResponseListener<PasswordResetModel>() {
            @Override
            public void done(PasswordResetModel dataModel) {
                APIManagerExtra.handelErrorBody(ForgetPasswordActivity.this, dataModel);
            }
            @Override
            public void failed(boolean fromConnection, int statusCode, String errorBody) {
                if(!fromConnection){
                    APIManagerExtra.handelErrorBody(ForgetPasswordActivity.this, errorBody);
                //if(statusCode==400){
                //try {
                //JSONObject errorJsonObject=new JSONObject(errorBody);
                //} catch (JSONException e) {}
                //}
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }else return super.onOptionsItemSelected(item);
    }
}
