package com.example.hendi.myapplication.entity;

/**
 * Created by Hendi on 5/11/2018.
 */

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;


import  com.example.hendi.myapplication.db.DatabaseContract;

import static android.provider.BaseColumns._ID;
import   static com.example.hendi.myapplication.db.DatabaseContract.getColumnInt;
import   static com.example.hendi.myapplication.db.DatabaseContract.getColumnString;



/**
 * Created by sidiqpermana on 11/23/16.
 */

public class Note implements Parcelable {
    private int id;
    private String title;
    private String description;
    private String date;
    private String poster;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPoster(String poster){
        this.poster = poster;
    }
    public String getPoster(){
        return poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.poster);
    }

    public Note() {

    }

    public Note(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, DatabaseContract.NoteColumns.TITLE);
        this.description = getColumnString(cursor, DatabaseContract.NoteColumns.DESCRIPTION);
       this.poster = getColumnString(cursor, DatabaseContract.NoteColumns.POSTER);
    }

    protected Note(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.poster = in.readString();
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
