package com.example.iterepi.model;

import java.io.Serializable;

public class Cart implements Serializable {

    private String id;
    private Item[] items;

    public Cart() {
    }

    public Cart(String id, Item[] items) {
        this.id = id;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
