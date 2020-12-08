package com.example.cnnapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    public void onButtonClick(View v)
    {
       //Moves to third activity
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        Bundle extras = getIntent().getExtras();
        try {
            if (extras.getString("imageUri") != null) {
                String imgURI = extras.getString("imageUri");
                String breed = extras.getString("breed");
                String confidence = extras.getString("confidence");

                intent.putExtra("imageUri", imgURI);
                intent.putExtra("breed", breed);
                intent.putExtra("confidence", confidence);
            }
        }
        catch (Exception e) { }
        startActivity(intent);
    }

    public void onYesClick(View v)
    {
        //Moves to fourth activity
        Intent intent = new Intent(SecondActivity.this, FourthActivity.class);

        Bundle extras = getIntent().getExtras();
        try {
            if (extras.getString("imageUri") != null) {
                String imgURI = extras.getString("imageUri");
                intent.putExtra("imageUri", imgURI);
            }
        }
        catch (Exception e) { }
        startActivity(intent);
    }
}
