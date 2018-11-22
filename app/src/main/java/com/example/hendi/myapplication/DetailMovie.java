package com.example.hendi.myapplication;

/**
 * Created by Hendi on 5/1/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hendi.myapplication.db.NoteHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.hendi.myapplication.db.DatabaseContract.CONTENT_URI;
import static com.example.hendi.myapplication.db.DatabaseContract.NoteColumns.DATE;
import static com.example.hendi.myapplication.db.DatabaseContract.NoteColumns.DESCRIPTION;
import static com.example.hendi.myapplication.db.DatabaseContract.NoteColumns.POSTER;
import static com.example.hendi.myapplication.db.DatabaseContract.NoteColumns.TITLE;

public class DetailMovie extends AppCompatActivity implements View.OnClickListener {
    Button button;
    public static String EXTRA_TITLE        = "extra_title";
    public static String EXTRA_OVERVIEW     = "extra_overview";
    public static String EXTRA_RELEASE_DATE = "extra_release_date";
    public static String EXTRA_POSTER_JPG   = "extra_poster_jpg";
    public static String EXTRA_RATE_COUNT   = "extra_rate_count";
    public static String EXTRA_RATE         = "extra_rate";
    public static int REQUEST_UPDATE = 200;
    private TextView textView,textOverview,textRate,textRateCount,textReleaseDate;
    private Context context;
    private ImageView  imageView,imageView2;
    public String title,overview,poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        setTitle("movie");
        NoteHelper noteHelper = new NoteHelper(DetailMovie.this);

        textView = (TextView)findViewById(R.id.textTitle);
        textOverview = (TextView)findViewById(R.id.textOverview);
        textRate = (TextView)findViewById(R.id.textRating);
        textRateCount = (TextView)findViewById(R.id.textTotalVote);
        textReleaseDate= (TextView)findViewById(R.id.textReleaseDate);

        button = (Button)findViewById(R.id.add);
        button.setOnClickListener(this);

        imageView = (ImageView)findViewById(R.id.imageView);
        // imageView2 = (ImageView)findViewById(R.id.imageView2);




         title = getIntent().getStringExtra(EXTRA_TITLE);
         poster = getIntent().getStringExtra(EXTRA_POSTER_JPG);
         overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String rating = getIntent().getStringExtra(EXTRA_RATE);
        String totalVote = getIntent().getStringExtra(EXTRA_RATE_COUNT);
        String releaseDate = getIntent().getStringExtra(EXTRA_RELEASE_DATE);

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = date_format.parse(releaseDate);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String date_of_release = new_date_format.format(date);
            textReleaseDate.setText(date_of_release);
        }catch (ParseException e){
            e.printStackTrace();
        }



        textView.setText(title);
        textOverview.setText(overview);
        textRate.setText(rating+ "/10");
        textRateCount.setText(totalVote+" Votes");


        Glide.with(DetailMovie.this).load("http://image.tmdb.org/t/p/w185/"+poster).into(imageView);

    }

    @Override
    public void onClick(View v) {
        ContentValues values = new ContentValues();
        values.put(TITLE,title);
       values.put(DESCRIPTION,overview);
        values.put(POSTER,poster);
       // values.put(DATE,getCurrentDate());

        getContentResolver().insert(CONTENT_URI,values);
        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }



}
