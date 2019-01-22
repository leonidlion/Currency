package com.currency.dev.leo.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Rate implements Parcelable {
    private String currencyName;
    private Double currencyRate;

    public Rate(String currencyName, Double currencyRate) {
        this.currencyName = currencyName;
        this.currencyRate = currencyRate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(Double currencyRate) {
        this.currencyRate = currencyRate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.currencyName);
        dest.writeValue(this.currencyRate);
    }

    public Rate() {
    }

    protected Rate(Parcel in) {
        this.currencyName = in.readString();
        this.currencyRate = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Rate> CREATOR = new Parcelable.Creator<Rate>() {
        @Override
        public Rate createFromParcel(Parcel source) {
            return new Rate(source);
        }

        @Override
        public Rate[] newArray(int size) {
            return new Rate[size];
        }
    };
}
