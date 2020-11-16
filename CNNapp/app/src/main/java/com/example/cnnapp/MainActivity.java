package com.example.cnnapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity
{
    static final int REQUEST_TAKE_PHOTO = 1;
    String imageFileName;
    String mCurrentPhotoPath;
    ImageView myImage;
    TextView resultTextView;
    String baseUrl;
    Bitmap myBitmap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //REST API INSTALL CODE
        baseUrl = "http://3.82.138.53:4201/image/";
        //baseUrl = "http://3.82.138.53:4201/image/?file=@";
        //http://3.88.49.82:4201/breed/[IMAGE_NAME]
        //http://IP:PORT/image?file=FILENAME

        //Use activity_main.xml to style the app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView) findViewById(R.id.resultTextDisplay);
        myImage = (ImageView) findViewById(R.id.pictureDisplay);

    }

    /* For Settings Icon */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu, menu);
        //Hides home button
        MenuItem item = menu.findItem(R.id.idHome);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        //Moves to second activity (settings)
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //Displays image that was taken to user
        if (requestCode == 1)
        {
            File imgFile = new File(mCurrentPhotoPath);
            //Checks if File exists
            if (imgFile.exists())
            {
                //Displays image taken
                myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                myImage.setImageBitmap(myBitmap);

                //Stores the image under the gallery
                MediaStore.Images.Media.insertImage(getContentResolver(), myBitmap, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()), null);

/*                Cursor cursor = getContentResolver().query(Uri.parse(path), null, null, null, null);
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                baseUrl += cursor.getString(idx);*/

                Toast.makeText(this, "Image Taken", Toast.LENGTH_LONG).show();

                //REST API INSTALL CODE
                try {
                    ApiAuthenticationClient apiAuthenticationClient =
                            new ApiAuthenticationClient(baseUrl, myBitmap, imgFile);

                    AsyncTask<Void, Void, String> execute = new ExecuteNetworkOperation(apiAuthenticationClient);
                    execute.execute();
                } catch (Exception ex) {
                }
            }
            }
        }




    public void onButtonClick(View v)
    {
        //Calls method to open the camera
        dispatchTakePictureIntent();
    }

    private File createImageFile() throws IOException
    {
        //Create a filename for the image
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = "dogImage_" + timeStamp + ".png";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //Creates File
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        //Save a File: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            //Create the File where the photo should go
            File photoFile = null;
            try
            {
                photoFile = createImageFile();
            }
            catch (IOException ex)
            {
                //Error occurred while creating the File
                Toast.makeText(this, "Unable to Save Image", Toast.LENGTH_LONG).show();
            }

            //Continue only if the File was successfully created
            if (photoFile != null)
            {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                //Opens camera for user to take a picture
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
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

        /* Opens second activity to display results */
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //Display results
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText(apiAuthenticationClient.getLastResponse());
        }
    }
}





