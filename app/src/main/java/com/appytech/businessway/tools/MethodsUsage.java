package com.appytech.businessway.tools;

public class MethodsUsage{
/*
    private void listDistrict(int cityId){
        APIManager.listDistrict(this, cityId, true, new APIManager.ResponseListener<ListDistrictModel>() {

        @Override
            public void done(ListDistrictModel dataModel) {
                

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

    private void listLanguage(){
        APIManager.listLanguage(this, true, new APIManager.ResponseListener<ListLanguageModel>() {

        @Override
            public void done(ListLanguageModel dataModel) {
                

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

    private void createIndividualCard(int createdBy, String firstName, String lastName, String companyName, String address, String email, String website, int mobile, int landPhone, int fax, String position, int templateId, String color, int fieldId, int industryId, int specialtyId, int accessible, int type){
        APIManager.createIndividualCard(this, createdBy, firstName, lastName, companyName, address, email, website, mobile, landPhone, fax, position, templateId, color, fieldId, industryId, specialtyId, accessible, type, true, new APIManager.ResponseListener<CreateIndividualCardModel>() {

        @Override
            public void done(CreateIndividualCardModel dataModel) {
                

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

    private void register(String username, String firstName, String lastName, String email, String birthDate, int password, int gender, String companyName, int languageId, int countryId, int cityId, int districtId, String address, int industryId, int fieldId, int specialtyId, String job, String bio){
        APIManager.register(this, username, firstName, lastName, email, birthDate, password, gender, companyName, languageId, countryId, cityId, districtId, address, industryId, fieldId, specialtyId, job, bio, true, new APIManager.ResponseListener<RegisterModel>() {

        @Override
            public void done(RegisterModel dataModel) {
                

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

    private void login(String identity, String password){
        APIManager.login(this, identity, password, true, new APIManager.ResponseListener<LoginModel>() {

        @Override
            public void done(LoginModel dataModel) {
                

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

    private void registerUser(String firstName, String lastName, String email, int password){
        APIManager.registerUser(this, firstName, lastName, email, password, true, new APIManager.ResponseListener<RegisterUserModel>() {

        @Override
            public void done(RegisterUserModel dataModel) {
                

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

    private void update(int cardId, int createdBy){
        APIManager.update(this, cardId, createdBy, true, new APIManager.ResponseListener<UpdateModel>() {

        @Override
            public void done(UpdateModel dataModel) {
                

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

    private void passwordReset(String email){
        APIManager.passwordReset(this, email, true, new APIManager.ResponseListener<PasswordResetModel>() {

        @Override
            public void done(PasswordResetModel dataModel) {
                

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

*/
}