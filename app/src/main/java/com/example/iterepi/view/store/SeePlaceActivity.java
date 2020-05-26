package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.CategoriesAdapter;
import com.example.iterepi.controller.store.seePlaceController;
import com.example.iterepi.model.Place;
import com.google.android.material.textfield.TextInputLayout;

public class SeePlaceActivity extends AppCompatActivity {

    private seePlaceController controller;

    private TextView placeNameTV;
    private TextInputLayout placeNameTF;
    private TextInputLayout placeLocationTF;
    private ListView myCategoriesList;
    private CategoriesAdapter adapter;
    private Button addMethodBtn;
    private Button updateDataBtn;
    private ImageButton backBtn;
    private Place place;
    private int placePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_place);

        this.placePosition = (Integer) getIntent().getExtras().getSerializable("placePosition");

        this.myCategoriesList = findViewById(R.id.myCategoriesList);
        this.addMethodBtn = findViewById(R.id.addMethodBtn);
        this.updateDataBtn = findViewById(R.id.updateDataBtn);
        this.backBtn = findViewById(R.id.backBtn);
        this.placeNameTV = findViewById(R.id.placeNameTV);
        this.placeNameTF = findViewById(R.id.placeNameTF);
        this.placeLocationTF = findViewById(R.id.placeLocationTF);

        this.controller = new seePlaceController(this);

    }

    public ListView getMyCategoriesList() {
        return myCategoriesList;
    }

    public CategoriesAdapter getAdapter() {
        return adapter;
    }

    public Button getAddMethodBtn() {
        return addMethodBtn;
    }

    public Button getUpdateDataBtn() {
        return updateDataBtn;
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
