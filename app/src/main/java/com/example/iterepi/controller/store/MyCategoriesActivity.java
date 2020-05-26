package com.example.iterepi.controller.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.CategoriesAdapter;
import com.example.iterepi.adapter.PlacesAdapter;

public class MyCategoriesActivity extends AppCompatActivity {

    private MyCategoriesController controller;
    private ListView myCategoriesList;
    private CategoriesAdapter adapter;
    private ImageButton addMethodBtn;
    private ImageButton backBtn;
    private int placePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_categories);

        placePosition = (Integer) getIntent().getExtras().getSerializable("placePosition");

        this.myCategoriesList = findViewById(R.id.myCategoriesList);
        this.addMethodBtn = findViewById(R.id.addMethodBtn);
        this.backBtn = findViewById(R.id.backBtn);

        this.controller = new MyCategoriesController(this);
        this.adapter = new CategoriesAdapter();
        this.myCategoriesList.setAdapter(adapter);
    }

    public ListView getMyCategoriesList() {
        return myCategoriesList;
    }

    public CategoriesAdapter getAdapter() {
        return adapter;
    }

    public ImageButton getAddMethodBtn() {
        return addMethodBtn;
    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public int getPlacePosition() {
        return placePosition;
    }
}
