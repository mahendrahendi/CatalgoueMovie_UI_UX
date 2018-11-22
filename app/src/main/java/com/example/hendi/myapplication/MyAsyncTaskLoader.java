package com.example.hendi.myapplication;

/**
 * Created by Hendi on 5/1/2018.
 */

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Hendi on 4/25/2018.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {
    private ArrayList<MovieItems> mData;
    private boolean mHasResult = false;

    private String mKumpulanKota;

    public MyAsyncTaskLoader(final Context context, String kumpulanKota) {
        super(context);

        onContentChanged();
        this.mKumpulanKota = kumpulanKota;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<MovieItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = "2693145f0d898527f366ad0b62c2eddd";

    // https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher

    @Override
    public ArrayList<MovieItems> loadInBackground() {
        String url="";
        SyncHttpClient client = new SyncHttpClient();

        if(mKumpulanKota.equalsIgnoreCase("upcoming")){
            url="https://api.themoviedb.org/3/movie/upcoming?api_key=" + API_KEY + "&language=en";
        }else if(mKumpulanKota.equalsIgnoreCase("now_playing")){
            url="https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY + "&language=en";
        }else {
            url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=" + mKumpulanKota;
        }
        final ArrayList<MovieItems> MovieItemses = new ArrayList<>();
       // String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=" + mKumpulanKota;
         //String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + API_KEY + "&language=en";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        MovieItems MovieItems = new MovieItems(weather);
                        MovieItemses.add(MovieItems);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //Jika response gagal maka , do nothing
            }
        });

        return MovieItemses;
    }

    protected void onReleaseResources(ArrayList<MovieItems> data) {
        //nothing to do.
    }

}