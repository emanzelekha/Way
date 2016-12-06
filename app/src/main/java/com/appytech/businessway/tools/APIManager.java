package com.appytech.businessway.tools;

import com.appytech.businessway.R;
import com.appytech.businessway.models.ListDistrictModel;
import com.appytech.businessway.models.ListLanguageModel;
import com.appytech.businessway.models.CreateIndividualCardModel;
import com.appytech.businessway.models.ListCountryModel;
import com.appytech.businessway.models.ListIndustryModel;
import com.appytech.businessway.models.RegisterModel;
import com.appytech.businessway.models.LoginModel;
import com.appytech.businessway.models.ListFieldModel;
import com.appytech.businessway.models.UpdateModel;
import com.appytech.businessway.models.ListCityModel;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class APIManager{

    public static final String URL_BASE="188.241.115.32:8080/";
    private static final boolean SHOW_CHECK_SERVER_DIALOG=false;

    public static final String TAG_PARENT_ID ="parentID";
    public static final String URL_LIST_DISTRICT="frontend/web/api/v1/settings/list-district";
    public static final String URL_LIST_LANGUAGE="frontend/web/api/v1/settings/list-language";
    public static final String URL_CREATE_INDIVIDUAL_CARD="frontend/web/api/v1/cards/create";
    public static final String URL_LIST_COUNTRY="frontend/web/api/v1/settings/list-country";
    public static final String URL_LIST_INDUSTRY="frontend/web/api/v1/settings/list-industry";
    public static final String URL_REGISTER="frontend/web/api/v1/user/register";
    public static final String URL_LOGIN="frontend/web/api/v1/user/login";
    public static final String URL_LIST_FIELD="frontend/web/api/v1/settings/list-speciality";
    public static final String URL_UPDATE="cards/update";
    public static final String URL_LIST_CITY="frontend/web/api/v1/settings/list-city";

    public static final String TAG_CITY_ID ="city_id";
    public static final String TAG_DATA ="data";
    public static final String TAG_TYPE ="type";
    public static final String TAG_STATUS ="status";
    public static final String TAG_NAME ="name";
    public static final String TAG_ID ="id";
    public static final String TAG_SHORTCUT ="shortcut";
    public static final String TAG_CREATED_BY ="created_by";
    public static final String TAG_FIRST_NAME ="first_name";
    public static final String TAG_LAST_NAME ="last_name";
    public static final String TAG_COMPANY_NAME ="company_name";
    public static final String TAG_ADDRESS ="address";
    public static final String TAG_EMAIL ="email";
    public static final String TAG_WEBSITE ="website";
    public static final String TAG_MOBILE ="mobile";
    public static final String TAG_LAND_PHONE ="land_phone";
    public static final String TAG_FAX ="fax";
    public static final String TAG_POSITION ="position";
    public static final String TAG_TEMPLATE_ID ="template_id";
    public static final String TAG_COLOR ="color";
    public static final String TAG_FIELD_ID ="field_id";
    public static final String TAG_INDUSTRY_ID ="industry_id";
    public static final String TAG_SPECIALTY_ID ="specialty_id";
    public static final String TAG_ACCESSIBLE ="accessible";
    public static final String TAG_AUTHORIZATION ="Authorization";
    public static final String TAG_USERNAME ="username";
    public static final String TAG_BIRTH_DATE ="birth_date";
    public static final String TAG_PASSWORD ="password";
    public static final String TAG_GENDER ="gender";
    public static final String TAG_LANGUAGE_ID ="language_id";
    public static final String TAG_COUNTRY_ID ="country_id";
    public static final String TAG_DISTRICT_ID ="district_id";
    public static final String TAG_JOB ="job";
    public static final String TAG_BIO ="bio";
    public static final String TAG_MESSAGE ="message";
    public static final String TAG_TOKEN ="token";
    public static final String TAG_IDENTITY ="identity";
    public static final String TAG_MOBILE1 ="mobile1";
    public static final String TAG_CREATED_AT ="created_at";
    public static final String TAG_OAUTH_CLIENT ="oauth_client";
    public static final String TAG_OAUTH_CLIENT_USER_ID ="oauth_client_user_id";
    public static final String TAG_UPDATED_AT ="updated_at";
    public static final String TAG_PASSWORD_HASH ="password_hash";
    public static final String TAG_MOBILE2 ="mobile2";
    public static final String TAG_IMAGE_URL ="image_url";
    public static final String TAG_BASE_URL ="base_url";
    public static final String TAG_AUTH_KEY ="auth_key";
    public static final String TAG_ACCESS_TOKEN ="access_token";
    public static final String TAG_LOGGED_AT ="logged_at";
    public static final String TAG_PHONE ="phone";
    public static final String TAG_CARD_ID ="card_id";
    public static final String TAG_CODE ="code";

    private static String getAuthorization(Context context){
        //TODO: implement this method
        return "Bearer  lZpMEt_-3gtRHdfgVcOh5Q9nH3KC0RdCvNaVzXmO";
    }

    public interface APIsInterface {
        @GET(URL_LIST_DISTRICT)
        Call<ListDistrictModel> listDistrict(@Query(TAG_CITY_ID) int cityId);

        @GET(URL_LIST_LANGUAGE)
        Call<ListLanguageModel> listLanguage();

        @FormUrlEncoded
        @POST(URL_CREATE_INDIVIDUAL_CARD)
        Call<CreateIndividualCardModel> createIndividualCard(@Header(TAG_AUTHORIZATION) String authorization, @Field(TAG_CREATED_BY) int createdBy, @Field(TAG_FIRST_NAME) String firstName, @Field(TAG_LAST_NAME) String lastName, @Field(TAG_COMPANY_NAME) String companyName, @Field(TAG_ADDRESS) String address, @Field(TAG_EMAIL) String email, @Field(TAG_WEBSITE) String website, @Field(TAG_MOBILE) int mobile, @Field(TAG_LAND_PHONE) int landPhone, @Field(TAG_FAX) int fax, @Field(TAG_POSITION) String position, @Field(TAG_TEMPLATE_ID) int templateId, @Field(TAG_COLOR) String color, @Field(TAG_FIELD_ID) int fieldId, @Field(TAG_INDUSTRY_ID) int industryId, @Field(TAG_SPECIALTY_ID) int specialtyId, @Field(TAG_ACCESSIBLE) int accessible, @Field(TAG_TYPE) int type);

        @GET(URL_LIST_COUNTRY)
        Call<ListCountryModel> listCountry();

        @GET(URL_LIST_INDUSTRY)
        Call<ListIndustryModel> listIndustry();

        @FormUrlEncoded
        @POST(URL_REGISTER)
        Call<RegisterModel> register(@Field(TAG_USERNAME) String username, @Field(TAG_FIRST_NAME) String firstName, @Field(TAG_LAST_NAME) String lastName, @Field(TAG_EMAIL) String email, @Field(TAG_BIRTH_DATE) String birthDate, @Field(TAG_PASSWORD) int password, @Field(TAG_GENDER) int gender, @Field(TAG_COMPANY_NAME) String companyName, @Field(TAG_LANGUAGE_ID) int languageId, @Field(TAG_COUNTRY_ID) int countryId, @Field(TAG_CITY_ID) int cityId, @Field(TAG_DISTRICT_ID) int districtId, @Field(TAG_ADDRESS) String address, @Field(TAG_INDUSTRY_ID) int industryId, @Field(TAG_FIELD_ID) int fieldId, @Field(TAG_SPECIALTY_ID) int specialtyId, @Field(TAG_JOB) String job, @Field(TAG_BIO) String bio);

        @FormUrlEncoded
        @POST(URL_LOGIN)
        Call<LoginModel> login(@Field(TAG_IDENTITY) String identity, @Field(TAG_PASSWORD) String password);

        @GET(URL_LIST_FIELD)
        Call<ListFieldModel> listField(@Query(TAG_FIELD_ID) int fieldId);

        @FormUrlEncoded
        @POST(URL_UPDATE)
        Call<UpdateModel> update(@Header(TAG_AUTHORIZATION) String authorization, @Query(TAG_CARD_ID) int cardId, @Field(TAG_CREATED_BY) int createdBy);

        @GET(URL_LIST_CITY)
        Call<ListCityModel> listCity(@Query(TAG_COUNTRY_ID) int countryId);

    }

    public static void listDistrict(Context context, int cityId, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).listDistrict(cityId);
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void listLanguage(Context context, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).listLanguage();
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void createIndividualCard(Context context, int createdBy, String firstName, String lastName, String companyName, String address, String email, String website, int mobile, int landPhone, int fax, String position, int templateId, String color, int fieldId, int industryId, int specialtyId, int accessible, int type, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).createIndividualCard(getAuthorization(context), createdBy, firstName, lastName, companyName, address, email, website, mobile, landPhone, fax, position, templateId, color, fieldId, industryId, specialtyId, accessible, type);
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void listCountry(Context context, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).listCountry();
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void listIndustry(Context context, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).listIndustry();
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void register(Context context, String username, String firstName, String lastName, String email, String birthDate, int password, int gender, String companyName, int languageId, int countryId, int cityId, int districtId, String address, int industryId, int fieldId, int specialtyId, String job, String bio, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).register(username, firstName, lastName, email, birthDate, password, gender, companyName, languageId, countryId, cityId, districtId, address, industryId, fieldId, specialtyId, job, bio);
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void login(Context context, String identity, String password, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).login(identity, password);
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void listField(Context context, int fieldId, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).listField(fieldId);
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void update(Context context, int cardId, int createdBy, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).update(getAuthorization(context), cardId, createdBy);
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void listCity(Context context, int countryId, ResponseListener responseListener) {
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).listCity(countryId);
        boolean showLoadingDialog=true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    private static ProgressDialog showLoadingDialog(Context context, final Call call) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(context.getString(R.string.msg_loading));
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                try{call.cancel();}catch (Exception e){}
            }
        });
        return progressDialog;
    }
    private static void closeLoadingDialog(ProgressDialog progressDialog) {
        try{progressDialog.dismiss();}catch (Exception e){}
    }

    public interface ResponseListener<M> {
        void done(M dataModel);
        void failed(boolean fromConnection, int statusCode, String errorBody);
    }

    private static void performNormalRequest(final Context context, final Call call, final boolean showLoading, final ResponseListener responseListener){
        Log.e("URL", call.request().url().toString());
        if(call.request().body()!=null)Log.e("onSuccess", new Gson().toJson(call.request().body()));

        ProgressDialog progressDialog = null;
        if(showLoading){
            progressDialog=showLoadingDialog(context, call);
        }
        final ProgressDialog tempProgressDialog=progressDialog;
        call.enqueue(new RetrofitManager.APICallBack() {
            @Override
            public void onSuccess(Object data) {
                String response=new Gson().toJson(data);
                Log.e("Result", response);
                if(showLoading)closeLoadingDialog(tempProgressDialog);
                //try{
//                    JSONObject responseJsonObject=new JSONObject(response);
//                    int isResultHasData=responseJsonObject.getInt(TAG_IS_RESULT_HAS_DATA);
//                    if(isResultHasData>0){
                        responseListener.done(data);
//                    }else{
//                        Toast.makeText(context, context.getString(R.string.msg_couldnt_perform_operation), Toast.LENGTH_SHORT).show();
//                    }
                //}catch(Exception e1){
                    //try{responseListener.done(data);}catch(Exception e){}
                //}
            }

            @Override
            public void onFailed(boolean fromConnection, int statusCode, String errorBody) {
                Log.e("onFailed", fromConnection+"");
                if(showLoading)closeLoadingDialog(tempProgressDialog);
                String msg;
                if(fromConnection)msg=context.getString(R.string.msg_check_connection);
                else msg=context.getString(R.string.msg_check_server);
                if(fromConnection || SHOW_CHECK_SERVER_DIALOG) {
                    if (context instanceof Activity) {
                        if (context != null) {
                            DialogManager.showConfirmDialog((Activity) context, msg, context.getString(R.string.label_retry), context.getString(R.string.label_cancel), new DialogManager.Confirm() {
                                @Override
                                public void ok() {
                                    performNormalRequest(context, call.clone(), showLoading, responseListener);
                                }
                            });
                        }
                    } else DialogManager.showToast(context, msg);
                }
                responseListener.failed(fromConnection, statusCode, errorBody);
            }
        });
    }
}