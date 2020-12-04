package com.example.cnnapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import androidx.exifinterface.media.ExifInterface;
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
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.*;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity
{
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int GALLERY_REQUEST = 9;
    String imageFileName;
    String mCurrentPhotoPath;
    ImageView myImage;
    TextView resultTextView;
    String baseUrl;
    Bitmap myBitmap;
    ProgressBar progressBar;
    File imgFile;
    Uri selectedImage;
    StringBuilder output = new StringBuilder();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //REST API INSTALL CODE
        baseUrl = "http://54.196.90.20:4201/image/";
                //"http://3.82.138.53:4201/image/";
        //baseUrl = "http://3.82.138.53:4201/image/?file=@";
        //http://3.88.49.82:4201/breed/[IMAGE_NAME]
        //http://IP:PORT/image?file=FILENAME

        //Use activity_main.xml to style the app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView) findViewById(R.id.resultTextDisplay);
        myImage = (ImageView) findViewById(R.id.pictureDisplay);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Set placeholder image
        myImage.setImageResource(R.drawable.dog_img_placeholder);

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
        if(selectedImage != null) {
            intent.putExtra("imageUri", selectedImage.toString());
        }
        startActivity(intent);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        resultTextView.setText("");
        //Displays image that was taken to user from Camera
        if (requestCode == 1)
        {
            imgFile = new File(mCurrentPhotoPath);
            selectedImage = Uri.parse(mCurrentPhotoPath);
            //Checks if File exists
            if (imgFile.exists())
            {
                //Displays image taken
                myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                myBitmap = displayImage(myBitmap);
                //myImage.setImageBitmap(myBitmap);

                //Stores the image under the gallery
                MediaStore.Images.Media.insertImage(getContentResolver(), myBitmap, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()), null);

                //Toast.makeText(this, "Image Taken", Toast.LENGTH_LONG).show();
            }
        }
        //Displays image chosen from Gallery
        else if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null)
        {
            try
            {
                //Get selected image uri
                selectedImage = data.getData();

                //Display image chosen
                myImage.setImageURI(selectedImage);

                //Convert uri to bitmap
                myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                //Get image filepath
                imgFile = new File(selectedImage.getPath());
            }
            catch(Exception e) {}
        }

        //Display progress bar
        progressBar.setVisibility(View.VISIBLE);

        //REST API INSTALL CODE
        try {
            ApiAuthenticationClient apiAuthenticationClient =
                    new ApiAuthenticationClient(baseUrl, myBitmap, imgFile, "");

            AsyncTask<Void, Void, String> execute = new ExecuteNetworkOperation(apiAuthenticationClient);
            execute.execute();
        } catch (Exception ex) {
        }
    }

    public void onButtonClick(View v)
    {
        //Calls method to open the camera
        dispatchTakePictureIntent();
    }

    public void onGalleryClick(View v)
    {
        //Calls method to get image from Gallery
        getImageFromGallery();
    }

    public void getImageFromGallery()
    {
        //Opens gallery and returns image selected
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
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


    /* To fix image rotation issue */
    public Bitmap displayImage(Bitmap myBitmap)
    {
        int orientation = 0;
        Bitmap rotatedBitmap = null;

        try {
            ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
            orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
        }
        catch (Exception e) { }

        switch (orientation)
        {
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(myBitmap, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(myBitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(myBitmap, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = myBitmap;

        }
        myImage.setImageBitmap(rotatedBitmap);
        return rotatedBitmap;
    }

    public static Bitmap rotateImage(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
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

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //Hide progress bar
            progressBar.setVisibility(View.INVISIBLE);

            //Display results
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText(apiAuthenticationClient.getLastResponse());

            /*

            String jsonResult = (apiAuthenticationClient.getLastResponse());
            //json = new JSONObject("{\"result\":\"{'breed':'toy_poodle','confidence':98.9319984654}\"}");

            try {
                JSONObject jsonObject = new JSONObject(jsonResult);
                JSONObject resultObject = jsonObject.getJSONObject("result");

                //String string = jsonObject.getString("result").replaceAll("[,}:{_\"\']", " ");

                String breed = resultObject.getString("label");
                String confidence = resultObject.getString("confidence");

                output.append(String.format("%s \n %s ", breed, confidence));


            } catch (JSONException e) {
                e.printStackTrace();
            }

            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText(output.toString());

            */


        }
    }
}





