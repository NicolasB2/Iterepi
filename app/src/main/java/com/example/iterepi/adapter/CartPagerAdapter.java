package com.example.iterepi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.iterepi.view.user.CartFragment;
import com.example.iterepi.view.user.PurchaseHistoryFragment;

public class CartPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public CartPagerAdapter(FragmentManager fm,int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new CartFragment();
            case 1:
                return new PurchaseHistoryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
