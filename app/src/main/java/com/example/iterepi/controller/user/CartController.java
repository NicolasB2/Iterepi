package com.example.iterepi.controller.user;

import com.example.iterepi.adapter.CartPagerAdapter;
import com.example.iterepi.view.user.CartActivity;
import com.google.android.material.tabs.TabLayout;

public class CartController {

    private CartActivity activity;
    private CartPagerAdapter adapter;

    public CartController(CartActivity activity) {
        this.activity = activity;

        adapter = new CartPagerAdapter(activity.getSupportFragmentManager(), activity.getCartTL().getTabCount());
        activity.getCartVP().setAdapter(adapter);

        //Cambiar tab view cuando la tab es selected or clicked
        activity.getCartTL().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                activity.getCartVP().setCurrentItem(tab.getPosition());
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
