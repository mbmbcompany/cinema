package com.example.cinema;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
public class CustomCursorAdapter extends CursorAdapter {

    public CustomCursorAdapter(Context context, Cursor c, int flags) {
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
        TextView tMovieId = view.findViewById(R.id.c_l_textView3);
        TextView tHall = view.findViewById(R.id.c_l_textView4);
       // TextView description = view.findViewById(R.id.c_l_textView5);
        TextView tDate = view.findViewById(R.id.c_l_textView6);


//        String movie_id = cursor.getString(cursor.getColumnIndex("_id"));
//        String movie_name = cursor.getString(cursor.getColumnIndex("movie_name"));
//        String main_actor = cursor.getString(cursor.getColumnIndex("main_actor"));
//        String dire_ctor = cursor.getString(cursor.getColumnIndex("director"));
//        String descri_ption = cursor.getString(cursor.getColumnIndex("description"));
//        String len_gth = cursor.getString(cursor.getColumnIndex("length"));

       // if (cursor.getPosition()==0){
           // cursor.moveToPosition(i);
            String id = cursor.getString(cursor.getColumnIndex("ID"));
            String hall = "Hall: "+cursor.getString(cursor.getColumnIndex("Hall"));
            String movie_name = cursor.getString(cursor.getColumnIndex("MovieName"));
            String date = cursor.getString(cursor.getColumnIndex("Date"));
            String movieId = cursor.getString(cursor.getColumnIndex("MV_ID"));


            tId.setText(id);
            tMovie.setText(movie_name);
            tHall.setText(hall);
            tDate.setText(date);
            tMovieId.setText(movieId);
            tMovieId.setVisibility(View.INVISIBLE);


//        String hall = cursor.getString(cursor.getColumnIndex("Hall"));
//        String movie_name = cursor.getString(cursor.getColumnIndex("MovieName"));
//        String date = cursor.getString(cursor.getColumnIndex("Date"));


       // if (movie_name.equals("SHREK")){
//            tHall.setVisibility(View.GONE);
//            tMovie.setVisibility(View.GONE);
//            tDate.setVisibility(View.GONE);
           // cursor.moveToNext();
//            tMovie.setText(movie_name);
//            tHall.setText(hall);
//            tDate.setText(date);


    //    }else{

//        tMovie.setText(movie_name);
//        tHall.setText(hall);
//        tDate.setText(date);
       // }
//        director.setText(dire_ctor);
//        description.setText(descri_ption);
//        length.setText(len_gth);


    }
}
