package com.appytech.businessway.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RegisterUserModel implements Parcelable {

    /**
     * type : success
     * data : {"message":"user Created successfully","token":"XfPLA3Nxeu3M0eFxEMYyJsJVu4qPb-0EWIjQ74nX","identity":{"id":70,"type":0,"username":"","email":"eng_mhosni99@gmail.com","status":2,"first_name":"mohamed","last_name":"hosni","fax":"","phone":"","mobile1":"","mobile2":"","country_id":"","city_id":"","address":"","birth_date":"","industry_id":"","field_id":"","specialty_id":"","job":"","gender":"","bio":"","company_name":"","base_url":"","image_url":"","created_at":1481799318,"updated_at":1481799318,"logged_at":""}}
     * status : 200
     */

    private String type;
    private DataEntity data;
    private int status;

    public void setType(String type) {
        this.type = type;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public DataEntity getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public static class DataEntity implements Parcelable {
        /**
         * message : user Created successfully
         * token : XfPLA3Nxeu3M0eFxEMYyJsJVu4qPb-0EWIjQ74nX
         * identity : {"id":70,"type":0,"username":"","email":"eng_mhosni99@gmail.com","status":2,"first_name":"mohamed","last_name":"hosni","fax":"","phone":"","mobile1":"","mobile2":"","country_id":"","city_id":"","address":"","birth_date":"","industry_id":"","field_id":"","specialty_id":"","job":"","gender":"","bio":"","company_name":"","base_url":"","image_url":"","created_at":1481799318,"updated_at":1481799318,"logged_at":""}
         */

        private String message;
        private String token;
        private IdentityEntity identity;

        public void setMessage(String message) {
            this.message = message;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setIdentity(IdentityEntity identity) {
            this.identity = identity;
        }

        public String getMessage() {
            return message;
        }

        public String getToken() {
            return token;
        }

        public IdentityEntity getIdentity() {
            return identity;
        }

        public static class IdentityEntity implements Parcelable {
            /**
             * id : 70
             * type : 0
             * username :
             * email : eng_mhosni99@gmail.com
             * status : 2
             * first_name : mohamed
             * last_name : hosni
             * fax :
             * phone :
             * mobile1 :
             * mobile2 :
             * country_id :
             * city_id :
             * address :
             * birth_date :
             * industry_id :
             * field_id :
             * specialty_id :
             * job :
             * gender :
             * bio :
             * company_name :
             * base_url :
             * image_url :
             * created_at : 1481799318
             * updated_at : 1481799318
             * logged_at :
             */

            private int id;
            private int type;
            private String username;
            private String email;
            private int status;
            private String first_name;
            private String last_name;
            private String fax;
            private String phone;
            private String mobile1;
            private String mobile2;
            private String country_id;
            private String city_id;
            private String address;
            private String birth_date;
            private String industry_id;
            private String field_id;
            private String specialty_id;
            private String job;
            private String gender;
            private String bio;
            private String company_name;
            private String base_url;
            private String image_url;
            private int created_at;
            private int updated_at;
            private String logged_at;

            public void setId(int id) {
                this.id = id;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public void setFax(String fax) {
                this.fax = fax;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setMobile1(String mobile1) {
                this.mobile1 = mobile1;
            }

            public void setMobile2(String mobile2) {
                this.mobile2 = mobile2;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setBirth_date(String birth_date) {
                this.birth_date = birth_date;
            }

            public void setIndustry_id(String industry_id) {
                this.industry_id = industry_id;
            }

            public void setField_id(String field_id) {
                this.field_id = field_id;
            }

            public void setSpecialty_id(String specialty_id) {
                this.specialty_id = specialty_id;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setBio(String bio) {
                this.bio = bio;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public void setBase_url(String base_url) {
                this.base_url = base_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public void setLogged_at(String logged_at) {
                this.logged_at = logged_at;
            }

            public int getId() {
                return id;
            }

            public int getType() {
                return type;
            }

            public String getUsername() {
                return username;
            }

            public String getEmail() {
                return email;
            }

            public int getStatus() {
                return status;
            }

            public String getFirst_name() {
                return first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public String getFax() {
                return fax;
            }

            public String getPhone() {
                return phone;
            }

            public String getMobile1() {
                return mobile1;
            }

            public String getMobile2() {
                return mobile2;
            }

            public String getCountry_id() {
                return country_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public String getAddress() {
                return address;
            }

            public String getBirth_date() {
                return birth_date;
            }

            public String getIndustry_id() {
                return industry_id;
            }

            public String getField_id() {
                return field_id;
            }

            public String getSpecialty_id() {
                return specialty_id;
            }

            public String getJob() {
                return job;
            }

            public String getGender() {
                return gender;
            }

            public String getBio() {
                return bio;
            }

            public String getCompany_name() {
                return company_name;
            }

            public String getBase_url() {
                return base_url;
            }

            public String getImage_url() {
                return image_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public String getLogged_at() {
                return logged_at;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.type);
                dest.writeString(this.username);
                dest.writeString(this.email);
                dest.writeInt(this.status);
                dest.writeString(this.first_name);
                dest.writeString(this.last_name);
                dest.writeString(this.fax);
                dest.writeString(this.phone);
                dest.writeString(this.mobile1);
                dest.writeString(this.mobile2);
                dest.writeString(this.country_id);
                dest.writeString(this.city_id);
                dest.writeString(this.address);
                dest.writeString(this.birth_date);
                dest.writeString(this.industry_id);
                dest.writeString(this.field_id);
                dest.writeString(this.specialty_id);
                dest.writeString(this.job);
                dest.writeString(this.gender);
                dest.writeString(this.bio);
                dest.writeString(this.company_name);
                dest.writeString(this.base_url);
                dest.writeString(this.image_url);
                dest.writeInt(this.created_at);
                dest.writeInt(this.updated_at);
                dest.writeString(this.logged_at);
            }

            public IdentityEntity() {
            }

            protected IdentityEntity(Parcel in) {
                this.id = in.readInt();
                this.type = in.readInt();
                this.username = in.readString();
                this.email = in.readString();
                this.status = in.readInt();
                this.first_name = in.readString();
                this.last_name = in.readString();
                this.fax = in.readString();
                this.phone = in.readString();
                this.mobile1 = in.readString();
                this.mobile2 = in.readString();
                this.country_id = in.readString();
                this.city_id = in.readString();
                this.address = in.readString();
                this.birth_date = in.readString();
                this.industry_id = in.readString();
                this.field_id = in.readString();
                this.specialty_id = in.readString();
                this.job = in.readString();
                this.gender = in.readString();
                this.bio = in.readString();
                this.company_name = in.readString();
                this.base_url = in.readString();
                this.image_url = in.readString();
                this.created_at = in.readInt();
                this.updated_at = in.readInt();
                this.logged_at = in.readString();
            }

            public static final Parcelable.Creator<IdentityEntity> CREATOR = new Parcelable.Creator<IdentityEntity>() {
                public IdentityEntity createFromParcel(Parcel source) {
                    return new IdentityEntity(source);
                }

                public IdentityEntity[] newArray(int size) {
                    return new IdentityEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.message);
            dest.writeString(this.token);
            dest.writeParcelable(this.identity, 0);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.message = in.readString();
            this.token = in.readString();
            this.identity = in.readParcelable(IdentityEntity.class.getClassLoader());
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeParcelable(this.data, 0);
        dest.writeInt(this.status);
    }

    public RegisterUserModel() {
    }

    protected RegisterUserModel(Parcel in) {
        this.type = in.readString();
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.status = in.readInt();
    }

    public static final Parcelable.Creator<RegisterUserModel> CREATOR = new Parcelable.Creator<RegisterUserModel>() {
        public RegisterUserModel createFromParcel(Parcel source) {
            return new RegisterUserModel(source);
        }

        public RegisterUserModel[] newArray(int size) {
            return new RegisterUserModel[size];
        }
    };
}