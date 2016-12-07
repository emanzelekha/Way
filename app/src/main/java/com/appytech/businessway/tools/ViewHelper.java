package com.appytech.businessway.tools;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.appytech.businessway.R;
import com.appytech.businessway.tools.glideUtils.GlideBlurTransformation;
import com.appytech.businessway.tools.glideUtils.GlideCircleTransform;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by selim on 11/30/2016.
 */

public class ViewHelper {

    private static final int TEXT_PLEASE_ENTER=1;
    private static final int TEXT_INVALID_EMAIL=2;
    private static final int TEXT_INVALID_PHONE=3;
    private static final int TEXT_INVALID_PASSWORD=4;

    private Activity activity;
    private View rootView;

    public ViewHelper(Activity activity) {
        this.activity = activity;
    }

    public ViewHelper(View rootView) {
        this.rootView = rootView;
    }

    private static String getString(Context context, int id){
        if(id==TEXT_PLEASE_ENTER)return context.getString(R.string.msg_please_enter);
        if(id==TEXT_INVALID_EMAIL)return context.getString(R.string.msg_enter_valid_email);
        if(id==TEXT_INVALID_PHONE)return context.getString(R.string.msg_enter_valid_phone);
        if(id==TEXT_INVALID_PASSWORD)return context.getString(R.string.msg_enter_valid_password);
        else return "";
    }

    private View getView(int id) {
        if (activity != null) return activity.findViewById(id);
        else if (rootView != null) return rootView.findViewById(id);
        else return null;
    }

    public String getValue(int id) {
        return ((TextView) getView(id)).getText().toString();
    }

    public int getIntValue(int id) {
        return Integer.parseInt(getValue(id));
    }

    public double getDoubleValue(int id) {
        return Double.parseDouble(getValue(id));
    }

    public double getFloatValue(int id) {
        return Float.parseFloat(getValue(id));
    }

    public void setValue(int id, String text) {
        ((TextView) getView(id)).setText(text);
    }

    public void setHint(int id, String hintText) {
        ((EditText) getView(id)).setHint(hintText);
    }

    public void setImage(int id, int image) {
        ((ImageView) getView(id)).setImageResource(image);
    }

    public void setImage(int id, String url) {
        ImageView imageView = (ImageView) getView(id);
        Glide.with(activity)
                .load(url)
                .placeholder(imageView.getDrawable())
                .error(imageView.getDrawable())
                .into(imageView);
    }

    public void setImage(int id, String url,  boolean isCached, boolean isCenterCrop, boolean isCircle, boolean isBlur) {
        ImageView imageView = (ImageView) getView(id);

        BitmapTypeRequest<String> glide = Glide.with(activity).load(url).asBitmap();

        if(isCached) glide.diskCacheStrategy(DiskCacheStrategy.ALL);
        if (isCenterCrop) glide.centerCrop();
        if (isCircle) glide.transform(new GlideCircleTransform(activity));
        if (isBlur) glide.transform(new GlideBlurTransformation(activity));
        if (isCenterCrop) glide.centerCrop();
        glide.placeholder(imageView.getDrawable())
                .error(imageView.getDrawable())
                .into(imageView);
    }

//    Glide.with(activity).
//    load(APIsConstants.IMAGES_BASE_URL+JsonHelper.getStringFromJSONObject(activity, dataJsonObject, APIsConstants.TAG_LOGO)).
//    asBitmap().
//    centerCrop().
//    placeholder(R.drawable.icon_plate).
//    error(R.drawable.icon_plate).
//    transform(new GlideCircleTransform(activity)).
//    diskCacheStrategy(DiskCacheStrategy.SOURCE).
//    into(new BitmapImageViewTarget(itemsViewHolder.getItemLogoImageView()) {
//        @Override
//        public void onResourceReady(Bitmap drawable, GlideAnimation anim) {
//            super.onResourceReady(drawable, anim);
//            itemsViewHolder.getItemLogoImageView().setBackgroundResource(R.drawable.shape_circle);
//        }
//    });

    public boolean validate(int... ids){
        return validate(getEditTexts(ids));
    }

