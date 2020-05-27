package com.example.iterepi.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.iterepi.R;
import com.example.iterepi.controller.user.AddPaymentMethodController;
import com.example.iterepi.model.Card;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddPaymentMethodActivity extends AppCompatActivity {

    private ImageButton backBtn;

    private TextInputLayout cardNumberTF;
    private TextInputLayout expirationDateTF;
    private TextInputLayout securityCodTF;
    private TextInputLayout nameTF;
    private TextInputLayout lastNameTF;

    private Button addCardBtn;


    private AddPaymentMethodController controller;

    private List<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);

        backBtn = findViewById(R.id.backBtn);

        cardNumberTF = findViewById(R.id.cardNumberTF);
        expirationDateTF = findViewById(R.id.expirationDateTF);
        securityCodTF = findViewById(R.id.securityCodTF);
        nameTF = findViewById(R.id.nameTF);
        lastNameTF = findViewById(R.id.lastNameTF);

        addCardBtn = findViewById(R.id.addCardBtn);

        cards = (List<Card>) getIntent().getExtras().getSerializable("cards");
        if(cards==null){
            cards = new ArrayList<>();
        }

        controller = new AddPaymentMethodController(this);

    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(ImageButton backBtn) {
        this.backBtn = backBtn;
    }

    public TextInputLayout getCardNumberTF() {
        return cardNumberTF;
    }

    public void setCardNumberTF(TextInputLayout cardNumberTF) {
        this.cardNumberTF = cardNumberTF;
    }

    public TextInputLayout getExpirationDateTF() {
        return expirationDateTF;
    }

    public void setExpirationDateTF(TextInputLayout expirationDateTF) {
        this.expirationDateTF = expirationDateTF;
    }

    public TextInputLayout getSecurityCodTF() {
        return securityCodTF;
    }

    public void setSecurityCodTF(TextInputLayout securityCodTF) {
        this.securityCodTF = securityCodTF;
    }

    public TextInputLayout getNameTF() {
        return nameTF;
    }

    public void setNameTF(TextInputLayout nameTF) {
        this.nameTF = nameTF;
    }

    public TextInputLayout getLastNameTF() {
        return lastNameTF;
    }

    public void setLastNameTF(TextInputLayout lastNameTF) {
        this.lastNameTF = lastNameTF;
    }

    public Button getAddCardBtn() {
        return addCardBtn;
    }

    public void setAddCardBtn(Button addCardBtn) {
        this.addCardBtn = addCardBtn;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
