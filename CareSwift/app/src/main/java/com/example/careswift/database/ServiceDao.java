package com.example.careswift.database;

import android.app.Service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServiceDao {
    @Insert
    void insert(ServiceEntity service);

    @Query("SELECT * FROM services LIMIT 9")
    List<ServiceEntity> getAllServices();
}
