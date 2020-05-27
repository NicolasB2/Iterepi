package com.example.iterepi.view.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.SellerAdapter;
import com.example.iterepi.controller.user.UserFeedController;

public class UserFeedActivity extends NavigationViewActivity {

    private UserFeedController controller;
    private RecyclerView sellersList;
    private SellerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_user_feed, null, false);
        getDrawerLayout().addView(contentView, 0);


        adapter = new SellerAdapter();

        sellersList = findViewById(R.id.sellersList);
        sellersList.setLayoutManager(new LinearLayoutManager(this));
        sellersList.setAdapter(adapter);
        DividerItemDecoration dI = new DividerItemDecoration(sellersList.getContext(), LinearLayoutManager.VERTICAL);
        sellersList.addItemDecoration(dI);

        controller = new UserFeedController(this);
        adapter.setOnClickListener(controller);

    }

    public RecyclerView getSellersList() {
        return sellersList;
    }

    public SellerAdapter getAdapter() {
        return adapter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
