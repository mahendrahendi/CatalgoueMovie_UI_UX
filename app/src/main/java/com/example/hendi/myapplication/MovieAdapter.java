package com.example.hendi.myapplication;

/**
 * Created by Hendi on 5/1/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Hendi on 4/25/2018.
 */

public class MovieAdapter extends BaseAdapter {
    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }
    public void addItem(final MovieItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }
    public void clearData(){
        mData.clear();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }
    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_row_text, null);
            holder.textViewTitle= (TextView)convertView.findViewById(R.id.textMovie);
            holder.textViewOverview = (TextView)convertView.findViewById(R.id.textOverview);
            holder.textviewDate= (TextView)convertView.findViewById(R.id.textDate);
            holder.imgPoster = (ImageView)convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewTitle.setText(mData.get(position).getTitle());
        holder.textViewOverview.setText(mData.get(position).getOverview());
        holder.textviewDate.setText(mData.get(position).getDate());
        Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+mData.get(position).getPoster()).into(holder.imgPoster);
        return convertView;

        //glide terbaru harurs menggunakan API 27 ?
    }
    private static class ViewHolder {
        TextView textViewTitle;
        TextView textViewOverview;
        TextView textviewDate;
        ImageView imgPoster;
    }
}