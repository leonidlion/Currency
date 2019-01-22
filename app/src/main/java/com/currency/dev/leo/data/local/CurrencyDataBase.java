package com.currency.dev.leo.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.currency.dev.leo.Constants;
import com.currency.dev.leo.data.local.dao.CurrencyDao;
import com.currency.dev.leo.data.local.entity.Currency;

@Database(entities = Currency.class, version = Constants.DB_VERSION, exportSchema = false)
public abstract class CurrencyDataBase extends RoomDatabase {
    private static CurrencyDataBase instance;

    public static synchronized CurrencyDataBase getInstance(final Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, CurrencyDataBase.class, Constants.DATABASE_NAME)
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build();
        }
        return instance;
    }

    public abstract CurrencyDao currencyDao();
}
