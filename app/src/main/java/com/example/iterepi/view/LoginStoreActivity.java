package com.example.iterepi.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.LoginStoreController;
import com.google.android.material.textfield.TextInputEditText;

public class LoginStoreActivity extends AppCompatActivity {

    private LoginStoreController controller;
    private Button forgotPassBtn;
    private Button registerHereBtn;
    private TextInputEditText nit;
    private TextInputEditText pass;
    private Button updateDataBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_store);

        forgotPassBtn = findViewById(R.id.forgotPass);
        registerHereBtn = findViewById(R.id.registerHereStore);
        nit = findViewById(R.id.inputNitTF);
        pass = findViewById(R.id.inputPassTF);
        updateDataBtn = findViewById(R.id.update_dataBtn);

        controller = new LoginStoreController(this);


    }

    public Button getForgotPassBtn() {
        return forgotPassBtn;
    }

    public Button getRegisterHereBtn() {
        return registerHereBtn;
    }

    public TextInputEditText getNit() {
        return nit;
    }

    public TextInputEditText getPass() {
        return pass;
    }

    public Button getUpdateDataBtn() {
        return updateDataBtn;
    }
}
