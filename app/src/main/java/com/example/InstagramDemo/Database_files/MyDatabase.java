package com.example.InstagramDemo.Database_files;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.InstagramDemo.Database_files.PostContractor.FeedPost.COLUMN_DESC;
import static com.example.InstagramDemo.Database_files.PostContractor.FeedPost.COLUMN_IMAGE_PATH;
import static com.example.InstagramDemo.Database_files.PostContractor.FeedPost.TABLE_NAME;


public class MyDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Insta2.db";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    PostContractor.FeedPost._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DESC + " TEXT," +
                    COLUMN_IMAGE_PATH+" TEXT "+
                    ");";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
