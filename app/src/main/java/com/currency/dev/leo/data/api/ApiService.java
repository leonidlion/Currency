package com.currency.dev.leo.data.api;

import com.currency.dev.leo.data.model.CurrencyResponse;
import com.currency.dev.leo.data.model.ExchangeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(".")
    Call<CurrencyResponse> getAllCurrencies(@Query("get") String get, @Query("key") String apiKey);

    @GET(".")
    Call<ExchangeResponse> getCurrencyExchange(@Query("get") String get, @Query("pairs") String pairs, @Query("key") String apiKey);
}
