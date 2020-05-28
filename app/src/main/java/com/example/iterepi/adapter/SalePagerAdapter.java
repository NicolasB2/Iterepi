package com.example.iterepi.adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.iterepi.view.store.OrderFragment;
import com.example.iterepi.view.store.SaleFragment;

public class SalePagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public SalePagerAdapter(FragmentManager supportFragmentManager, int numOfTabs) {
        super(supportFragmentManager);
        this.numOfTabs = numOfTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new OrderFragment();
            case 1:
                return new SaleFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

