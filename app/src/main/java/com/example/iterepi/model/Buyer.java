package com.example.iterepi.model;

import java.io.Serializable;
import java.util.HashMap;

public class Buyer implements Serializable{

    public static final int WOMAN = 0;
    public static final int MAN = 1;

    private String id;
    private String name;
    private String identification;
    private String email;
    private String photo;
    private int gender;
    private String birthday;
    private HashMap<String, String> purchasesID;
    private Cart cart;
    private HashMap<String, Card> cards;

    public Buyer() {
    }

    public Buyer(String id, String name, String identification, String email, String photo, int gender, String birthday, HashMap<String, String> purchasesID, Cart cart, HashMap<String, Card> cards) {
        this.id = id;
        this.name = name;
        this.identification = identification;
        this.email = email;
        this.photo = photo;
        this.gender = gender;
        this.birthday = birthday;
        this.purchasesID = purchasesID;
        this.cart = cart;
        this.cards = cards;
    }

    public HashMap<String, Card> getCards() {
        return cards;
    }

    public void setCards(HashMap<String, Card> cards) {
        this.cards = cards;
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public HashMap<String, String> getPurchasesID() {
        return purchasesID;
    }

    public void setPurchasesID(HashMap<String, String> purchasesID) {
        this.purchasesID = purchasesID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
