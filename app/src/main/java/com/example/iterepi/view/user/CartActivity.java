package com.example.iterepi.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.iterepi.R;
import com.example.iterepi.controller.user.CartController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

        cartTL = findViewById(R.id.orderTL);
        tabCart = findViewById(R.id.tabCart);
        tabHistory = findViewById(R.id.tabHistory);
        cartVP = findViewById(R.id.orderVP);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.cart);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.location:
                        startActivity(new Intent(getApplicationContext(), LocationActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.feed:
                        startActivity(new Intent(getApplicationContext(), UserFeedActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }


                return false;
            }
        });


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
