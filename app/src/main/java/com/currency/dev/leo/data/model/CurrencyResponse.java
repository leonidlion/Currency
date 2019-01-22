package com.currency.dev.leo.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CurrencyResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<String> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public List<com.currency.dev.leo.data.local.entity.Currency> dataToEntity(){
        List<com.currency.dev.leo.data.local.entity.Currency> currencies = new ArrayList<>();
        for (String x : data){
            currencies.add(new com.currency.dev.leo.data.local.entity.Currency(x));
        }
        return currencies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.message);
        dest.writeStringList(this.data);
    }

    public CurrencyResponse() {
    }

    protected CurrencyResponse(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.createStringArrayList();
    }

    public static final Parcelable.Creator<CurrencyResponse> CREATOR = new Parcelable.Creator<CurrencyResponse>() {
        @Override
        public CurrencyResponse createFromParcel(Parcel source) {
            return new CurrencyResponse(source);
        }

        @Override
        public CurrencyResponse[] newArray(int size) {
            return new CurrencyResponse[size];
        }
    };
}
