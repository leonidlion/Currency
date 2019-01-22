package com.currency.dev.leo.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.currency.dev.leo.data.local.entity.Currency;

import java.util.List;

@Dao
public interface CurrencyDao extends BaseDao<Currency> {
    @Query("select * from currency order by currencyName desc")
    List<Currency> getAllCurrency();

    @Query("select * from currency where id =:id")
    Currency getCurrencyById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Currency> currencies);
}
