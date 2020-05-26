package com.example.iterepi.model;

import java.io.Serializable;

public class Card implements Serializable {

    private String cardNumber;
    private String expirationDate;
    private String securityCode;
    private String nameUser;
    private String lastNameUser;

    public Card() {
    }

    public Card(String cardNumber, String expirationDate, String securityCode, String nameUser, String lastNameUser) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.nameUser = nameUser;
        this.lastNameUser = lastNameUser;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }
}
