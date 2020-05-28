package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.SaleController;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SaleActivity extends AppCompatActivity {


    private TabLayout orderTL;
    private TabItem tabOrder;
    private TabItem tabSale;
    private ViewPager orderVP;
    private SaleController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_sale);

        orderTL = findViewById(R.id.orderTL);
        tabOrder = findViewById(R.id.tabOrder);
        tabSale = findViewById(R.id.tabSale);
        orderVP = findViewById(R.id.orderVP);

        this.controller = new SaleController(this);


    }

    public TabLayout getOrderTL() {
        return orderTL;
    }

    public void setOrderTL(TabLayout orderTL) {
        this.orderTL = orderTL;
    }

    public TabItem getTabOrder() {
        return tabOrder;
    }

    public void setTabOrder(TabItem tabOrder) {
        this.tabOrder = tabOrder;
    }

    public TabItem getTabSale() {
        return tabSale;
    }

    public void setTabSale(TabItem tabSale) {
        this.tabSale = tabSale;
    }

    public ViewPager getOrderVP() {
        return orderVP;
    }

    public void setOrderVP(ViewPager orderVP) {
        this.orderVP = orderVP;
    }
}
