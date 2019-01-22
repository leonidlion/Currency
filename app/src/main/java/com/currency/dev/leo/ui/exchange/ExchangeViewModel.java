package com.currency.dev.leo.ui.exchange;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.currency.dev.leo.data.RepositoryProvider;
import com.currency.dev.leo.data.model.Rate;

import java.util.List;

public class ExchangeViewModel extends ViewModel {

    public LiveData<List<Rate>> loadExchangeRates(List<String> currencies){
        return RepositoryProvider.getCurrencyRepository().getExchanges(currencies);
    }
}
