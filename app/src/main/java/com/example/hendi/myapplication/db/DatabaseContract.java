package com.example.hendi.myapplication.db;

/**
 * Created by Hendi on 5/11/2018.
 */
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dicoding on 10/12/2017.
 */

public class DatabaseContract {

    public static String TABLE_NOTE = "note";

    public static final class NoteColumns implements BaseColumns {

        //Note title
        public  static String TITLE = "title";
        //Note description
        public static String DESCRIPTION = "description";
        //Note date
        public static String DATE = "date";
        public static String POSTER = "poster";

    }
    public static final String AUTHORITY = "com.example.hendi.myapplication";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NOTE)
            .build();
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }

}