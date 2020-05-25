package com.example.iterepi.model;

public class Iterepi {

    private Buyer[] buyers;
    private Seller[] sellers;
    private Transaction[] transactions;
    private User[] users;

    public Iterepi() {
    }


    public Iterepi(Buyer[] buyers, Seller[] sellers, Transaction[] transactions, User[] users) {
        this.buyers = buyers;
        this.sellers = sellers;
        this.transactions = transactions;
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Buyer[] getBuyers() {
        return buyers;
    }

    public void setBuyers(Buyer[] buyers) {
        this.buyers = buyers;
    }

    public Seller[] getSellers() {
        return sellers;
    }

    public void setSellers(Seller[] sellers) {
        this.sellers = sellers;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }
}
