package com.example.cinema;

public final class CinemaTables {

    public static class Movie{

        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_MOVIE_ID = "_id";
        public static final String COLUMN_MOVIE_NAME = "movie_name";
        public static final String COLUMN_DIRECTOR = "director";
        public static final String COLUMN_LENGTH = "length";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_MAIN_ACTOR = "main_actor";

    }
    public static class Movie_Hall{

        public static final String TABLE_NAME = "movie_hall";
        //added row _id
        public static final String COLUMN_MOVIE_HALL_ID = "_id";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_HALL_ID = "hall_id";
        public static final String DATE_TIME = "date_time";
    }

    public static class USER_SCHEDULES_LIST{

        public static final String TABLE_NAME = "user_schedules";
        //added row _id
        public static final String COLUMN_MOVIE_HALL_ID = "_id";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_HALL_ID = "hall_id";
        public static final String DATE_TIME = "date_time";
    }

    public static class Hall{

        public static final String TABLE_NAME = "hall";
        public static final String COLUMN_HALL_ID = "_id";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_DESCRIPTOR = "descriptor";
    }
}
