package com.example.demo.entity;

import java.time.LocalDate;

public class Weather {

    private final long id;
    private final String description;
    private final LocalDate date;

    public long getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    public LocalDate getDate() {
        return date;
    }


    public Weather(long id, String description, LocalDate date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }


}
