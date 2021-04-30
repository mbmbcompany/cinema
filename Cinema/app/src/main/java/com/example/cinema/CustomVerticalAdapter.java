package com.example.cinema;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomVerticalAdapter extends CursorAdapter {
    public CustomVerticalAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_vertical,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView movieId = view.findViewById(R.id.c_v_textView1);
        TextView movieName = view.findViewById(R.id.c_v_textView2);
        TextView mainActor = view.findViewById(R.id.c_v_textView3);
        TextView director = view.findViewById(R.id.c_v_textView4);
        TextView description = view.findViewById(R.id.c_v_textView5);
        TextView length = view.findViewById(R.id.c_v_textView6);


        String movie_id = cursor.getString(cursor.getColumnIndex("_id"));
        String movie_name = "Movie name: "+ cursor.getString(cursor.getColumnIndex("movie_name"));
        String main_actor = "Main actor: "+ cursor.getString(cursor.getColumnIndex("main_actor"));
        String dire_ctor = "Director: "+ cursor.getString(cursor.getColumnIndex("director"));
        String descri_ption = "Description: "+ cursor.getString(cursor.getColumnIndex("description"));
        String len_gth = "Length: "+ cursor.getString(cursor.getColumnIndex("length"))+"h";

//        String movie_id = cursor.getString(cursor.getColumnIndex("movie_id"));
//        String movie_name = cursor.getString(cursor.getColumnIndex("hall_id"));
       // String main_actor = cursor.getString(cursor.getColumnIndex("MovieName"));

       // movieId.setText(movie_id);
        movieName.setText(movie_name);
        mainActor.setText(main_actor);
        director.setText(dire_ctor);
        description.setText(descri_ption);
        length.setText(len_gth);
    }
}
