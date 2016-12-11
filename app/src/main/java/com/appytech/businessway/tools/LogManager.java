package com.appytech.businessway.tools;

import android.support.compat.BuildConfig;
import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by Nourhan Selim on 2016-03-17.
 */
public class LogManager {
    private static final String TAG="BusinessWay";

    public static void e(String tag, String msg){
        if(BuildConfig.DEBUG)Log.e(tag, msg);
    }

    public static void e(String msg){
        if(BuildConfig.DEBUG)Log.e(TAG, msg);
    }

    public static void e(Object object){
        if(BuildConfig.DEBUG)Log.e(TAG, new Gson().toJson(object));
    }
}
