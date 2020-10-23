package com.example.cnnapp;

import org.junit.Before;
import org.junit.Test;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static org.junit.Assert.*;
import android.os.Environment;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
*/

public class UnitTesting {
    @Test
    public void dispatchTakePictureIntent()
    {
        //Opens camera app
    }

    /* Test to see if file can be created */
    @Test
    public void createImageFile() throws IOException
    {
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

    @Test
    public void onActivityResult()
    {
        //Saving image to gallery

    }
}

/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
/*
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeTextBehaviorTest {

    public static final String STRING_TO_BE_TYPED = "Espresso";

    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    //@Rule public ActivityScenarioRule<MainActivity> activityScenarioRule
    //        = new ActivityScenarioRule<>(MainActivity.class);
/*
    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @Test
    public void changeText_newActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput)).perform(typeText(STRING_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.activityChangeTextBtn)).perform(click());

        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.show_text_view)).check(matches(withText(STRING_TO_BE_TYPED)));
    }
}*/





