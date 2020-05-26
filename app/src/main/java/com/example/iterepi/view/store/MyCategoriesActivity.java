package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.CategoriesAdapter;
import com.example.iterepi.controller.store.MyCategoriesController;
import com.example.iterepi.model.Place;
import com.google.android.material.textfield.TextInputLayout;

public class MyCategoriesActivity extends AppCompatActivity {

    private MyCategoriesController controller;

    private TextView placeNameTV;
    private TextInputLayout placeNameTF;
    private TextInputLayout placeLocationTF;
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

        this.placePosition = (Integer) getIntent().getExtras().getSerializable("placePosition");

        this.myCategoriesList = findViewById(R.id.myCategoriesList);
        this.addMethodBtn = findViewById(R.id.addMethodBtn);
        this.backBtn = findViewById(R.id.backBtn);
        this.placeNameTV = findViewById(R.id.placeNameTV);
        this.placeNameTF = findViewById(R.id.placeNameTF);
        this.placeLocationTF = findViewById(R.id.placeLocationTF);

        this.controller = new MyCategoriesController(this);

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

    public TextView getPlaceNameTV() {
        return placeNameTV;
    }

    public TextInputLayout getPlaceNameTF() {
        return placeNameTF;
    }

    public TextInputLayout getPlaceLocationTF() {
        return placeLocationTF;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
        this.adapter = new CategoriesAdapter(place.getCategories());
        this.myCategoriesList.setAdapter(adapter);
    }

    public int getPlacePosition() {
        return placePosition;
    }
}
