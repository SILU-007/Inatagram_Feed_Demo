package com.example.InstagramDemo.Database_files;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.InstagramDemo.Data_gettter_setters.feedData;

import java.util.ArrayList;


public final class CRUDHelper {

    private static Cursor cursor;
    private static Context context;
    public static boolean dropRecord(SQLiteDatabase database,long id)
    {
        boolean ret= database.delete(PostContractor.FeedPost.TABLE_NAME,
                PostContractor.FeedPost._ID+"="+id,null)>0;

        return ret;
    }

    //method to delete all records from database
    public static void dropAllRecord(SQLiteDatabase database) {
        String DELETE_ALL_RECORDS = " DELETE FROM " + PostContractor.FeedPost.TABLE_NAME;
        database.execSQL(DELETE_ALL_RECORDS);
    }


    public CRUDHelper(Context context) {
        this.context = context;
    }

    //method to insert the records
    public static void insertDataToDatabase(SQLiteDatabase database, ArrayList<feedData> feedDataArrayList) {

        for (feedData feed_data_for_post : feedDataArrayList) {
            ContentValues cv = new ContentValues();
            cv.put(PostContractor.FeedPost.COLUMN_DESC, feed_data_for_post.getFeedDescription());
            cv.put(PostContractor.FeedPost.COLUMN_IMAGE_PATH,feed_data_for_post.getFeedImagePath());
            //Toast.makeText(context, feed_data_for_post.getFeedImagePath(), Toast.LENGTH_SHORT).show();
            database.insert(PostContractor.FeedPost.TABLE_NAME, null, cv);
            cv.clear();
        }
    }

/////////////////--------------------------------//getImages//------------------------------------//////////////////////
public static ArrayList<feedData> getAllImageStatus(SQLiteDatabase database){

    cursor = getAllData(database);

    ArrayList<feedData> posts = new ArrayList<>();

    while (cursor.moveToNext()) {
        feedData postData;
        /// get long column id
        long source_id = cursor.getLong(cursor.getColumnIndex(PostContractor.FeedPost._ID));
        /// gets the description
        String imagePath=null;
        try {
            imagePath=cursor.getString(cursor.getColumnIndex(PostContractor.FeedPost.COLUMN_IMAGE_PATH));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        /// adds the retrieved data from database to feeddata type variable
        postData = new feedData(source_id,imagePath);
        /// now adds the postData item to ArrayList
        posts.add(postData);
    }
    /// closing the open data cursor on database
    cursor.close();

    return posts;

}

///////////-------------------------    //method that return to all data from Database -----------------------------------------
   public static ArrayList<feedData> getAllRecords(SQLiteDatabase database){

         cursor = getAllData(database);

        ArrayList<feedData> posts = new ArrayList<>();

        while (cursor.moveToNext()) {
            feedData postData;
            /// get long column id
            long source_id = cursor.getLong(cursor.getColumnIndex(PostContractor.FeedPost._ID));
            /// gets the description
            String description = cursor.getString(cursor.getColumnIndex(PostContractor.FeedPost.COLUMN_DESC));
            String imagePath=null;
            try {
                 imagePath=cursor.getString(cursor.getColumnIndex(PostContractor.FeedPost.COLUMN_IMAGE_PATH));
            }
            catch (Exception e)
            {
             e.printStackTrace();
            }
            /// adds the retrieved data from database to feeddata type variable
            postData = new feedData(description,source_id,imagePath);
            /// now adds the postData item to ArrayList
            posts.add(postData);
        }
        /// closing the open data cursor on database
        cursor.close();

        return posts;

    }


    private static Cursor getAllData(SQLiteDatabase database) {

        return database.query(
                PostContractor.FeedPost.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                PostContractor.FeedPost._ID+" DESC ");

    }
//////////////////////////////////////--------------------------------------------------------//////////////////////////////////
}
