package com.example.iterepi.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.HashMap;

public class Place implements Serializable {

    private String id;
    private String name;
    private String location;
    private HashMap<String,Category> categories;

    public Place() {

    }

    public Place(String id, String name, String location, HashMap<String,Category>  categories) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HashMap<String,Category>  getCategories() {
        return categories;
    }

    public void setCategories(HashMap<String,Category>  categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getName()+": "+this.getLocation();
    }


}
