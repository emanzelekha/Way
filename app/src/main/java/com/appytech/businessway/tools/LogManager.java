package com.appytech.businessway.tools;

import android.support.compat.BuildConfig;
import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by Nourhan Selim on 2016-03-17.
 */
public class LogManager {
    private static final String TAG="BusinessWay";
    private static final boolean DEBUG=true;

    public static void e(String tag, String msg){
        if(DEBUG)Log.e(tag, msg);
    }

    public static void e(String msg){
        if(DEBUG)Log.e(TAG, msg);
    }

    public static void e(Object object){
        if(DEBUG)Log.e(TAG, new Gson().toJson(object));
    }
}
