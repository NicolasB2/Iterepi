package com.example.iterepi.view.login;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.login.MainController;

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
