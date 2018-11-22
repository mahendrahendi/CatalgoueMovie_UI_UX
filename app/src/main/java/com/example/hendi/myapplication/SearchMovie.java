package com.example.hendi.myapplication;

/**
 * Created by Hendi on 5/1/2018.
 */

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchMovie extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    ListView listView;
    MovieAdapter adapter;
    EditText editMovie;
    Button buttonCari;
    ProgressBar progressBar;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie);
        setTitle(getResources().getString(R.string.search));
        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        listView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);


        editMovie = (EditText) findViewById(R.id.edit_movie);
        buttonCari = (Button) findViewById(R.id.btn_movie);

        buttonCari.setOnClickListener(myListener);
        listView.setOnItemClickListener(listB);

        String movie = editMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, movie);

        getLoaderManager().initLoader(0, bundle, this);


    }

    //Fungsi ini yang akan menjalankan proses myasynctaskloader
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {

        String kumpulanKota = "";
        if (args != null) {
            kumpulanKota = args.getString(EXTRAS_MOVIE);
        }

        return new MyAsyncTaskLoader(this, kumpulanKota);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {

        adapter.setData(data);
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);

    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String kota = editMovie.getText().toString();

            if (TextUtils.isEmpty(kota)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, kota);
            getLoaderManager().restartLoader(0, bundle,SearchMovie.this);

            progressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
    };


    ListView.OnItemClickListener listB = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MovieItems items =(MovieItems)parent.getItemAtPosition(position);


            Intent intent = new Intent(SearchMovie.this, DetailMovie.class);

            intent.putExtra(DetailMovie.EXTRA_TITLE, items.getTitle());
            intent.putExtra(DetailMovie.EXTRA_POSTER_JPG, items.getPoster());
            intent.putExtra(DetailMovie.EXTRA_OVERVIEW, items.getOverview());
            intent.putExtra(DetailMovie.EXTRA_RATE, items.getRate());
            intent.putExtra(DetailMovie.EXTRA_RATE_COUNT, items.getRate_count());
            intent.putExtra(DetailMovie.EXTRA_RELEASE_DATE, items.getDate());
            startActivity(intent);


        }
    };

}
