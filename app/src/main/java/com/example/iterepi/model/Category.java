package com.example.iterepi.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.HashMap;

public class Category implements Serializable {

    private String id;
    private String name;
    private HashMap<String,Item> items;

    public Category() {

    }

    public Category(String id, String name,HashMap<String,Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
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

    public HashMap<String,Item> getItems() {
        return items;
    }

    public void setItems(HashMap<String,Item> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
