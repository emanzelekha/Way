
package com.appytech.businessway.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    private static SharedPreferences getMultiProcessSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_MULTI_PROCESS);
    }

    //String -> get, set
    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getSharedPreferences(context).getString(key, defaultValue);
    }

    public static void setString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).commit();
    }

    //int -> get, set
    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static void setInt(Context context, String key, int value) {
        getSharedPreferences(context).edit().putInt(key, value).commit();
    }

    //boolean -> get, set
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).commit();
    }

    //float -> get, set
    public static double getFloat(Context context, String key) {
        return getFloat(context, key, 0);
    }

    public static double getFloat(Context context, String key, float defaultValue) {
        return getSharedPreferences(context).getFloat(key, defaultValue);
    }

    public static void setFloat(Context context, String key, float value) {
        getSharedPreferences(context).edit().putFloat(key, value).commit();
    }

    //jsonObject -> get, set, setValue, getValue, hasKey
    static class AppyJSONObject {
        private JSONObject jsonObject;
        private Context context;

        public AppyJSONObject(Context context, String key) {
            this.context = context;
            jsonObject = DataManager.getJSONObject(context, key);
        }

        public AppyJSONObject(Context context, JSONObject jsonObject) {
            this.context = context;
            this.jsonObject = jsonObject;
        }

        public void setValue(String key, String value) {
            try {
                DataManager.setJSONObject(context, key, jsonObject.put(key, value));
            } catch (JSONException e) {
            }
        }

        public String getValue(String key) {
            try {
                return jsonObject.getString(key);
            } catch (JSONException e) {
                return "";
            }
        }

        public boolean hasKey(String key) {
            return jsonObject.has(key);
        }

        public JSONObject getJSONObject() {
            return jsonObject;
        }
    }

    public static JSONObject getJSONObject(Context context, String key) {
        try {
            return new JSONObject(getString(context, key, "{}"));
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    public static AppyJSONObject getAppyJSONObject(Context context, String key) {
        return new AppyJSONObject(context, key);
    }

    public static void setJSONObject(Context context, String key, JSONObject value) {
        setString(context, key, value.toString());
    }

    //JsonArrayOfJsonObject -> get, set, add, remove, getAppyJsonObjectAt

    //jsonArray -> get, set, add, remove, getItemAt
    public static void setJSONArray(Context context, String key, JSONArray value) {
        setString(context, key, value.toString());
    }
    public static JSONArray getJSONArray(Context context, String key) {
        try {
            return new JSONArray(getString(context, key, "[]"));
        } catch (JSONException e) {
            return new JSONArray();
        }
    }

    //Object -> get, set, getAsJsonObject
    public static <M> M getObject(Context context, String key, Class<M> objectClass) {
        return new Gson().fromJson(getString(context, key, "{}"), objectClass);
    }

    public static JSONObject getAsJSONObject(Context context, String key) {
        return getJSONObject(context, key);
    }

    public static void setObject(Context context, String key, Object object) {
        setString(context, key, new Gson().toJson(object));
    }

    //ListOfObjects -> get, getAsJsonArrayOfJsonObject, set, add, remove, getObjectAt, getAppyJsonObjectAt

    //List -> get, set, add, remove, has
    public static void setList(Context context, String key, List list){
        JSONArray jsonArray=new JSONArray();
        for (int i=0; i<list.size(); i++) jsonArray.put(list.get(i));
        setJSONArray(context, key, jsonArray);
    }
    public static List getList(Context context, String key){
        JSONArray jsonArray=getJSONArray(context, key);
        List list=new ArrayList();
        for (int i=0; i<jsonArray.length(); i++)
            try {
                list.add(jsonArray.get(i));
            } catch (JSONException e) {}
        return list;
    }

    //HashMap -> get, set, add, remove, has

}
