package com.example.iterepi.model;

public class User {

    public static final int BUYER = 1;
    public static final int SELLER = 2;

    private String id;
    private int userType;

    public User() {
    }

    public User(String id, int userType) {
        this.id = id;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
