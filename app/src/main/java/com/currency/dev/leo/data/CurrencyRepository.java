package com.currency.dev.leo.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.currency.dev.leo.data.api.ApiHelper;
import com.currency.dev.leo.data.local.CurrencyDataBase;
import com.currency.dev.leo.data.local.entity.Currency;
import com.currency.dev.leo.data.model.CurrencyResponse;
import com.currency.dev.leo.data.model.ExchangeResponse;
import com.currency.dev.leo.data.model.Rate;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CurrencyRepository {
    private final Executor executor = Executors.newFixedThreadPool(2);
    private CurrencyDataBase dataBase;
    private ApiHelper apiHelper;

    public CurrencyRepository(CurrencyDataBase dataBase, ApiHelper apiHelper){
        this.dataBase = dataBase;
        this.apiHelper = apiHelper;
    }

    public LiveData<List<Currency>> getAllCurrencies(boolean forceUpdate){
        MutableLiveData<List<Currency>> liveData = new MutableLiveData<>();
        executor.execute(()->{
            List<Currency> currencyList = dataBase.currencyDao().getAllCurrency();
            if (currencyList.isEmpty() || forceUpdate){
                apiHelper.getAllCurrencies(new BaseCallback<CurrencyResponse>() {
                    @Override
                    public void onSuccess(CurrencyResponse data) {
                        liveData.setValue(data.dataToEntity());
                        executor.execute(()-> dataBase.currencyDao().insertAll(data.dataToEntity()));
                    }

                    @Override
                    public void onError(int code, String message) {
                        liveData.setValue(null);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        liveData.setValue(null);
                    }
                });
            } else liveData.postValue(currencyList);
        });
        return liveData;
    }

    public LiveData<List<Rate>> getExchanges(List<String> currencies){
        MutableLiveData<List<Rate>> liveData = new MutableLiveData<>();
        apiHelper.getCurrencyExchange(currencies, new BaseCallback<ExchangeResponse>() {
            @Override
            public void onSuccess(ExchangeResponse data) {
                liveData.setValue(data.getRateList());
            }

            @Override
            public void onError(int code, String message) {
                liveData.setValue(null);
            }

            @Override
            public void onFailure(Throwable throwable) {
                liveData.setValue(null);
            }
        });
        return liveData;
    }
}
