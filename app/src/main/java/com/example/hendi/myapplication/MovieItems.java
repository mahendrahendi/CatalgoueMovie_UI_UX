package com.example.hendi.myapplication;

/**
 * Created by Hendi on 5/1/2018.
 */

import org.json.JSONObject;

/**
 * Created by Hendi on 4/25/2018.
 */

public class MovieItems {
    private int id;
    private String title;
    private String poster;
    private String overview;
    private String date;
    private String rate_count;
    private String rate;

    public MovieItems (JSONObject object){
        try {
            //int id = object.getString()
            String title = object.getString("original_title");
            String overview = object.getString("overview");
            String date = object.getString("release_date");
            String poster = object.getString("poster_path");
            String rate_count = object.getString("vote_count");
            String rate = object.getString("vote_average");

            this.title = title;
            this.overview = overview;
            this.poster = poster;
            this.date = date;
            this.rate_count = rate_count;
            this.rate = rate;
        }catch (Exception e){
            e.printStackTrace();
        }
    }








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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRate_count() {
        return rate_count;
    }

    public void setRate_count(String rate_count) {
        this.rate_count = rate_count;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
