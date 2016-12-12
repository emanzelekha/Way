package com.appytech.businessway.tools;

import android.content.Context;

import com.appytech.businessway.activities.EntryActivity;
import com.appytech.businessway.models.LoginModel;

/**
 * Created by eng_m on 12/12/2016.
 */

public class AppDataManager {

    public static void saveUserData(Context context, LoginModel.DataEntity.IdentityEntity userData) {
        DataManager.setObject(context, APIManager.TAG_DATA, userData);
    }
}
