package com.example.iterepi.view.store;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.AddPlaceController;
import com.example.iterepi.model.Place;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddPlaceDialog extends AppCompatActivity {

    private TextInputLayout placeNameTF;
    private TextInputLayout placeLocationTF;

    private ImageButton mapLocationPlaceBtn;
    private ImageButton closeBtn;
    private Button addPlaceBtn;

    private AddPlaceController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_place);

        placeNameTF = findViewById(R.id.categoryNameTF);
        placeLocationTF = findViewById(R.id.placeLocationTF);
        mapLocationPlaceBtn = findViewById(R.id.mapLocationPlaceBtn);
        addPlaceBtn = findViewById(R.id.updateDataBtn);
        closeBtn = findViewById(R.id.closeBtn);


        controller = new AddPlaceController(this);
    }

    public TextInputLayout getPlaceNameTF() {
        return placeNameTF;
    }

    public TextInputLayout getPlaceLocationTF() {
        return placeLocationTF;
    }

    public ImageButton getMapLocationPlaceBtn() {
        return mapLocationPlaceBtn;
    }


    public Button getAddPlaceBtn() {
        return addPlaceBtn;
    }

    public ImageButton getCloseBtn() {
        return closeBtn;
    }
}
