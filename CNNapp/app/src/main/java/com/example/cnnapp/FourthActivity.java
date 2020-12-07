package com.example.cnnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class FourthActivity extends AppCompatActivity
{
    ImageView image;
    EditText userInput;
    Button submitBtn;
    String baseUrl;
    Bitmap myBitmap;
    Uri imgURI;
    File imgFile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //REST API INSTALL CODE
        baseUrl = "http://54.196.90.20:4201/correction/";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        image = (ImageView)findViewById(R.id.pictureDisplay);
        userInput = (EditText) findViewById(R.id.breedInput);
        submitBtn = (Button) findViewById(R.id.submitButton);

        //Enable submit button and text box
        submitBtn.setEnabled(true);
        userInput.setEnabled(true);

        //Display image
        try
        {
            //Get file path
            Bundle extras = getIntent().getExtras();
            imgURI = Uri.parse(extras.getString("imageUri"));
            imgFile = new File(imgURI.getPath());

            //Display image
            image.setImageURI(imgURI);
        }
        catch (Exception e)
        {
            //Prevent user from entering dog breed and submitting without a image chosen/taken
            userInput.setEnabled(false);
            //Disable submit button
            submitBtn.setEnabled(false);
        }
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
        //Grab user input - dog breed
        userInput = (EditText) findViewById(R.id.breedInput);
        String input = userInput.getText().toString();

        //Get bitmap
        try
        {
            myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgURI);
        }
        catch(Exception e) { }

        //REST API INSTALL CODE
        try {
            ApiAuthenticationClient apiAuthenticationClient =
                    new ApiAuthenticationClient(baseUrl, myBitmap, imgFile, input);

            AsyncTask<Void, Void, String> execute = new FourthActivity.ExecuteNetworkOperation(apiAuthenticationClient);
            execute.execute();
        } catch (Exception ex) {
        }
    }

    public void onCancelClick(View v)
    {
        //Moves back to second activity
        Intent intent = new Intent(FourthActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    //REST API INSTALL CODE
    /**
     * This subclass handles the network operations in a new thread.
     * It starts the progress bar, makes the API call, and ends the progress bar.
     */
    public class ExecuteNetworkOperation extends AsyncTask<Void, Void, String> {

        private ApiAuthenticationClient apiAuthenticationClient;
        private String returnValue;

        /**
         * Overload the constructor to pass objects to this class.
         */
        public ExecuteNetworkOperation(ApiAuthenticationClient apiAuthenticationClient) {
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* Sends request to server */
        @Override
        protected String doInBackground(Void... params) {
            try {
                returnValue = apiAuthenticationClient.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Display results
            Toast.makeText(FourthActivity.this, "Thank you, your response has been submitted!", Toast.LENGTH_LONG).show();

            //Prevent user from submitting multiple times
            submitBtn.setEnabled(false);
            userInput.setEnabled(false);
            }
        }
    }