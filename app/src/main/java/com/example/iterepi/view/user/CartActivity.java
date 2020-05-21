package com.example.iterepi.view.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.iterepi.R;
import com.example.iterepi.adapter.CestPagerAdapter;
import com.example.iterepi.controller.CestController;

public class CestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cest);
    }
}
