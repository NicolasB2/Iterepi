package com.example.iterepi.model;

import com.example.iterepi.adapter.CartItemAdapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

    public double getTotalCart(){
        double total = 0;
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            Item i = entry.getValue();
            total += i.getPrice()*i.getQuantity();
        }
        return  total;
    }
}
