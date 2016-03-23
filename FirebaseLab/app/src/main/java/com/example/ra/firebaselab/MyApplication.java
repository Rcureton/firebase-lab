package com.example.ra.firebaselab;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Ra on 3/23/16.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
