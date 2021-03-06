package com.appytech.businessway.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ListCityModel implements Parcelable {

    /**
     * type : success
     * data : [{"id":"4","name":"القاهرة"},{"id":"5","name":"اﻻسكندرية "}]
     * status : 200
     */

    private String type;
    private int status;
    private List<DataEntity> data;

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Parcelable {
        /**
         * id : 4
         * name : القاهرة
         */

        private String id;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
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
        dest.writeInt(this.status);
        dest.writeTypedList(data);
    }

    public ListCityModel() {
    }

    protected ListCityModel(Parcel in) {
        this.type = in.readString();
        this.status = in.readInt();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
    }

    public static final Parcelable.Creator<ListCityModel> CREATOR = new Parcelable.Creator<ListCityModel>() {
        public ListCityModel createFromParcel(Parcel source) {
            return new ListCityModel(source);
        }

        public ListCityModel[] newArray(int size) {
            return new ListCityModel[size];
        }
    };
}