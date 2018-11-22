package com.example.hendi.myapplication.adapter;

/**
 * Created by Hendi on 5/11/2018.
 */

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.hendi.myapplication.CustomOnItemClickListener;
import com.example.hendi.myapplication.DetailMovie;
import com.example.hendi.myapplication.R;
import com.example.hendi.myapplication.entity.Note;

import java.util.LinkedList;
import static com.example.hendi.myapplication.db.DatabaseContract.CONTENT_URI;

/**
 * Created by sidiqpermana on 11/23/16.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewholder>{
    private Cursor listNotes;
    private Activity activity;
    Context context;
    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListNotes(Cursor listNotes) {
        this.listNotes = listNotes;
    }

    @Override
    public NoteViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewholder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewholder holder, int position) {
        final Note note = getItem(position);
        holder.tvTitle.setText(note.getTitle());
      //holder.tvDate.setText(note.geDate());
        holder.tvDescription.setText(note.getDescription());
        Glide.with(activity)
                .load("http://image.tmdb.org/t/p/w185/"+note.getPoster())
                .override(80, 70)
                .crossFade()
                .into(holder.tvPoster);

      /*  holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                Uri uri = Uri.parse(CONTENT_URI+"/"+note.getId());
                intent.setData(uri);
                activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);
            }
        }));  */
    }

    @Override
    public int getItemCount() {
        if (listNotes == null) return 0;
        return listNotes.getCount();
    }
    private Note getItem(int position){
        if (!listNotes.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Note(listNotes);
    }

    class NoteViewholder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDescription, tvDate;
        CardView cvNote;
        ImageView tvPoster;

        NoteViewholder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_item_title);
            tvDescription = (TextView)itemView.findViewById(R.id.tv_item_description);
           // tvDate = (TextView)itemView.findViewById(R.id.tv_item_date);
            cvNote = (CardView)itemView.findViewById(R.id.cv_item_note);
            tvPoster = (ImageView)itemView.findViewById(R.id.img_item_photo2);
        }
    }
}