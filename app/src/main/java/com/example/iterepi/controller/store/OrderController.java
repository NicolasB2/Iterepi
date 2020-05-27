package com.example.iterepi.controller.store;

import com.example.iterepi.adapter.OrderPagerAdapter;
import com.example.iterepi.view.store.OrderActivity;
import com.google.android.material.tabs.TabLayout;

public class OrderController {

    private OrderActivity activity;
    private OrderPagerAdapter adapter;

    public OrderController(OrderActivity activity) {
        this.activity = activity;

        adapter = new OrderPagerAdapter(activity.getSupportFragmentManager(), activity.getOrderTL().getTabCount());
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
