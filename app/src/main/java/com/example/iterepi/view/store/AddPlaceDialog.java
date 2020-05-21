package com.example.iterepi.view.store;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddPlaceDialog extends AppCompatActivity {

    private TextInputLayout placeNameTF;
    private TextInputLayout placeLocationTF;

    private ImageButton mapLocationPlaceBtn;
    private Button addPlaceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_place);

        placeNameTF = findViewById(R.id.categoryNameTF);
        placeLocationTF = findViewById(R.id.placeLocationTF);
        mapLocationPlaceBtn = findViewById(R.id.mapLocationPlaceBtn);
        addPlaceBtn = findViewById(R.id.updateDataBtn);


    }

    public TextInputLayout getPlaceNameTF() {
        return placeNameTF;
    }

    public void setPlaceNameTF(TextInputLayout placeNameTF) {
        this.placeNameTF = placeNameTF;
    }

    public TextInputLayout getPlaceLocationTF() {
        return placeLocationTF;
    }

    public void setPlaceLocationTF(TextInputLayout placeLocationTF) {
        this.placeLocationTF = placeLocationTF;
    }

    public ImageButton getMapLocationPlaceBtn() {
        return mapLocationPlaceBtn;
    }

    public void setMapLocationPlaceBtn(ImageButton mapLocationPlaceBtn) {
        this.mapLocationPlaceBtn = mapLocationPlaceBtn;
    }

    public Button getAddPlaceBtn() {
        return addPlaceBtn;
    }

    public void setAddPlaceBtn(Button addPlaceBtn) {
        this.addPlaceBtn = addPlaceBtn;
    }
}
