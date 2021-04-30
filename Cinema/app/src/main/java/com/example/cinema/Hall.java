package com.example.cinema;

import java.util.ArrayList;
import java.util.List;

public class Hall {

    private long id;

    private String address;

    private char descriptor;

    private List<Movie> movies = new ArrayList<>();

    public Hall(String address, char descriptor) {
        this.address = address;
        this.descriptor = descriptor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public char getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(char descriptor) {
        this.descriptor = descriptor;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
