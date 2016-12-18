package com.appytech.businessway.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.appytech.businessway.R;
import com.appytech.businessway.adapters.SpinnerAdapter;
import com.appytech.businessway.dialogs.SelectImageDialog;
import com.appytech.businessway.models.ListCityModel;
import com.appytech.businessway.models.ListCountryModel;
import com.appytech.businessway.models.ListDistrictModel;
import com.appytech.businessway.models.ListFieldModel;
import com.appytech.businessway.models.ListIndustryModel;
import com.appytech.businessway.tools.APIManager;

import java.util.ArrayList;
import java.util.List;

public class CompleteProfileActivity extends AppCompatActivity {

    private SpinnerAdapter positionSpinnerAdapter, genderSpinnerAdapter, countrySpinnerAdapter, citySpinnerAdapter, districtSpinnerAdapter;
    private Spinner positionSpinner, genderSpinner, countrySpinner, citySpinner, districtSpinner;
    private List positionsList, gendersList, countriesList, citiesList, districtsList;
    private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        positionSpinner=(Spinner)findViewById(R.id.complete_profile_position_spinner);
        genderSpinner=(Spinner)findViewById(R.id.complete_profile_gender_spinner);
        countrySpinner=(Spinner)findViewById(R.id.complete_profile_country_spinner);
        citySpinner=(Spinner)findViewById(R.id.complete_profile_city_spinner);
        districtSpinner=(Spinner)findViewById(R.id.complete_profile_district_spinner);

        positionSpinnerAdapter=new SpinnerAdapter(this, positionSpinner, R.layout.spinner_item_simple, R.string.label_position);
        genderSpinnerAdapter=new SpinnerAdapter(this, genderSpinner, R.layout.spinner_item_simple, R.string.label_gender);
        countrySpinnerAdapter=new SpinnerAdapter(this, countrySpinner, R.layout.spinner_item_simple, R.string.label_country);
        citySpinnerAdapter=new SpinnerAdapter(this, citySpinner, R.layout.spinner_item_simple, R.string.label_city);
        districtSpinnerAdapter=new SpinnerAdapter(this, districtSpinner, R.layout.spinner_item_simple, R.string.label_district);

        gendersList=new ArrayList();
        gendersList.add(getString(R.string.label_male));
        gendersList.add(getString(R.string.label_female));
        genderSpinnerAdapter.setDataList(gendersList);

        List positionsList=new ArrayList();
        positionsList.add("Developer");
        positionsList.add("Engineer");
        positionsList.add("Doctor");
        positionSpinnerAdapter.setDataList(positionsList);

        listCountry();

        profileImageView=(ImageView)findViewById(R.id.complete_profile_profile_imageView);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfileImage();
            }
        });

        findViewById(R.id.complete_profile_finish_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompleteProfileActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private Bitmap currentProfileImage;
    private void editProfileImage() {
        final SelectImageDialog selectImageDialog=SelectImageDialog.newInstance("", true);
        selectImageDialog.setSelectListener(new SelectImageDialog.SelectImageListener() {
            @Override
            public void onSelectImage(final Bitmap bitmap, String base64) {
//                Log.e("finalBitmap", bitmap.getWidth()+" - "+bitmap.getHeight());
//                Log.e("imageBase64", base64.length()*8+" - "+base64);
                currentProfileImage=bitmap;
                profileImageView.setImageBitmap(bitmap);
            }
        });
        selectImageDialog.setImage(currentProfileImage, R.drawable.icon_upload);
        selectImageDialog.show(this.getSupportFragmentManager(), "EditItemLogo");
    }

    private void listIndustry(){
        APIManager.listIndustry(this, true, new APIManager.ResponseListener<ListIndustryModel>() {

            @Override
            public void done(ListIndustryModel dataModel) {


            }
            @Override
            public void failed(boolean fromConnection, int statusCode, String errorBody) {
                //if(!fromConnection){
                //if(statusCode==400){
                //try {
                //JSONObject errorJsonObject=new JSONObject(errorBody);
                //} catch (JSONException e) {}
                //}
                //}
            }
        });
    }

    private void listField(int fieldId){
        APIManager.listField(this, fieldId, true, new APIManager.ResponseListener<ListFieldModel>() {

            @Override
            public void done(ListFieldModel dataModel) {


            }
            @Override
            public void failed(boolean fromConnection, int statusCode, String errorBody) {
                //if(!fromConnection){
                //if(statusCode==400){
                //try {
                //JSONObject errorJsonObject=new JSONObject(errorBody);
                //} catch (JSONException e) {}
                //}
                //}
            }
        });
    }

    private void listCountry(){
        APIManager.listCountry(this, true, new APIManager.ResponseListener<ListCountryModel>() {

            @Override
            public void done(ListCountryModel dataModel) {
                final List<ListCountryModel.DataEntity> countriesModelList=dataModel.getData();
                countriesList=new ArrayList();
                for (int i=0; i<countriesModelList.size(); i++)countriesList.add(countriesModelList.get(i).getName());
                if(countriesList.size()>0){
                    countrySpinnerAdapter.setDataList(countriesList);
                    countrySpinnerAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            listCity(Integer.parseInt(countriesModelList.get(i).getId()));
                        }
                    });
                }
            }
            @Override
            public void failed(boolean fromConnection, int statusCode, String errorBody) {
                //if(!fromConnection){
                //if(statusCode==400){
                //try {
                //JSONObject errorJsonObject=new JSONObject(errorBody);
                //} catch (JSONException e) {}
                //}
                //}
            }
        });
    }

    private void listCity(int countryId){
        APIManager.listCity(this, countryId, true, new APIManager.ResponseListener<ListCityModel>() {

            @Override
            public void done(ListCityModel dataModel) {
                final List<ListCityModel.DataEntity> citiesModelList=dataModel.getData();
                citiesList=new ArrayList();
                for (int i=0; i<citiesModelList.size(); i++)citiesList.add(citiesModelList.get(i).getName());
                if(citiesList.size()>0){
                    citySpinnerAdapter.setDataList(citiesList);
                    citySpinnerAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            listDistrict(Integer.parseInt(citiesModelList.get(i).getId()));
                        }
                    });
                }
            }
            @Override
            public void failed(boolean fromConnection, int statusCode, String errorBody) {
                //if(!fromConnection){
                //if(statusCode==400){
                //try {
                //JSONObject errorJsonObject=new JSONObject(errorBody);
                //} catch (JSONException e) {}
                //}
                //}
            }
        });
    }

    private void listDistrict(int cityId){
        APIManager.listDistrict(this, cityId, true, new APIManager.ResponseListener<ListDistrictModel>() {

            @Override
            public void done(ListDistrictModel dataModel) {
                final List<ListDistrictModel.DataEntity> districtsModelList=dataModel.getData();
                districtsList=new ArrayList();
                for (int i=0; i<districtsModelList.size(); i++)districtsList.add(districtsModelList.get(i).getName());
                if(districtsList.size()>0){
                    districtSpinnerAdapter.setDataList(districtsList);
                }
            }
            @Override
            public void failed(boolean fromConnection, int statusCode, String errorBody) {
                //if(!fromConnection){
                //if(statusCode==400){
                //try {
                //JSONObject errorJsonObject=new JSONObject(errorBody);
                //} catch (JSONException e) {}
                //}
                //}
            }
        });
    }

}
