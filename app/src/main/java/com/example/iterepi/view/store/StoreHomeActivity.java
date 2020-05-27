package com.example.iterepi.view.store;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.StoreHomeController;
import com.example.iterepi.model.Seller;

public class StoreHomeActivity extends SellerNavigationDrawerActivity {

    private Button addPlaceBtn;
    private Button addCategoryBtn;
    private Button addProductBtn;
    private StoreHomeController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_store_home, null, false);
        getDrawerLayout().addView(contentView, 0);

        addPlaceBtn = findViewById(R.id.addPlaceBtn);
        addCategoryBtn = findViewById(R.id.addCategoryBtn);
        addProductBtn = findViewById(R.id.addProductBtn);

        controller = new StoreHomeController(this);

    }

    public Button getAddPlaceBtn() {
        return addPlaceBtn;
    }

    public Button getAddCategoryBtn() {
        return addCategoryBtn;
    }

    public Button getAddProductBtn() {
        return addProductBtn;
    }
    
}
