package com.example.cinema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> myAdapter;
    CustomUserAdapter customCursorAdapter;
    SimpleCursorAdapter simpleCursorAdapter;
    DBHelper dbHelper = new DBHelper(this);
    String manualText = "Hello user:). Into this main screen you can add films you want to see in cinema. Regardless movie name you will see  place and time where an when they are transmitted."+
           "For adding films from cinema scheduled films into your personal list click on the button Pick films and via button Add films add them to your screen." ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        try {
            NacitajData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void NacitajData() throws ParseException {

        Movie movie = new Movie("TOP LUN","Stevens",2,"Romantic comedy","Philip Gruentol Jr.");
        Movie movie1 = new Movie("Bees are going to live","Michael Tarhon",1,"Documentary","Nature");
        Movie movie2 = new Movie("NOT HOME NOT ALONE","Benny Pochanke",2,"Horror","Sam Durmtell");
        dbHelper.addMovie(movie);
        dbHelper.addMovie(movie1);
        dbHelper.addMovie(movie2);
        Hall hall  = new Hall("Nitra",'A');
        Hall hall1  = new Hall("Nitra",'B');
        Hall hall2  = new Hall("Nitra",'C');
        Hall hall3 = new Hall("Nitra",'D');
        dbHelper.addHall(hall);
        dbHelper.addHall(hall1);
        dbHelper.addHall(hall2);
        dbHelper.addHall(hall3);
        dbHelper.schedule(1,1, Timestamp.valueOf("2018-09-01 09:01:15"));
        dbHelper.schedule(2,2, Timestamp.valueOf("2019-09-05 10:21:10"));
        dbHelper.schedule(3,3, Timestamp.valueOf("2020-01-10 17:42:05"));
        dbHelper.schedule(1,3, Timestamp.valueOf("2020-10-01 09:16:15"));
        dbHelper.schedule(2,3, Timestamp.valueOf("2021-11-12 18:21:14"));
        dbHelper.schedule(3,1, Timestamp.valueOf("2022-12-04 15:42:02"));

        attachCustomCursor();
        Button pickButton = findViewById(R.id.pickFilmButton);
        pickButton.setOnClickListener((l) -> {
            Intent intent = new Intent(MainActivity.this, CinemaRoster.class);
            startActivityForResult(intent,1);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.manualId:
                System.out.println("TOAST SE SUNKU");
                Toast.makeText(this,manualText,Toast.LENGTH_LONG).show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void attachSimpleAdapter(){
        simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.custom_layout,dbHelper.getMovieCursor(),
                new String[]{CinemaTables.Movie.COLUMN_MOVIE_ID,CinemaTables.Movie.COLUMN_MOVIE_NAME,CinemaTables.Movie.COLUMN_MAIN_ACTOR,CinemaTables.Movie.COLUMN_DIRECTOR,CinemaTables.Movie.COLUMN_DESCRIPTION,CinemaTables.Movie.COLUMN_LENGTH},
                new int[]{R.id.c_l_textView1,R.id.c_l_textView2,R.id.c_l_textView3,R.id.c_l_textView4,R.id.c_l_textView5,R.id.c_l_textView6},
                0);
        lv.setAdapter(simpleCursorAdapter);

    }
    public void attachCustomCursor(){
        customCursorAdapter = new CustomUserAdapter(this, dbHelper.getUserSchedulesList_INNERJOIN_Movie_Hall(),0);
        lv.setAdapter(customCursorAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                //ulozit nejake id, resp kombinaciu movie hall a date a poslat do main activitz ako result
                if (resultCode==RESULT_OK){
//                    String ziak = data.getStringExtra("ziak");
//                    String znamky = data.getStringExtra("znamky");
//                    this.znamkyArray.add(znamky);
//                    myAdapter.add(ziak);
                    // view.setBackgroundColor(getResources().getColor(R.color.purple_200,getTheme()));
                    ArrayList<Integer> indexes = data.getIntegerArrayListExtra("indexes");
                    dbHelper.getFromSchedulesSetToUserSchedules(indexes);
                    attachCustomCursor();
                    //indexes.stream().forEach(System.out::println);


                    //myList.getSelectedView().setBackgroundColor(getResources().getColor(R.color.purple_200,getTheme()));

                    //myList.getItemAtPosition(position);

                    break;
                }
                if (resultCode==RESULT_CANCELED){
                /*    String ziak = data.getStringExtra("ziak");
                    String znamky = data.getStringExtra("znamky");
                    this.znamkyArray.add(znamky);
                    myAdapter.add(ziak);*/
                    break;
                }
//            case 2:
//                if (resultCode==RESULT_OK){
//                    String changedZnamky = data.getStringExtra("changedZnamky");
//                    int position = data.getIntExtra("position",0);
//                    dbHelper.updateStudentMarks(Long.valueOf(position),changedZnamky);
//                    break;
//                }
        }
    }
}