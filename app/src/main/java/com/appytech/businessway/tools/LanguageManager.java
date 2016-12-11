package com.appytech.businessway.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class LanguageManager {
	
	final private static String LANGUAGE_PREF = "language";
	
	final public static String LANGUAGE_ENGLISH = "en";
	final public static String LANGUAGE_ARABIC = "ar";

	public static String getLanguage(Context context){
		return context.getSharedPreferences("preferences", Context.MODE_PRIVATE).getString(LANGUAGE_PREF, "");
	}

	public static String getUsedLanguage(Context context) {
		return context.getSharedPreferences("preferences", Context.MODE_PRIVATE).getString(LANGUAGE_PREF, Locale.getDefault().getLanguage());
	}

	public static boolean setLanguage(Context context, String lang){
		Log.e("currentLang", "lang:"+getLanguage(context)+","+lang);
		if(!getLanguage(context).equalsIgnoreCase(lang)){
			SharedPreferences.Editor preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE).edit();
			preferences.putString(LANGUAGE_PREF, lang);
			preferences.commit();

			changeLanguage(context, lang);

		    return true;
		}else return false;
	}

	private static void changeLanguage(Context context, String lang){
		if(lang.length()>0){
			Log.e("changeLanguage", lang);
			Configuration config = new Configuration();
			config.locale = new Locale(lang);
		    context.getResources().updateConfiguration(config, null);
//			Locale myLocale = new Locale(lang);
//			Resources res = context.getResources();
//			DisplayMetrics dm = res.getDisplayMetrics();
//			Configuration conf = res.getConfiguration();
//			conf.locale = myLocale;
//			res.updateConfiguration(conf, dm);
		}
	}

	private static void setLocale(Activity activity, String lang) {
		Locale myLocale = new Locale(lang);
		Resources res = activity.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
		Intent refresh = new Intent(activity, activity.getClass());
		activity.startActivity(refresh);
		activity.finish();
	}

	public static void applyAppLanguage(Context context){
//		Locale.setDefault(new Locale(LanguageManager.getUsedLanguage(context)));
		String language=getUsedLanguage(context);
		if(!language.equalsIgnoreCase(LANGUAGE_ARABIC))language=LANGUAGE_ENGLISH;
		setLanguage(context, language);
		changeLanguage(context, language);
	}

	public static boolean isArabic(Context context) {
		return (getUsedLanguage(context).equalsIgnoreCase(LANGUAGE_ARABIC));
	}

//	public static void showLanguageDialog(final Activity activity){
//		String[] items = {activity.getString(R.string.english), activity.getString(R.string.arabic)};
//		new AlertDialog.Builder(activity)
//		.setTitle(activity.getString(R.string.app_name))
//		.setItems(items, new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				if(which==0){
//					if(setLanguage(activity, LANGUAGE_ENGLISH)){
//						ShowConfirmDialog(activity, activity.getString(R.string.msg_language_restart_app), activity.getString(R.string.button_restart), activity.getString(R.string.button_cancel), new Confirm() {
//							@Override
//							public void ok() {
//								Intent intent=new Intent(activity, activity.getClass());
//								intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//								activity.startActivity(intent);
//							}
//						});
//					}
//				}else if(which==1){
//					if(setLanguage(activity, LANGUAGE_ARABIC)){
//						ShowConfirmDialog(activity, activity.getString(R.string.msg_language_restart_app), activity.getString(R.string.button_restart), activity.getString(R.string.button_cancel), new Confirm() {
//							@Override
//							public void ok() {
//								Intent intent=new Intent(activity, activity.getClass());
//								intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//								activity.startActivity(intent);
//							}
//						});
//					}
//				}
//			}
//		})
//		.setNegativeButton(activity.getString(R.string.button_cancel), new OnClickListener() {
//			public void onClick(DialogInterface dialog, int whichButton) {
//				dialog.dismiss();
//			}
//		}).show();
//	}

//	public static interface Confirm{
//		public void ok();
//	}
	
}
