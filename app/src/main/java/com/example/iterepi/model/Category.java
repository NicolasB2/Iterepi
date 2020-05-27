package com.example.iterepi.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Category implements Serializable {

    private String id;
    private String name;
    private Item[] items;

    public Category() {
        items = new Item[0];
    }

    public Category(String id, String name, Item[] items) {
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

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int numItems(){
        if(items==null){
            return 0;
        }else{
            return items.length;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