    public boolean validate(EditText... fieldsEditTexts){
        boolean valid=validateEmpty(fieldsEditTexts);
        if(valid){
            EditText passwordEditText=null;
            for (int i=0; i<fieldsEditTexts.length;i++){
                if(fieldsEditTexts[i].getInputType()== InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)valid=isValidEmail(getContext(), fieldsEditTexts[i]);
                if(fieldsEditTexts[i].getInputType()== InputType.TYPE_CLASS_PHONE)valid=isValidPhone(getContext(), fieldsEditTexts[i]);
                if(fieldsEditTexts[i].getInputType()== InputType.TYPE_TEXT_VARIATION_PASSWORD){
                    if(passwordEditText==null)passwordEditText=fieldsEditTexts[i];
                    else valid=isValidPassword(getContext(), passwordEditText, fieldsEditTexts[i]);
                }
            }
        }
        return valid;
    }

    public boolean validateEmpty(int... ids) {
        return validateEmpty(getEditTexts(ids));
    }

    private EditText[] getEditTexts(int... ids){
        EditText[] fieldsEditText=new EditText[ids.length];
        for (int i=0; i<ids.length; i++){
            fieldsEditText[i]=(EditText)getView(ids[i]);
        }
        return fieldsEditText;
    }

    public boolean validateEmpty(EditText... fieldsEditText) {
        boolean valid=true;
        for (int i=0; i<fieldsEditText.length; i++){
            if(!validateEmptyField(getContext(), fieldsEditText[i]))valid=false;
        }
        return valid;
    }

    private Context getContext() {
        if (activity != null) return activity;
        else if (rootView != null) return rootView.getContext();
        else return null;
    }

    public static boolean validateEmptyField(Context context, EditText editText) {
        String value=editText.getText().toString().trim();
        if(value.length()==0){
            editText.setError(getString(context, TEXT_PLEASE_ENTER)+" "+ getHint(editText));
            return false;
        }else{
            editText.setError(null);
            return true;
        }
    }

    private static String getHint(EditText editText){
        String hint="";
        try{editText.getHint();}catch (Exception e){}

        if(hint.length()==0){
            ViewParent tempParent = null;
            for(int i=0; i<5; i++){
                if(tempParent==null)tempParent=editText.getParent();
                else tempParent=tempParent.getParent();
                if(tempParent!=null && tempParent instanceof TextInputLayout)hint = ((TextInputLayout)tempParent).getHint().toString();
            }
        }
        return hint;
    }

//    public static boolean isValidEmailOrPhone(Context context, EditText editText) {
//        String value=editText.getText().toString().trim();
//        boolean isValidEmail=Patterns.EMAIL_ADDRESS.matcher(value).matches();
//        boolean isValidPhone=Patterns.PHONE.matcher(value).matches();
//        if(!isValidEmail && !isValidPhone){
//            editText.setError(context.getString(R.string.msg_enter_valid_email_or_phone));
//            return false;
//        }
//        else return true;
//    }

    public static boolean isValidEmail(Context context, EditText editText) {
        String value=editText.getText().toString().trim();
        boolean isValidEmail= Patterns.EMAIL_ADDRESS.matcher(value).matches();
        if(!isValidEmail)editText.setError(getString(context, TEXT_INVALID_EMAIL));
        return isValidEmail;
    }

    public static boolean isValidPhone(Context context, EditText editText) {
        String value=editText.getText().toString().trim();
        boolean isValidPhone=Patterns.PHONE.matcher(value).matches();
        if(!isValidPhone)editText.setError(getString(context, TEXT_INVALID_PHONE));
        return isValidPhone;
    }

    public static boolean isValidPassword(Context context, EditText passwordEditText, EditText confirmPasswordEditText) {
        boolean isValidPassword=true;
        if(!passwordEditText.getText().toString().trim().equals(confirmPasswordEditText.getText().toString().trim())){
            isValidPassword=false;
            passwordEditText.setError(getString(context, TEXT_INVALID_PASSWORD));
            confirmPasswordEditText.setError(getString(context, TEXT_INVALID_PASSWORD));
        }
        return isValidPassword;
    }
//    public String[] getValue(int... ids) {
//        String[] values= new String[ids.length];
//
//        for (int i=0; i<ids.length; i++){
//            if (ValidationManager.validateEmptyFields(activity, ids)) {
//                if (activity != null)
//                    values[i]= ((TextView) activity.findViewById(ids[i])).getText().toString();
//                else if (rootView != null)
//                    values[i]= ((TextView) rootView.findViewById(ids[i])).getText().toString();
//            }
//        }
//        return values;
//    }


}

