package com.appytech.businessway.tools;

import android.util.Log;

import com.appytech.businessway.BuildConfig;

/**
 * Created by Nourhan Selim on 2016-03-17.
 */
public class LogManager {
    public static void e(String tag, String msg){
        if(BuildConfig.DEBUG)Log.e(tag, msg);
    }

    public static void e(String msg){
        if(BuildConfig.DEBUG)Log.e("Foody", msg);
    }

    public static void e(Object object){
//        if(BuildConfig.DEBUG)Log.e("Foody", new Gson().to);
    }
}
