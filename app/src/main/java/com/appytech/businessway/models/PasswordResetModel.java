package com.appytech.businessway.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PasswordResetModel implements Parcelable {

    /**
     * type : success
     * data : ["Check your email for further instructions."]
     * status : 200
     */

    private String type;
    private int status;
    private List<String> data;

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public List<String> getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeInt(this.status);
        dest.writeStringList(this.data);
    }

    public PasswordResetModel() {
    }

    protected PasswordResetModel(Parcel in) {
        this.type = in.readString();
        this.status = in.readInt();
        this.data = in.createStringArrayList();
    }

    public static final Parcelable.Creator<PasswordResetModel> CREATOR = new Parcelable.Creator<PasswordResetModel>() {
        public PasswordResetModel createFromParcel(Parcel source) {
            return new PasswordResetModel(source);
        }

        public PasswordResetModel[] newArray(int size) {
            return new PasswordResetModel[size];
        }
    };
}