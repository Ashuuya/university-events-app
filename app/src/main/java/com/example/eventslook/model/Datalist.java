package com.example.eventslook.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Datalist {

    @SerializedName("events")
    @Expose
    private List<Data> events;

    public List<Data> getEvents() {
        return events;
    }
    public void setEvents(List<Data> events) {
        this.events = events;
    }
    public int getSize(){
        return events.size();
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return ""+ events;
    }
}
