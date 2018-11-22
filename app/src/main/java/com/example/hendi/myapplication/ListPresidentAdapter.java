package com.example.hendi.myapplication;

/**
 * Created by Hendi on 5/1/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Hendi on 4/30/2018.
 */

public class ListPresidentAdapter extends RecyclerView.Adapter<ListPresidentAdapter.CategoryViewHolder> {
    private Context context;

    ArrayList<MovieItems> getListPresident() {
        return listPresident;
    }

    void setListPresident(ArrayList<MovieItems> listPresident) {
        this.listPresident = listPresident;
        notifyDataSetChanged();
    }


    private ArrayList<MovieItems> listPresident;

    ListPresidentAdapter(Context context) {
        this.context = context;
    }


    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType)

    {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_president, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.tvName.setText(getListPresident().get(position).getTitle());
        holder.tvRemarks.setText(getListPresident().get(position).getOverview());

        //Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+mData.get(position).getPoster()).into(holder.imgPoster);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185/"+getListPresident().get(position).getPoster())
                .override(80, 70)
                .crossFade()
                .into(holder.imgPhoto);
    }

    public int getItemCount() {
        return getListPresident().size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvRemarks;
        ImageView imgPhoto;

        CategoryViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_name);
            tvRemarks = (TextView) itemView.findViewById(R.id.tv_item_remarks);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_item_photo);
        }
    }
}