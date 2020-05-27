package com.example.iterepi.model;

import java.io.Serializable;
import java.util.HashMap;

public class Cart implements Serializable {

    private String id;
    private HashMap<String, Item> items;

    public Cart() {
    }

    public Cart(String id, HashMap<String, Item> items) {
        this.id = id;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }
}
