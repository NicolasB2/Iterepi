package com.example.iterepi.model;

import com.example.iterepi.adapter.CartItemAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class Transaction {

    public static final String TO_DELIVER = "to_deliver";
    public static final  String DELIVERED = "delivered";
    public static final String TO_PICK = "to_pick";
    public static final String PICKED_UP = "picked_up";

    private String id;
    private double value;
    private String purchaseDate;
    private String buyerName;
    private String buyerID;
    private String sellerName;
    private String sellerID;
    private String state;
    private Cart cart;
    private String buyerPhoto;

    public Transaction() {
    }

    public Transaction(String id, Buyer b, Cart cart){
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm z");
        Date date = new Date(System.currentTimeMillis());
        this.id = id;
        this.value = value;
        this.purchaseDate = formatter.format(date);
        this.buyerName = b.getName();
        this.buyerID = b.getId();
        this.state = TO_PICK;
        this.cart = cart;
        this.buyerPhoto = b.getPhoto();
        this.value = cart.getTotalCart();
    }

    public Transaction(String id, double value, String purchaseDate, String buyerName, String buyerID, String sellerName, String sellerID, String state, Cart cart, String photo) {

        this.id = id;
        this.value = value;
        this.purchaseDate = purchaseDate;
        this.buyerName = buyerName;
        this.buyerID = buyerID;
        this.sellerName = sellerName;
        this.sellerID = sellerID;
        this.state = state;
        this.cart = cart;
        this.buyerPhoto = photo;
    }

    public String getBuyerPhoto() {
        return buyerPhoto;
    }

    public void setBuyerPhoto(String buyerPhoto) {
        this.buyerPhoto = buyerPhoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
