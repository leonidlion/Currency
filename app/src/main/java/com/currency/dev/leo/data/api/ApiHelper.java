package com.currency.dev.leo.data.api;

import com.currency.dev.leo.BuildConfig;
import com.currency.dev.leo.Constants;
import com.currency.dev.leo.data.BaseCallback;
import com.currency.dev.leo.data.model.CurrencyResponse;
import com.currency.dev.leo.data.model.ExchangeResponse;
import com.currency.dev.leo.enums.CurrencyGet;
import com.currency.dev.leo.utils.ApiUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private static ApiHelper instance;
    private ApiService service;

    private ApiHelper(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(ExchangeResponse.class, new ExchangeDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(ApiService.class);
    }

    public static synchronized ApiHelper getInstance(){
        if (instance == null)
            instance = new ApiHelper();
        return instance;
    }

    public void getAllCurrencies(final BaseCallback<CurrencyResponse> callback){
        Call<CurrencyResponse> call = service.getAllCurrencies(CurrencyGet.LIST.getDataTypes(), BuildConfig.API_KEY);
        call.enqueue(ApiUtils.getGenericCallback(callback));
    }

    public void getCurrencyExchange(List<String> currencies, final BaseCallback<ExchangeResponse> callback){
        String query = currencies.toString();
        query = query.substring(1, query.length() - 1).replaceAll(" ", "");
        Call<ExchangeResponse> call = service.getCurrencyExchange(CurrencyGet.RATES.getDataTypes(), query, BuildConfig.API_KEY);
        call.enqueue(ApiUtils.getGenericCallback(callback));
    }
}
