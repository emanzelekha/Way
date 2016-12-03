package com.appytech.businessway.tools;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Mohamed on 7/19/2016.
 */
public class RetrofitManager {

//    public static final String URL_BASE="http://salonattest.nahrapps.com/api/";
//    public static final String URL_IMAGES_BASE="http://www.feen.info/services/foody/mediaFiles/";
//    public static final String URL_GET_CATALOGS_WITH_SERVICES ="Catalog/GetAllCatalogs_with_Service";
//    public static final String TAG_LANGUAGE="language";
//    public static final int TAG_LANGUAGE_AR=0;
//    public static final int TAG_LANGUAGE_EN=1;

//    public interface APIsInterface {
////        @GET(URL_GET_RESTAURANT)
////        Call<RestaurantsModel> getCatalogs();
////
//        @FormUrlEncoded
//        @POST(URL_GET_CATALOGS_WITH_SERVICES)
//        Call<CatalogsModel> getCatalogs(@Field(TAG_LANGUAGE) int language);
//
////        @GET("movie/{id}")
////        Call<TopMoviesModel> getMoviesDetails(@Path("id") int id, @Query(TAG_API_KEY) String apiKey);
////
////        @POST(URL_TOP_MOVIES)
////        Call<TopMoviesModel> getTopRatedMovies2(@Body TopMoviesModel postData, @Header("Autorization") String Authorization);
//    }

    public static Retrofit retrofit;
    private static Retrofit getRetrofit(String baseURL) {
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIManager.APIsInterface getAPIBuilder(String baseUrl){
        APIManager.APIsInterface apIsInterface = getRetrofit(baseUrl).create(APIManager.APIsInterface.class);
        return apIsInterface;
    }

    public static abstract class APICallBack<M> implements Callback<M>{
        @Override
        public void onResponse(Call<M> call, Response<M> response) {
            Log.e("StatusCode", response.code()+"");
            if(response.isSuccessful()){
                onSuccess(response.body());
            }else{
                int statusCode=response.code();
                Converter<ResponseBody, Object> errorConverter = retrofit.responseBodyConverter(Object.class, new Annotation[0]);
                String errorBody="{}";
                try {
                    Object error = errorConverter.convert(response.errorBody());
                    errorBody =new Gson().toJson(error);
                } catch (IOException e) {}
                onFailed(false, statusCode, errorBody);
            }
        }
        @Override
        public void onFailure(Call<M> call, Throwable t) {
            try{Log.e("onFailure", t.getMessage());}catch(Exception e){}
            onFailed(true, 0, null);
        }
        public abstract void onSuccess(M data);
        public abstract void onFailed(boolean fromConnection, int statusCode, String errorBody);
    }
}
