package com.example.iterepi.model;

public class Iterepi {

    private Buyer[] buyers;
    private Seller[] sellers;
    private Transaction[] transactions;

    public Iterepi() {
    }


    public Iterepi(Buyer[] buyers, Seller[] sellers, Transaction[] transactions) {
        this.buyers = buyers;
        this.sellers = sellers;
        this.transactions = transactions;
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
