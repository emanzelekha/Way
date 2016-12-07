package com.appytech.businessway.tools;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by eng_m on 5/8/2016.
 */
public class JSONHelper {

    public static JSONArray sortIntJSONArray(JSONArray jsonArray){
        return sortIntJSONArray(jsonArray, false);
    }

    public static JSONArray sortIntJSONArray(JSONArray jsonArray, final boolean desc){
        if (jsonArray != null) {
            try {
                // Sort:
                final ArrayList<String> arrayForSorting = new ArrayList<String>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayForSorting.add(jsonArray.get(i).toString());
                }
                Collections.sort(arrayForSorting, new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        if(desc){
                            return ((Integer) Integer.parseInt(rhs)).compareTo((Integer.parseInt(lhs)));
                        }else{
                            return ((Integer) Integer.parseInt(lhs)).compareTo((Integer.parseInt(rhs)));
                        }
                    }
                });

                // Prepare and send result:
                JSONArray resultArray = new JSONArray();
                for (int i = 0; i < arrayForSorting.size(); i++) {
                    resultArray.put(arrayForSorting.get(i));
                }
                return resultArray;
            }catch(Exception e){
                Log.e("sortIntJSONArray",e.toString());
                return jsonArray;
            }
        }
        return jsonArray; // Return error.
    }

    public static boolean isJSONArrayContains(JSONArray jsonArray, String value){
        if(jsonArray!=null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    if (jsonArray.getString(i).equals(value)) return true;
                } catch (JSONException e) {
                    LogManager.e("isJSONArrayContains",e.toString());
                    return false;
                }
            }
        }
        return false;
    }
    public static JSONObject getJsonObject(String stringToJSON) {
        try {
            return new JSONObject(stringToJSON);
        } catch (JSONException e) {
            LogManager.e("getJsonObject",e.toString());
            return new JSONObject();
        }
    }
    public static JSONArray getJsonArray(String stringToJSON) {
        try {
            return new JSONArray(stringToJSON);
        } catch (JSONException e) {
            LogManager.e("getJsonArray",e.toString());
            return new JSONArray();
        }
    }

//    public static String getStringFromJSONObject(Context context, JSONObject jsonObject, String key){
//        try {
//            boolean isArabic=false;
//            try{isArabic=LanguageManager.isArabic(context);}catch(Exception e){}
//            String value;
//            if(isArabic && jsonObject.has(key+"_ar"))value=jsonObject.getString(key+"_ar");
//            else value=jsonObject.getString(key);
//            return value;
//        } catch (JSONException e) {
//            LogManager.e("getStringFromJSONObject",e.toString());
//            return "";
//        }
//    }

    public static String getStringFromNestedJSONObject(JSONObject jsonObject, String... keys){
        for (int i=0; i<keys.length-1; i++)jsonObject=getJSONObjectFromJSONObject(jsonObject, keys[i]);
        return getStringFromJSONObject(jsonObject, keys[keys.length-1]);
    }

    public static String getStringFromJSONObject(JSONObject jsonObject, String key){
        try {
            String value=jsonObject.getString(key);
            return value;
        } catch (JSONException e) {
            LogManager.e("getStringFromJSONObject",e.toString());
            return "";
        }
    }

    public static int getIntFromJSONObject(JSONObject jsonObject, String key){
        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            LogManager.e("getIntFromJSONObject",e.toString()+" - "+key+":"+jsonObject);
            return 0;
        }
    }

    public static double getDoubleFromJSONObject(JSONObject jsonObject, String key){
        try {
            return jsonObject.getDouble(key);
        } catch (JSONException e) {
            LogManager.e("getDoubleFromJSONObject",e.toString());
            return 0;
        }
    }

    public static JSONObject getJSONObjectFromJSONArray(JSONArray jsonArray, int position){
        try {
            return jsonArray.getJSONObject(position);
        } catch (JSONException e) {
            LogManager.e("getJSONObjectFromJSONArray",e.toString());
            return new JSONObject();
        }
    }

    public static JSONObject getJSONObjectFromJSONObject(JSONObject jsonObject, String key){
        try {
            return new JSONObject(jsonObject.getString(key));
        } catch (JSONException e) {
            LogManager.e("getJSONObjectFromJSONObject",e.toString());
            return new JSONObject();
        }
    }


    public static JSONArray getJSONArrayFromJSONObject(JSONObject jsonObject, String key) {
        try {
            return new JSONArray(jsonObject.getString(key));
        } catch (JSONException e) {
            LogManager.e("getJSONArrayFromJSONObject",e.toString());
            return new JSONArray();
        }
    }

    public static List<JSONObject> getListFromJSONArray(JSONArray jsonArray){
        final List<JSONObject> list=new ArrayList<JSONObject>();
        for(int i=0; i<jsonArray.length(); i++){
            try {
                list.add(jsonArray.getJSONObject(i));
            } catch (JSONException e) {}
        }
        return list;
    }

    public static String getStringFromJSONArray(JSONArray jsonArray, int index) {
        try {
            return jsonArray.getString(index);
        } catch (JSONException e) {
            return "";
        }
    }

    public static void put(JSONObject jsonObject, String tag, Object data) {
        try {
            jsonObject.put(tag, data);
        } catch (JSONException e) {}
    }

}
