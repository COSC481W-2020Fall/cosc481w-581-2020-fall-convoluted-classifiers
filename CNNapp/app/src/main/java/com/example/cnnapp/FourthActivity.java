package com.example.cnnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class FourthActivity extends AppCompatActivity
{
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        image = (ImageView)findViewById(R.id.pictureDisplay);

        //Display dog image
        Bitmap bmp;
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        try
        {
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            image.setImageBitmap(bmp);
        } catch (Exception e) {}
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
        Intent intent = new Intent(FourthActivity.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    public void onSubmitClick(View v)
    {

    }

    public void onCancelClick(View v)
    {
        //Moves back to second activity
        Intent intent = new Intent(FourthActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}