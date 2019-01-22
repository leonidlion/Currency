package com.currency.dev.leo.enums;

public enum CurrencyGet {
    LIST("currency_list"),
    RATES("rates");

    String dataTypes;

    CurrencyGet(String dataTypes){
        this.dataTypes = dataTypes;
    }

    public String getDataTypes() {
        return dataTypes;
    }
}
