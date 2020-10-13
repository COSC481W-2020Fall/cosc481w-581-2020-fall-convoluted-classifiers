package com.example.cnnapp;

import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class Application  extends android.app.Application {
    public void onCreate() {
        super.onCreate();

        try {
            //Add the AWSCognitoAuthPlugin and AWSS3StoragePlugin plugins
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());

            Amplify.configure(getApplicationContext());
            Log.i("CNNapp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("CNNapp", "Could not initialize Amplify", error);
        }
    }

}
