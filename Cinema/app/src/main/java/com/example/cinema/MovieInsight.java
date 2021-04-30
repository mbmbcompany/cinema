package com.example.cinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
public class MovieInsight extends AppCompatActivity {

    CustomVerticalAdapter customVerticalAdapter;
    DBHelper dbHelper;
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_insight);
        NacitajDate();

    }

    private void NacitajDate(){
        dbHelper  = new DBHelper(this);
        myList = findViewById(R.id.movieInsightListView);
        Intent intent = getIntent();
        long movieId = intent.getLongExtra("movieId",0);
        int position = intent.getIntExtra("position",0);

        attachCustomCursor(movieId);

        Button confirm = findViewById(R.id.buttonAdd);
        confirm.setOnClickListener((l) -> {
           // Intent intent = new Intent();
            intent.putExtra("answer",true);
            intent.putExtra("index",position+1);
            setResult(RESULT_OK,intent);
            finish();
        });

        Button decline = findViewById(R.id.buttonSkip);
        decline.setOnClickListener((l) -> {
            //Intent intent = new Intent();
            intent.putExtra("answer",false);

            setResult(RESULT_CANCELED,intent);
            finish();
        });
    }

    public void attachCustomCursor(long movieId){
        customVerticalAdapter = new CustomVerticalAdapter(this, dbHelper.getMovieByIdCursor(movieId),0);
        myList.setAdapter(customVerticalAdapter);
    }
}