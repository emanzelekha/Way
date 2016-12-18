package com.appytech.businessway.tools;

import android.app.Activity;
import android.content.Context;

import com.appytech.businessway.activities.EntryActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by eng_m on 12/6/2016.
 */

public class APIManagerExtra {
    public static final String TAG_SUCCESS="success";
    public static final String TAG_ERROE="success";

    public static void handelErrorBody(Activity activity, Object response) {
        handelErrorBody(activity, new Gson().toJson(response));
    }
    public static void handelErrorBody(Activity activity, String errorBody) {
        JSONObject errorJsonObject = JSONHelper.getJsonObject(errorBody);
        LogManager.e("failed:"+errorJsonObject);
        JSONArray errorMsgsJsonArray=JSONHelper.getJSONArrayFromJSONObject(errorJsonObject, APIManager.TAG_DATA);
        String errorMsg="";
        for(int i=0; i<errorMsgsJsonArray.length(); i++){
            if(errorMsg.length()>0)errorBody+="\n";
            errorMsg+=JSONHelper.getStringFromJSONArray(errorMsgsJsonArray, i);
        }
        if(errorMsg.length()>0)DialogManager.showDialog(activity, errorMsg);
    }
}
