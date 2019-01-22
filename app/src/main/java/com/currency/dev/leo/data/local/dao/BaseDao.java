package com.currency.dev.leo.data.local.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T data);

    @Delete
    int delete(T data);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(T data);
}
