package com.example.cinema;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private long id;

    private String movieName;

    private String director;

    private int length;

    private String description;

    private String mainActor;

    private List<Hall> halls = new ArrayList<>();

    public Movie(String movieName, String director, int length, String description, String mainActor) {
        this.movieName=movieName;
        this.director = director;
        this.length = length;
        this.description = description;
        this.mainActor = mainActor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }
}
