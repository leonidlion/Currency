package com.currency.dev.leo.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExchangeResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Rate> rateList;

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

    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.message);
        dest.writeTypedList(this.rateList);
    }

    public ExchangeResponse() {
    }

    protected ExchangeResponse(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.rateList = in.createTypedArrayList(Rate.CREATOR);
    }

    public static final Parcelable.Creator<ExchangeResponse> CREATOR = new Parcelable.Creator<ExchangeResponse>() {
        @Override
        public ExchangeResponse createFromParcel(Parcel source) {
            return new ExchangeResponse(source);
        }

        @Override
        public ExchangeResponse[] newArray(int size) {
            return new ExchangeResponse[size];
        }
    };
}
