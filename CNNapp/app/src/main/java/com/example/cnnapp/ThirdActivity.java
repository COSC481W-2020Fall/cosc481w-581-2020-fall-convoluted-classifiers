package com.example.cnnapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.LinkedList;

public class ThirdActivity extends AppCompatActivity
{
    DatabaseManager db;
    File imgFile;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
            imgFile = new File(imgURI.getPath());

            String breed = extras.getString("breed");
            String confidence = extras.getString("confidence");

        }
        catch (Exception e) {}

        db = new DatabaseManager(this);

        //Build table and add image, breed, and confidence
        buildTable();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void buildTable()
    {
        LinkedList<History> list = db.display();

        TableLayout tl = (TableLayout) findViewById(R.id.main_table);

        TableRow trHead = new TableRow(this);
        trHead.setId(TableLayout.generateViewId());
        trHead.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        //Get amount of rows in database
        TableRow[] tr_head = new TableRow[list.size()];
        ImageView[] imageArray = new ImageView[list.size()];
        TextView[] textBreed = new TextView[list.size()];
        TextView[] textConfidence = new TextView[list.size()];

        //Get row
        for(int i = 0; i < list.size(); i++)
        {
            //Create the table rows
            tr_head[i] = new TableRow(this);
            tr_head[i].setId(i+1);
            tr_head[i].setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            //Display dog image
            imageArray[i] = new ImageView(this);
            imageArray[i].setId(i+111);
            try {
                String pathStr = list.get(i).getImage();
                Bitmap myBitmap = BitmapFactory.decodeFile(pathStr);
                imageArray[i].setImageBitmap(myBitmap);
            }
            catch(Exception e) { }

            imageArray[i].setPadding(5, 5, 5, 5);
            tr_head[i].addView(imageArray[i]);
            imageArray[i].getLayoutParams().height = 250;
            imageArray[i].getLayoutParams().width= 350;
            imageArray[i].requestLayout();

            //Create the TextView breed
            textBreed[i] = new TextView(this);
            textBreed[i].setId(i+111);
            textBreed[i].setText(list.get(i).getBreed());
            textBreed[i].setPadding(5, 5, 5, 5);
            textBreed[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textBreed[i].setMaxWidth(400);
            tr_head[i].addView(textBreed[i]);

            //Create the TextView confidence
            textConfidence[i] = new TextView(this);
            textConfidence[i].setId(i+111);
            textConfidence[i].setText(list.get(i).getConfidence().substring(0, 4)  + "%");
            textConfidence[i].setPadding(5, 5, 5, 5);
            textConfidence[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            tr_head[i].addView(textConfidence[i]);

            //Add each row to table
            tl.addView(tr_head[i], new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT));
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
