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

    public static final String SELLER = "seller";
    public static final String BUYER = "buyer";

    private ImageButton backBtn;

    private TextInputLayout cardNumberTF;
    private TextInputLayout expirationDateTF;
    private TextInputLayout securityCodTF;
    private TextInputLayout nameTF;
    private TextInputLayout lastNameTF;

    private Button addCardBtn;


    private AddPaymentMethodController controller;
    private String type;
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

        this.type = (String) getIntent().getExtras().getSerializable("type");
        this.cards = (List<Card>) getIntent().getExtras().getSerializable("cards");

        if(cards==null){
            cards = new ArrayList<>();
        }

        controller = new AddPaymentMethodController(this);

    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public TextInputLayout getCardNumberTF() {
        return cardNumberTF;
    }

    public TextInputLayout getExpirationDateTF() {
        return expirationDateTF;
    }

    public TextInputLayout getSecurityCodTF() {
        return securityCodTF;
    }

    public TextInputLayout getNameTF() {
        return nameTF;
    }

    public TextInputLayout getLastNameTF() {
        return lastNameTF;
    }

    public Button getAddCardBtn() {
        return addCardBtn;
    }

    public String getType() {
        return type;
    }

    public List<Card> getCards() {
        return cards;
    }
}
