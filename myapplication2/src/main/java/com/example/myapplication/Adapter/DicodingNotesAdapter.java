package com.example.myapplication.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import static com.example.myapplication.db.DatabaseContract.NoteColumns.DATE;
import static com.example.myapplication.db.DatabaseContract.NoteColumns.DESCRIPTION;
import static com.example.myapplication.db.DatabaseContract.NoteColumns.POSTER;
import static com.example.myapplication.db.DatabaseContract.NoteColumns.TITLE;
import static com.example.myapplication.db.DatabaseContract.getColumnString;
/**
 * Created by Hendi on 5/11/2018.
 */

public class DicodingNotesAdapter extends CursorAdapter {

    public DicodingNotesAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dicoding_note, viewGroup, false);
        return view;
    }


    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null){
            TextView tvTitle = (TextView)view.findViewById(R.id.tv_item_title);
           // TextView tvDate = (TextView)view.findViewById(R.id.tv_item_date);
            TextView tvDescription = (TextView)view.findViewById(R.id.tv_item_description);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);



            tvTitle.setText(getColumnString(cursor,TITLE));
            tvDescription.setText(getColumnString(cursor,DESCRIPTION));
           // tvDate.setText(getColumnString(cursor,POSTER));
            String posterImg = getColumnString(cursor,POSTER);

            //Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+POSTER).into(imageView);
            Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+posterImg).into(imageView);

        }
    }
}