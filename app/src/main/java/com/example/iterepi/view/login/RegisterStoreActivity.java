package com.example.iterepi.view.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.RegisterStoreController;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterStoreActivity extends AppCompatActivity {

    private TextInputLayout nameStoreTF;
    private TextInputLayout emailStoreRegTF;
    private TextInputLayout confEmailStoreRegTF;
    private TextInputLayout passwordStoreRegTF;
    private TextInputLayout confPasswordStoreRegTF;
    private TextInputLayout nitStoreRegTF;
    private Button registerBtn;
    private CheckBox termsCB;
    private RegisterStoreController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_store);

        nameStoreTF = findViewById(R.id.nameStoreTF);
        emailStoreRegTF = findViewById(R.id.emailStoreRegTF);
        confEmailStoreRegTF = findViewById(R.id.confEmailStoreRegTF);
        passwordStoreRegTF = findViewById(R.id.passwordStoreRegTF);
        confPasswordStoreRegTF = findViewById(R.id.confPasswordStoreRegTF);
        nitStoreRegTF = findViewById(R.id.nitStoreRegTF);
        registerBtn = findViewById(R.id.registerBtn);
        termsCB = findViewById(R.id.termsCB);
        controller = new RegisterStoreController(this);


    }

    public TextInputLayout getNameStoreTF() {
        return nameStoreTF;
    }

    public TextInputLayout getEmailStoreRegTF() {
        return emailStoreRegTF;
    }

    public TextInputLayout getConfEmailStoreRegTF() {
        return confEmailStoreRegTF;
    }

    public TextInputLayout getPasswordStoreRegTF() {
        return passwordStoreRegTF;
    }

    public TextInputLayout getConfPasswordStoreRegTF() {
        return confPasswordStoreRegTF;
    }

    public TextInputLayout getNitStoreRegTF() {
        return nitStoreRegTF;
    }

    public Button getRegisterBtn() {
        return registerBtn;
    }

    public CheckBox getTermsCB() {
        return termsCB;
    }
}
