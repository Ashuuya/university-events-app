package com.example.eventslook.model;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Datalist {


    private List<Data> events;

    public List<Data> getEvents() {
        return events;
    }
    public void setEvents(List<Data> events) {
        this.events = events;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return ""+ events;
    }
}
