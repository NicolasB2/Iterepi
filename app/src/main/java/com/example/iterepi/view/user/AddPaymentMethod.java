package com.example.iterepi.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.iterepi.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddPaymentMethod extends AppCompatActivity {

    private ImageButton backBtn;

    private TextInputLayout cardNumberTF;
    private TextInputLayout expirationDate;
    private TextInputLayout securityCodTF;
    private TextInputLayout nameTF;
    private TextInputLayout lastNameTF;

    private Button addCardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);

        backBtn = findViewById(R.id.backBtn);

        cardNumberTF = findViewById(R.id.cardNumberTF);
        expirationDate = findViewById(R.id.expirationDateTF);
        securityCodTF = findViewById(R.id.securityCodTF);
        nameTF = findViewById(R.id.nameTF);
        lastNameTF = findViewById(R.id.lastNameTF);

        addCardBtn = findViewById(R.id.addCardBtn);

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

    public TextInputLayout getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(TextInputLayout expirationDate) {
        this.expirationDate = expirationDate;
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
}
