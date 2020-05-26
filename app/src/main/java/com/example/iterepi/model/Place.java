package com.example.iterepi.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Place implements Serializable {

    private String id;
    private String name;
    private String location;
    private Category[] categories;

    public Place() {
        categories = new Category[0];
    }

    public Place(String id, String name, String location, Category[] categories) {
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

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getName()+": "+this.getLocation();
    }

    public void addCategory(Category category) {
        Category[] c = new Category[categories.length+1];

        for (int i = 0; i< categories.length;i++){
            c[i]=categories[i];
        }
        c[categories.length]=category;

        this.categories = c;
    }
}
