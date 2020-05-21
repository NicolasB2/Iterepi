package com.example.iterepi.view.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.iterepi.R;
import com.example.iterepi.controller.CartController;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class CartActivity extends AppCompatActivity {

    private TabLayout cartTL;
    private TabItem tabCart;
    private TabItem tabHistory;
    private ViewPager cartVP;
    private CartController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartTL = findViewById(R.id.cartTL);
        tabCart = findViewById(R.id.tabCart);
        tabHistory = findViewById(R.id.tabHistory);
        cartVP = findViewById(R.id.cartVP);

        this.controller = new CartController(this);
    }

    public TabLayout getCartTL() {
        return cartTL;
    }

    public TabItem getTabCart() {
        return tabCart;
    }

    public TabItem getTabHistory() {
        return tabHistory;
    }

    public ViewPager getCartVP() {
        return cartVP;
    }
}
