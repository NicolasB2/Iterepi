package com.example.iterepi.model;

import java.io.Serializable;

public class Seller implements Serializable {

    private String id;
    private String name;
    private String nit;
    private String email;
    private String logo;
    private String[] salesID;
    private Place[] places;
    private Card[] cards;

    public Seller() {
    }

    public Seller(String id, String name, String nit, String email, String logo, String[] salesID, Place[] places, Card[] cards) {
        this.id = id;
        this.name = name;
        this.nit = nit;
        this.email = email;
        this.logo = logo;
        this.salesID = salesID;
        this.places = places;
        this.cards = cards;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String[] getSalesID() {
        return salesID;
    }

    public void setSalesID(String[] salesID) {
        this.salesID = salesID;
    }

    public Place[] getPlaces() {
        return places;
    }

    public void setPlaces(Place[] places) {
        this.places = places;
    }
}
