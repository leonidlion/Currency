package com.currency.dev.leo.data;

import com.currency.dev.leo.data.api.ApiHelper;
import com.currency.dev.leo.data.local.CurrencyDataBase;

public class RepositoryProvider {
    private static CurrencyRepository currencyRepository;

    public static void initCurrencyRepository(CurrencyDataBase dataBase, ApiHelper apiHelper){
        currencyRepository = new CurrencyRepository(dataBase, apiHelper);
    }

    public static CurrencyRepository getCurrencyRepository() {
        return currencyRepository;
    }
}
