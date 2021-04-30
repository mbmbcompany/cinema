package com.example.cinema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CinemaRoster extends AppCompatActivity {

    volatile boolean color=false;

    ArrayAdapter<String> myAdapter;
    DBHelper dbHelper;
    List<String> personalFilmList = new ArrayList<>();
    CustomCursorAdapter customCursorAdapter;
    ListView myList;
    ArrayList<Integer> indexes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_roster);
        NacitajData();
    }

    private void NacitajData() {
        dbHelper = new DBHelper(this);
        myList = (ListView) findViewById(R.id.cinema_roster_list);
        attachCustomCursor();

         myList.setOnItemClickListener((parent, view, position, id) -> {


             System.out.println("ID je  "+id);
             System.out.println("Pozicia je  "+position);



                Intent intent = new Intent(CinemaRoster.this, MovieInsight.class);

                Long movieId = dbHelper.getMovieIdFromMovieHall(position);

                if (movieId!=null) {
                    System.out.println("AAAAAAAAA  "+movieId);
                    intent.putExtra("movieId", movieId);
                    intent.putExtra("position", position);
                    System.out.println(id);
                    System.out.println("position " + position);
                    startActivityForResult(intent, 2);
                }else{System.out.println("NEJDEEEE");}
//            if (color){
//            view.setBackgroundColor(getResources().getColor(R.color.purple_200,getTheme()));
//
//            }
//            else {
//                view.setBackgroundColor(getResources().getColor(R.color.white,getTheme()));
//
//            }
           // color=!color;
            //view.setBackgroundResource(R.color.black);

            //pridaj do listu tie ktore su oznacene,ked niekto odznaci tak vymazem z listu, farbu manazujeme cez boolean premennu, prvy click true+pridaj farbu druhy click false doober farbu

        });



        Button confirmPicks = findViewById(R.id.confirmPicksButton);
        confirmPicks.setOnClickListener((l) -> {
            Intent intent = new Intent();
            //intent.putExtra("picks",)
            //intent.putExtra("indexes", (Parcelable) indexes);
            intent.putIntegerArrayListExtra("indexes",indexes);
            setResult(RESULT_OK,intent);
            finish();
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.admin:
                Toast.makeText(getBaseContext(),"For admin access identifications contact developer.",Toast.LENGTH_LONG).show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2:
                //ulozit nejake id, resp kombinaciu movie hall a date a poslat do main activitz ako result
                if (resultCode==RESULT_OK){
//                    String ziak = data.getStringExtra("ziak");
//                    String znamky = data.getStringExtra("znamky");
//                    this.znamkyArray.add(znamky);
//                    myAdapter.add(ziak);
                   // view.setBackgroundColor(getResources().getColor(R.color.purple_200,getTheme()));
                    int position = data.getIntExtra("index",0);
                    Integer iPosition = Integer.valueOf(position);
                    if (!indexes.contains(iPosition)) {
                        indexes.add(iPosition);
                    }
                    //myList.getSelectedView().setBackgroundColor(getResources().getColor(R.color.purple_200,getTheme()));

                    //myList.getItemAtPosition(position);
                    color=true;
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

    public void attachCustomCursor(){
        customCursorAdapter = new CustomCursorAdapter(this, dbHelper.getMovieHall_InnerJoin_Movie_Hall_Cursor(),0);
        myList.setAdapter(customCursorAdapter);
    }
}