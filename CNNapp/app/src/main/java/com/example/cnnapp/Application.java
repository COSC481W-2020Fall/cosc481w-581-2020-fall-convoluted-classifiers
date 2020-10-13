package com.example.cnnapp;

import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;

public class Application  extends android.app.Application {
    public void onCreate() {
        super.onCreate();

        try {
            Amplify.configure(getApplicationContext());
            Log.i("CNNapp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("CNNapp", "Could not initialize Amplify", error);
        }
    }

}
