package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.PlacesAdapter;
import com.example.iterepi.controller.store.MyPlacesController;

public class MyPlacesActivity extends AppCompatActivity {

    private MyPlacesController controller;
    private ListView myPlacesList;
    private PlacesAdapter adapter;
    private ImageButton addMethodBtn;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);

        this.myPlacesList = findViewById(R.id.myCategoriesList);
        this.addMethodBtn = findViewById(R.id.addMethodBtn);
        this.backBtn = findViewById(R.id.backBtn);

        this.controller = new MyPlacesController(this);
        this.adapter = new PlacesAdapter(this);
        this.myPlacesList.setAdapter(adapter);
    }

    public ListView getMyPlacesList() {
        return myPlacesList;
    }

    public PlacesAdapter getAdapter() {
        return adapter;
    }

    public ImageButton getAddMethodBtn() {
        return addMethodBtn;
    }

    public ImageButton getBackBtn() {
        return backBtn;
    }


}
