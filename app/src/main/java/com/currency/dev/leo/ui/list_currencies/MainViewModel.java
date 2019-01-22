package com.currency.dev.leo.ui.list_currencies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.currency.dev.leo.data.RepositoryProvider;
import com.currency.dev.leo.data.local.entity.Currency;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Currency>> currencyLiveData;

    public MainViewModel() {
        currencyLiveData = RepositoryProvider.getCurrencyRepository().getAllCurrencies(false);
    }

    public LiveData<List<Currency>> getCurrencyLiveData() {
        return currencyLiveData;
    }
}
