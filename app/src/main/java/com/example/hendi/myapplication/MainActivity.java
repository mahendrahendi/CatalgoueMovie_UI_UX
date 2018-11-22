package com.example.hendi.myapplication;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>   {
    RecyclerView rvCategory;
    TextView depan;
    ProgressBar progressBar;
    private ArrayList<MovieItems> list;
    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        depan = (TextView)findViewById(R.id.depan);
        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);
        rvCategory.setVisibility(View.INVISIBLE);

        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        depan.setVisibility(View.VISIBLE);

        //list = new ArrayList<>();
       // list.addAll(PresidentData.getListData());


        String movie = "n";

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, movie);

       getLoaderManager().initLoader(0, bundle, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Menu");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.setting) {
            depan.setVisibility(View.VISIBLE);
            rvCategory.setVisibility(View.INVISIBLE);
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);


        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.now_play) {

            setTitle(getResources().getString(R.string.now_playing));
            rvCategory.setVisibility(View.INVISIBLE);
            kataKunci("now_playing");

        } else if (id == R.id.upcoming) {

            rvCategory.setVisibility(View.INVISIBLE);
            setTitle(getResources().getString(R.string.upcoming));
            kataKunci("upcoming");

        } else if (id == R.id.search) {
            Intent intent = new Intent(MainActivity.this, SearchMovie.class);
            startActivity(intent);
        }else if (id == R.id.favorite){
            Intent intent = new Intent(MainActivity.this, Favorite.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;

    }
    private void kataKunci(String kota){
          depan.setVisibility(View.INVISIBLE);
          progressBar.setVisibility(View.VISIBLE);
          rvCategory.setVisibility(View.VISIBLE);
          Bundle bundle = new Bundle();
          bundle.putString(EXTRAS_MOVIE, kota);
          getLoaderManager().restartLoader(0, bundle,MainActivity.this);
}

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

        progressBar.setVisibility(View.INVISIBLE);
       showRecyclerList(data);

       //rvCategory.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
       // rvCategory.setLayoutManager(new LinearLayoutManager(this));
       // ListPresidentAdapter listPresidentAdapter = new ListPresidentAdapter(this);
       // listPresidentAdapter.setListPresident(null);
        //rvCategory.setAdapter(null);
        ListPresidentAdapter listPresidentAdapter = new ListPresidentAdapter(this);
        listPresidentAdapter.setListPresident(null);
    }
    private void showRecyclerList(final ArrayList<MovieItems> data) {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListPresidentAdapter listPresidentAdapter = new ListPresidentAdapter(this);
        listPresidentAdapter.setListPresident(data);
        rvCategory.setAdapter(listPresidentAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
              Intent intent = new Intent(MainActivity.this, DetailMovie.class);
              MovieItems items = data.get(position);
                intent.putExtra(DetailMovie.EXTRA_TITLE, items.getTitle());
                intent.putExtra(DetailMovie.EXTRA_POSTER_JPG, items.getPoster());
                intent.putExtra(DetailMovie.EXTRA_OVERVIEW, items.getOverview());
                intent.putExtra(DetailMovie.EXTRA_RATE, items.getRate());
                intent.putExtra(DetailMovie.EXTRA_RATE_COUNT, items.getRate_count());
                intent.putExtra(DetailMovie.EXTRA_RELEASE_DATE, items.getDate());
                startActivity(intent);
            }
        });

    }

}
