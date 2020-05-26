package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.PlacesAdapter;
import com.example.iterepi.controller.MyPlacesController;

public class MyPlacesActivity extends AppCompatActivity {

    private MyPlacesController myPlacesController;
    private ListView myPlacesList;
    private PlacesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);

        this.myPlacesList = findViewById(R.id.myPlacesList);
        this.myPlacesController = new MyPlacesController(this);
        this.adapter = new PlacesAdapter();
        this.myPlacesList.setAdapter(adapter);
    }

    public ListView getMyPlacesList() {
        return myPlacesList;
    }

    public PlacesAdapter getAdapter() {
        return adapter;
    }
}
