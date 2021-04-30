package com.example.cinema;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cinema";
    private static final int DATABASE_VERSION = 20;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + CinemaTables.Movie.TABLE_NAME  + "("
                + CinemaTables.Movie.COLUMN_MOVIE_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CinemaTables.Movie.COLUMN_MOVIE_NAME + " TEXT, "
                + CinemaTables.Movie.COLUMN_DESCRIPTION + " TEXT, "
                + CinemaTables.Movie.COLUMN_DIRECTOR + " TEXT, "
                + CinemaTables.Movie.COLUMN_MAIN_ACTOR + " TEXT, "
                + CinemaTables.Movie.COLUMN_LENGTH + " INTEGER ) ";

        Log.d("sql",sql);
        db.execSQL(sql);
        Log.d("sql","done");

        String sql1 = "CREATE TABLE " + CinemaTables.Hall.TABLE_NAME  + "("
                + CinemaTables.Hall.COLUMN_HALL_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CinemaTables.Hall.COLUMN_ADDRESS + " TEXT, "
                + CinemaTables.Hall.COLUMN_DESCRIPTOR + " TEXT ) ";

        Log.d("sql",sql1);
        db.execSQL(sql1);
        Log.d("sql","done");

//        String sql2 = "CREATE TABLE " + CinemaTables.Movie_Hall.TABLE_NAME  + "("
//                + CinemaTables.Movie_Hall.COLUMN_HALL_ID  + " INTEGER NOT NULL,"
//                + CinemaTables.Movie_Hall.COLUMN_MOVIE_ID + " INTEGER NOT NULL, "
//                + CinemaTables.Movie_Hall.DATE_TIME + " TEXT, "
//                + " FOREIGN KEY("+CinemaTables.Movie_Hall.COLUMN_HALL_ID+")REFERENCES "+CinemaTables.Hall.TABLE_NAME+"("+CinemaTables.Hall.COLUMN_HALL_ID+") ON DELETE NO ACTION ON UPDATE NO ACTION,"
//                + " FOREIGN KEY("+CinemaTables.Movie_Hall.COLUMN_MOVIE_ID+")REFERENCES "+CinemaTables.Movie.TABLE_NAME+"("+CinemaTables.Movie.COLUMN_MOVIE_ID+") ON DELETE NO ACTION ON UPDATE NO ACTION,"
//                + "CONSTRAINT pk_movie_track PRIMARY KEY("+CinemaTables.Movie_Hall.COLUMN_HALL_ID+","+CinemaTables.Movie_Hall.COLUMN_MOVIE_ID+","+CinemaTables.Movie_Hall.DATE_TIME+") ) ";
        String sql2 = "CREATE TABLE " + CinemaTables.Movie_Hall.TABLE_NAME  + "("
                + CinemaTables.Movie_Hall.COLUMN_MOVIE_HALL_ID  + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + CinemaTables.Movie_Hall.COLUMN_HALL_ID  + " INTEGER NOT NULL,"
                + CinemaTables.Movie_Hall.COLUMN_MOVIE_ID + " INTEGER NOT NULL, "
                + CinemaTables.Movie_Hall.DATE_TIME + " TEXT, "
                + " FOREIGN KEY("+CinemaTables.Movie_Hall.COLUMN_HALL_ID+")REFERENCES "+CinemaTables.Hall.TABLE_NAME+"("+CinemaTables.Hall.COLUMN_HALL_ID+") ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " FOREIGN KEY("+CinemaTables.Movie_Hall.COLUMN_MOVIE_ID+")REFERENCES "+CinemaTables.Movie.TABLE_NAME+"("+CinemaTables.Movie.COLUMN_MOVIE_ID+") ON DELETE NO ACTION ON UPDATE NO ACTION)";

        Log.d("sql",sql2);
        db.execSQL(sql2);
        Log.d("sql","done");

        String sql3 = "CREATE TABLE " + CinemaTables.USER_SCHEDULES_LIST.TABLE_NAME  + "("
                + CinemaTables.USER_SCHEDULES_LIST.COLUMN_MOVIE_HALL_ID  + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + CinemaTables.USER_SCHEDULES_LIST.COLUMN_HALL_ID  + " INTEGER NOT NULL,"
                + CinemaTables.USER_SCHEDULES_LIST.COLUMN_MOVIE_ID + " INTEGER NOT NULL, "
                + CinemaTables.USER_SCHEDULES_LIST.DATE_TIME + " TEXT, "
                + " FOREIGN KEY("+CinemaTables.USER_SCHEDULES_LIST.COLUMN_HALL_ID+")REFERENCES "+CinemaTables.Hall.TABLE_NAME+"("+CinemaTables.Hall.COLUMN_HALL_ID+") ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " FOREIGN KEY("+CinemaTables.USER_SCHEDULES_LIST.COLUMN_MOVIE_ID+")REFERENCES "+CinemaTables.Movie.TABLE_NAME+"("+CinemaTables.Movie.COLUMN_MOVIE_ID+") ON DELETE NO ACTION ON UPDATE NO ACTION)";

        Log.d("sql",sql3);
        db.execSQL(sql3);
        Log.d("sql","done");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CinemaTables.Movie_Hall.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CinemaTables.Hall.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CinemaTables.Movie.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CinemaTables.USER_SCHEDULES_LIST.TABLE_NAME);
        onCreate(db);
    }

    public void addMovie(Movie m) {
        ContentValues data = new ContentValues();
        data.put(CinemaTables.Movie.COLUMN_MOVIE_NAME, m.getMovieName());
        data.put(CinemaTables.Movie.COLUMN_DESCRIPTION, m.getDescription());
        data.put(CinemaTables.Movie.COLUMN_DIRECTOR, m.getDirector());
        data.put(CinemaTables.Movie.COLUMN_LENGTH, m.getLength());
        data.put(CinemaTables.Movie.COLUMN_MAIN_ACTOR, m.getMainActor());

        SQLiteDatabase db = getWritableDatabase();
        long ID = db.insert(
                CinemaTables.Movie.TABLE_NAME,
                null,
                data);
        db.close();
    }

    public void addHall(Hall h) {
        ContentValues data = new ContentValues();
        data.put(CinemaTables.Hall.COLUMN_ADDRESS, h.getAddress());
        data.put(CinemaTables.Hall.COLUMN_DESCRIPTOR, String.valueOf(h.getDescriptor()));


        SQLiteDatabase db = getWritableDatabase();
        long ID = db.insert(
                CinemaTables.Hall.TABLE_NAME,
                null,
                data);
        db.close();
    }

    public void schedule(long movieId, long hallId, Timestamp timestamp){
       // System.out.println(timestamp);
        System.out.println(timestamp.toString());

       // String [] columns={CinemaTables.Movie_Hall.COLUMN_MOVIE_ID,CinemaTables.Movie_Hall.COLUMN_HALL_ID};
        ArrayList<String> movies = new ArrayList<>();
        ArrayList<String> halls = new ArrayList<>();
        int movieFromDbId = 0;
        int hallFromDbId = 0;
        try(SQLiteDatabase db = getReadableDatabase()) {
           // Cursor c = db.rawQuery("SELECT * FROM "+CinemaTables.Movie_Hall.TABLE_NAME+" WHERE "+CinemaTables.Movie_Hall.COLUMN_MOVIE_ID+"="+"",null);
            //db.query(ZiackaTables.Student.TABLE_NAME,columns,null,null,null,null,null);
            Cursor c =  db.rawQuery("SELECT * FROM "+CinemaTables.Movie.TABLE_NAME+" WHERE "+CinemaTables.Movie.COLUMN_MOVIE_ID+"='"+movieId+"'",null);
          //  +"' AND "+CinemaTables.Hall.COLUMN_HALL_ID+"='"+hallId+"'"
            if(c.moveToFirst()){
                do{ movies.add(c.getString(c.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_ID))+"  "+c.getString(c.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_NAME)));
                movieFromDbId = Integer.parseInt(c.getString(c.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_ID)));
                }while (c.moveToNext());
            }

            Cursor cu =  db.rawQuery("SELECT * FROM "+CinemaTables.Hall.TABLE_NAME+" WHERE "+CinemaTables.Hall.COLUMN_HALL_ID+"='"+hallId+"'",null);
            //  +"' AND "+CinemaTables.Hall.COLUMN_HALL_ID+"='"+hallId+"'"
            if(cu.moveToFirst()){
                do{ halls.add(cu.getString(cu.getColumnIndex(CinemaTables.Hall.COLUMN_HALL_ID))+"  "+cu.getString(cu.getColumnIndex(CinemaTables.Hall.COLUMN_DESCRIPTOR)));
                    hallFromDbId = Integer.parseInt(cu.getString(cu.getColumnIndex(CinemaTables.Hall.COLUMN_HALL_ID)));
                }while (cu.moveToNext());
            }
            //return names;
           // movies.stream().forEach(System.out::println);
           // halls.stream().forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }

        //try(SQLiteDatabase db = getWritableDatabase()) {

            //Cursor c = db.rawQuery("SELECT * FROM "+CinemaTables.Hall.TABLE_NAME+" where "+CinemaTables.Hall.COLUMN_HALL_ID+"="+hallId,null);
            //Cursor d = db.rawQuery("SELECT * FROM "+CinemaTables.Movie.TABLE_NAME+" where "+CinemaTables.Movie.COLUMN_MOVIE_ID+"="+movieId,null);
            //db.query(ZiackaTables.Student.TABLE_NAME,columns,null,null,null,null,null);
           // ("select * from " + BanksTable.NAME + " where " + BanksTable.COL_ID + "=" + bankId  , null);



           // if(c.moveToFirst()){

               // && d.moveToFirst()

                if(movieFromDbId!=0 && hallFromDbId !=0) {
                    ContentValues data = new ContentValues();
                    data.put(CinemaTables.Movie_Hall.COLUMN_HALL_ID, movieFromDbId);
                    data.put(CinemaTables.Movie_Hall.COLUMN_MOVIE_ID, hallFromDbId);
                    data.put(CinemaTables.Movie_Hall.DATE_TIME, timestamp.toString());

                    // c.getColumnIndex(CinemaTables.Hall.COLUMN_HALL_ID)

                    //d.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_ID)

                    SQLiteDatabase db = getWritableDatabase();
                    long id = db.insert(
                            CinemaTables.Movie_Hall.TABLE_NAME,
                            null,
                            data);
                    db.close();
                    System.out.println("ID je "+id);
                }
           //}

        //}
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public ArrayList<String> getAllSchedules(){

        ArrayList<String> schedules = new ArrayList<>();
        int movieId=0;
        int hallId=0;
        String date="";
        String movieName="";
        String hallDescriptor="";

        try(SQLiteDatabase db = getReadableDatabase()) {
            Cursor cursor = db.rawQuery("SELECT * FROM "+CinemaTables.Movie_Hall.TABLE_NAME,null);
            //db.query(ZiackaTables.Student.TABLE_NAME,columns,null,null,null,null,null);
            if(cursor.moveToFirst()){
                do{
                    movieId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CinemaTables.Movie_Hall.COLUMN_MOVIE_ID)));
                    hallId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CinemaTables.Movie_Hall.COLUMN_HALL_ID)));
                    date = cursor.getString(cursor.getColumnIndex(CinemaTables.Movie_Hall.DATE_TIME));
                    //names.add(c.getString(c.getColumnIndex(CinemaTables.Movie_Hall.COLUMN_MOVIE_ID))+"  "+c.getString(c.getColumnIndex(CinemaTables.Movie_Hall.COLUMN_HALL_ID))+" "+c.getString(c.getColumnIndex(CinemaTables.Movie_Hall.DATE_TIME)));

                    Cursor c =  db.rawQuery("SELECT * FROM "+CinemaTables.Movie.TABLE_NAME+" WHERE "+CinemaTables.Movie.COLUMN_MOVIE_ID+"='"+movieId+"'",null);
                    //  +"' AND "+CinemaTables.Hall.COLUMN_HALL_ID+"='"+hallId+"'"
                    if(c.moveToFirst()){
                        do{
                            //movies.add(c.getString(c.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_ID))+"  "+c.getString(c.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_NAME)));
                            movieName = c.getString(c.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_NAME));
                        }while (c.moveToNext());
                    }

                    Cursor cu =  db.rawQuery("SELECT * FROM "+CinemaTables.Hall.TABLE_NAME+" WHERE "+CinemaTables.Hall.COLUMN_HALL_ID+"='"+hallId+"'",null);
                    //  +"' AND "+CinemaTables.Hall.COLUMN_HALL_ID+"='"+hallId+"'"
                    if(cu.moveToFirst()){
                        do{
                            //halls.add(cu.getString(cu.getColumnIndex(CinemaTables.Hall.COLUMN_HALL_ID))+"  "+cu.getString(cu.getColumnIndex(CinemaTables.Hall.COLUMN_DESCRIPTOR)));
                            hallDescriptor = cu.getString(cu.getColumnIndex(CinemaTables.Hall.COLUMN_DESCRIPTOR));
                        }while (cu.moveToNext());
                    }


                    schedules.add(movieName+" Hall:"+hallDescriptor+"  ("+date+")");


                }while (cursor.moveToNext());
            }
            return schedules;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getAllMovieNames(){
        String [] columns={CinemaTables.Movie.COLUMN_MOVIE_NAME};
        ArrayList<String> names = new ArrayList<>();
        try(SQLiteDatabase db = getReadableDatabase()) {
            Cursor c = db.rawQuery("SELECT * FROM "+CinemaTables.Movie.TABLE_NAME,null);
            //db.query(ZiackaTables.Student.TABLE_NAME,columns,null,null,null,null,null);
            if(c.moveToFirst()){
                do{
                    names.add(c.getString(c.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_NAME)));
                }while (c.moveToNext());
            }
            return names;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getAllHallNames(){

        String [] columns={CinemaTables.Hall.COLUMN_DESCRIPTOR};

        ArrayList<String> names = new ArrayList<>();


        try(SQLiteDatabase db = getReadableDatabase()) {

            Cursor c = db.rawQuery("SELECT * FROM "+CinemaTables.Hall.TABLE_NAME,null);
            //db.query(ZiackaTables.Student.TABLE_NAME,columns,null,null,null,null,null);


            if(c.moveToFirst()){
                do{

                    names.add(c.getString(c.getColumnIndex(CinemaTables.Hall.COLUMN_DESCRIPTOR)));

                }while (c.moveToNext());

            }
            return names;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void getFromSchedulesSetToUserSchedules(ArrayList<Integer> indexes){

        ArrayList<String> schedules = new ArrayList<>();
        int ID=0;
        int movieId=0;
        int hallId=0;
        String date="";
        String movieName="";
        String hallDescriptor="";

        try(SQLiteDatabase db = getWritableDatabase()) {
            Cursor cursor = db.rawQuery("SELECT * FROM "+CinemaTables.Movie_Hall.TABLE_NAME,null);
            //db.query(ZiackaTables.Student.TABLE_NAME,columns,null,null,null,null,null);
            if(cursor.moveToFirst()){
                do{
                    ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CinemaTables.Movie_Hall.COLUMN_MOVIE_HALL_ID)));
                    if (indexes.contains(Integer.valueOf(ID))) {
                        movieId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CinemaTables.Movie_Hall.COLUMN_MOVIE_ID)));
                        hallId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CinemaTables.Movie_Hall.COLUMN_HALL_ID)));
                        date = cursor.getString(cursor.getColumnIndex(CinemaTables.Movie_Hall.DATE_TIME));

                        ContentValues data = new ContentValues();
                        data.put(CinemaTables.USER_SCHEDULES_LIST.COLUMN_HALL_ID, hallId);
                        data.put(CinemaTables.USER_SCHEDULES_LIST.COLUMN_MOVIE_ID, movieId);
                        data.put(CinemaTables.USER_SCHEDULES_LIST.DATE_TIME, date);


                        long id = db.insert(
                                CinemaTables.USER_SCHEDULES_LIST.TABLE_NAME,
                                null,
                                data);

                        System.out.println("NOVEEE ID je " + id);
                    }
                }while (cursor.moveToNext());
                db.close();
            }
            //return schedules;
        }catch (Exception e){
            e.printStackTrace();
        }
        //return null;
    }








    public Cursor getMovieHallCursor(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CinemaTables.Movie_Hall.TABLE_NAME,null);
        cursor.moveToFirst();
        db.close();
        //cursor.close();
        return cursor;
    }

    public Cursor getMovieCursor(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CinemaTables.Movie.TABLE_NAME,null);
        cursor.moveToFirst();
        db.close();
        //cursor.close();
        return cursor;
    }

    public Cursor getMovieByIdCursor(long movieId){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CinemaTables.Movie.TABLE_NAME+" WHERE "+CinemaTables.Movie.COLUMN_MOVIE_ID+"='"+movieId+"'",null);
        cursor.moveToFirst();
        db.close();
        //cursor.close();
        return cursor;
    }

    public Cursor getMovieHall_InnerJoin_Movie_Hall_Cursor(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+CinemaTables.Movie_Hall.TABLE_NAME+"."+CinemaTables.Movie_Hall.COLUMN_MOVIE_HALL_ID+" AS ID,"+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_ID+" AS MV_ID,"+CinemaTables.Hall.TABLE_NAME+"."+CinemaTables.Hall.COLUMN_HALL_ID+","+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_NAME+" AS MovieName, "+CinemaTables.Hall.TABLE_NAME+"."+CinemaTables.Hall.COLUMN_DESCRIPTOR+" AS Hall,"+CinemaTables.Movie_Hall.TABLE_NAME+"."+CinemaTables.Movie_Hall.DATE_TIME+" AS Date FROM "+CinemaTables.Movie_Hall.TABLE_NAME+" INNER JOIN "+CinemaTables.Movie.TABLE_NAME+" ON "+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_ID+" = "+CinemaTables.Movie_Hall.TABLE_NAME+"."+CinemaTables.Movie_Hall.COLUMN_MOVIE_ID+" INNER JOIN "+CinemaTables.Hall.TABLE_NAME+" ON "+CinemaTables.Hall.TABLE_NAME+"."+CinemaTables.Hall.COLUMN_HALL_ID+" = "+CinemaTables.Movie_Hall.TABLE_NAME+"."+CinemaTables.Movie_Hall.COLUMN_HALL_ID,null);
       // Cursor cursor = db.rawQuery("SELECT "+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_ID+" FROM "+CinemaTables.Movie_Hall.TABLE_NAME+" INNER JOIN "+CinemaTables.Movie.TABLE_NAME+" ON "+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_ID+" = "+CinemaTables.Movie_Hall.TABLE_NAME+"."+CinemaTables.Movie_Hall.COLUMN_MOVIE_ID,null);
        cursor.moveToFirst();
        db.close();
        //cursor.close();
        return cursor;
    }

    public Cursor getUserSchedulesList_INNERJOIN_Movie_Hall(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+CinemaTables.USER_SCHEDULES_LIST.TABLE_NAME+"."+CinemaTables.USER_SCHEDULES_LIST.COLUMN_MOVIE_HALL_ID+" AS ID,"+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_ID+","+CinemaTables.Hall.TABLE_NAME+"."+CinemaTables.Hall.COLUMN_HALL_ID+","+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_NAME+" AS MovieName, "+CinemaTables.Hall.TABLE_NAME+"."+CinemaTables.Hall.COLUMN_DESCRIPTOR+" AS Hall,"+CinemaTables.USER_SCHEDULES_LIST.TABLE_NAME+"."+CinemaTables.USER_SCHEDULES_LIST.DATE_TIME+" AS Date FROM "+CinemaTables.USER_SCHEDULES_LIST.TABLE_NAME+" INNER JOIN "+CinemaTables.Movie.TABLE_NAME+" ON "+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_ID+" = "+CinemaTables.USER_SCHEDULES_LIST.TABLE_NAME+"."+CinemaTables.USER_SCHEDULES_LIST.COLUMN_MOVIE_ID+" INNER JOIN "+CinemaTables.Hall.TABLE_NAME+" ON "+CinemaTables.Hall.TABLE_NAME+"."+CinemaTables.Hall.COLUMN_HALL_ID+" = "+CinemaTables.USER_SCHEDULES_LIST.TABLE_NAME+"."+CinemaTables.USER_SCHEDULES_LIST.COLUMN_HALL_ID,null);
        // Cursor cursor = db.rawQuery("SELECT "+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_ID+" FROM "+CinemaTables.Movie_Hall.TABLE_NAME+" INNER JOIN "+CinemaTables.Movie.TABLE_NAME+" ON "+CinemaTables.Movie.TABLE_NAME+"."+CinemaTables.Movie.COLUMN_MOVIE_ID+" = "+CinemaTables.Movie_Hall.TABLE_NAME+"."+CinemaTables.Movie_Hall.COLUMN_MOVIE_ID,null);
        cursor.moveToFirst();
        db.close();
        //cursor.close();
        return cursor;
    }

    public Long getMovieIdFromMovieHall(int position) {
        long id = position + 1;

        try(SQLiteDatabase db = getReadableDatabase()) {
            Cursor c = db.rawQuery("SELECT * FROM " + CinemaTables.Movie_Hall.TABLE_NAME + " WHERE " + CinemaTables.Movie_Hall.COLUMN_MOVIE_HALL_ID + "='" + id + "'", null);
            //  +"' AND "+CinemaTables.Hall.COLUMN_HALL_ID+"='"+hallId+"'"
            if (c.moveToFirst()) {
               // do {
                    //movies.add(c.getString(c.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_ID))+"  "+c.getString(c.getColumnIndex(CinemaTables.Movie.COLUMN_MOVIE_NAME)));
                   Long movieId = c.getLong(c.getColumnIndex(CinemaTables.Movie_Hall.COLUMN_MOVIE_ID));
                   return movieId;
               // } while (c.moveToNext());

            }
            return null;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
