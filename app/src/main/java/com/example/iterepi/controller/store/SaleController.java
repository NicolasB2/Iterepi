package com.example.iterepi.controller.store;

import com.example.iterepi.adapter.SalePagerAdapter;
import com.example.iterepi.view.store.SaleActivity;
import com.google.android.material.tabs.TabLayout;

public class SaleController {

    private SaleActivity activity;
    private SalePagerAdapter adapter;

    public SaleController(SaleActivity activity) {
        this.activity = activity;

        adapter = new SalePagerAdapter(activity.getSupportFragmentManager(), activity.getOrderTL().getTabCount());
        activity.getOrderVP().setAdapter(adapter);

        activity.getOrderTL().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                activity.getOrderVP().setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
