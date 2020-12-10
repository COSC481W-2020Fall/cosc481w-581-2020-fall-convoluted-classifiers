package com.example.cnnapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.LinkedList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASENAME = "HistoryDatabase";
    private static final String TABLENAME = "DogHistory";
    private static final int DATABASEVERSION = 1;


    public DatabaseManager(@Nullable Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String command = "create Table " + TABLENAME + "(IMAGE image, " + "BREED breed, " + "CONFIDENCE confidence)";
        sqLiteDatabase.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void delete()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
    }

    public void create()
    {
        SQLiteDatabase db = getWritableDatabase();
        String command = "create Table " + TABLENAME + "(IMAGE image, " + "BREED breed, " + "CONFIDENCE confidence)";
        db.execSQL(command);
    }

    public void insert(History history) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues row = new ContentValues();

        row.put("IMAGE", history.getImage());
        row.put("BREED", history.getBreed());
        row.put("CONFIDENCE", history.getConfidence());

        db.insert(TABLENAME, null, row);
        db.close();
    }


    public LinkedList<History> display() {
        SQLiteDatabase db = getWritableDatabase();
        LinkedList<History> list = new LinkedList<>();
        Cursor cursor = db.query(TABLENAME, new String[] {"IMAGE", "BREED", "CONFIDENCE"}, null, null, null, null, null);

        while(cursor.moveToNext()){
            String image = cursor.getString(0);
            String breed = cursor.getString(1);
            String confidence = cursor.getString(2);

            History history = new History(image, breed, confidence);
            list.addLast(history);
        }

        cursor.close();
        db.close();

        return list;
    }

}
