package com.example.careswift;

import android.app.Application;

import com.example.careswift.database.DbInitializer;

public class MyApplication extends Application {
   // private static MyApplication instance;
   /* public static MyApplication getInstance() {
        return instance;
    } */
    @Override
    public void onCreate() {
        super.onCreate();
        DbInitializer.initializeServicesDatabase(getApplicationContext());
       // instance = this;
    }
}

