package com.example.careswift.database;

import android.app.Service;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ServiceEntity.class}, version = 1)
public abstract class ServicesDataBase extends RoomDatabase {
    public abstract ServiceDao serviceDao();

    private static volatile ServicesDataBase INSTANCE;

    public static ServicesDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ServicesDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ServicesDataBase.class, "services_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

