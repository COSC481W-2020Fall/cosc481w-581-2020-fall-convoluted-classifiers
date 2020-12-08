package com.example.cnnapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ThirdActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //Display image
        try
        {
            //Get file path
            Bundle extras = getIntent().getExtras();
            Uri imgURI = Uri.parse(extras.getString("imageUri"));
            File imgFile = new File(imgURI.getPath());

            String breed = extras.getString("breed");
            String confidence = extras.getString("confidence");

        }
        catch (Exception e) {}
    }

    /* For Home Icon */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu, menu);
        //Hides setting icon
        MenuItem item = menu.findItem(R.id.idSettings);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        //Moves to main activity
        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    public void onButtonClick(View v)
    {
        //Moves to second activity
        Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
        startActivity(intent);
        //setContentView(R.layout.activity_third);
    }
}
