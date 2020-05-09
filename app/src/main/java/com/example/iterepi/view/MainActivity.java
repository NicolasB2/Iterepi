package com.example.iterepi.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.MainController;

public class MainActivity extends AppCompatActivity {

    private MainController controller;
    private Button buyerBtn;
    private Button storeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as);

        buyerBtn = findViewById(R.id.buyerBtn);
        storeBtn = findViewById(R.id.storeBtn);

        controller = new MainController(this);
    }

    public Button getBuyerBtn() {
        return buyerBtn;
    }

    public void setBuyerBtn(Button buyerBtn) {
        this.buyerBtn = buyerBtn;
    }

    public Button getStoreBtn() {
        return storeBtn;
    }

    public void setStoreBtn(Button storeBtn) {
        this.storeBtn = storeBtn;
    }
}
