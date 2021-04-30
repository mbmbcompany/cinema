package com.example.cinema;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomUserAdapter extends CursorAdapter {

    public CustomUserAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tId = view.findViewById(R.id.c_l_textView1);
        TextView tMovie = view.findViewById(R.id.c_l_textView2);
        //TextView mainActor = view.findViewById(R.id.c_l_textView3);
        TextView tHall = view.findViewById(R.id.c_l_textView4);
        // TextView description = view.findViewById(R.id.c_l_textView5);
        TextView tDate = view.findViewById(R.id.c_l_textView6);

        String id = cursor.getString(cursor.getColumnIndex("ID"));
        String hall = "Hall: "+cursor.getString(cursor.getColumnIndex("Hall"));
        String movie_name = cursor.getString(cursor.getColumnIndex("MovieName"));
        String date = cursor.getString(cursor.getColumnIndex("Date"));


        tId.setText(id);
        tMovie.setText(movie_name);
        tHall.setText(hall);
        tDate.setText(date);
    }
}
