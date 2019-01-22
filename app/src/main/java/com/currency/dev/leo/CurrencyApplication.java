package com.currency.dev.leo;

import android.app.Application;

import com.currency.dev.leo.data.RepositoryProvider;
import com.currency.dev.leo.data.api.ApiHelper;
import com.currency.dev.leo.data.local.CurrencyDataBase;

public class CurrencyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final ApiHelper helper = ApiHelper.getInstance();
        RepositoryProvider.initCurrencyRepository(CurrencyDataBase.getInstance(this), helper);
    }
}
