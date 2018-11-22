package com.example.myapplication.entity;

/**
 * Created by Hendi on 5/11/2018.
 */

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;



import com.example.myapplication.db.DatabaseContract;

import static android.provider.BaseColumns._ID;

import static com.example.myapplication.db.DatabaseContract.getColumnString;
import static com.example.myapplication.db.DatabaseContract.getColumnInt;



/**
 * Created by sidiqpermana on 11/23/16.
 */

public class NoteItem implements Parcelable {
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

    public NoteItem() {

    }

    public NoteItem(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, DatabaseContract.NoteColumns.TITLE);
        this.description = getColumnString(cursor, DatabaseContract.NoteColumns.DESCRIPTION);
        this.poster = getColumnString(cursor, DatabaseContract.NoteColumns.POSTER);
    }

    protected NoteItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.poster = in.readString();
    }

    public static final Parcelable.Creator<NoteItem> CREATOR = new Parcelable.Creator<NoteItem>() {
        @Override
        public NoteItem createFromParcel(Parcel source) {
            return new NoteItem(source);
        }

        @Override
        public NoteItem[] newArray(int size) {
            return new NoteItem[size];
        }
    };
}
