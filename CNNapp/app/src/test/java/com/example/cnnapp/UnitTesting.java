package com.example.cnnapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import static org.robolectric.Shadows.shadowOf;
import static android.os.Looper.getMainLooper;
import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.P)
public class UnitTesting
{
    private MainActivity activity;
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    /* Test to see if application can access the camera */
    @Test
    public void testCameraAccess() {
        Context context = activity;
        PackageManager packageManager = context.getPackageManager();
        assertEquals(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA), false);
    }

    /* Test to see if file can be created */
    @Test
    public void createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStorageDirectory();

        //Creates File
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        assertNotNull(imageFile);
    }

    /* Test to see if Second Activity can be opened */
    /* Not Working - null*/
    @Test
    public void testOpeningSecondActivity() throws NullPointerException
    {
        activity.goToSecondActivity();
    }
}





