package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.CategoriesAdapter;
import com.example.iterepi.adapter.PlacesAdapter;
import com.example.iterepi.controller.store.MyCategoriesController;
import com.example.iterepi.model.Place;

public class MyCategoriesActivity extends AppCompatActivity {

    private MyCategoriesController controller;
    private ListView myCategoriesList;
    private CategoriesAdapter adapter;
    private ImageButton addMethodBtn;
    private ImageButton backBtn;
    private Place place;
    private int placePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_categories);

        this.place = (Place) getIntent().getExtras().getSerializable("place");
        this.placePosition = (Integer) getIntent().getExtras().getSerializable("placePosition");

        this.myCategoriesList = findViewById(R.id.myCategoriesList);
        this.addMethodBtn = findViewById(R.id.addMethodBtn);
        this.backBtn = findViewById(R.id.backBtn);

        this.controller = new MyCategoriesController(this);
        this.adapter = new CategoriesAdapter(place.getCategories());
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getPlacePosition() {
        return placePosition;
    }
}
