package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iterepi.R;

public class SeePlaceDialog extends AppCompatActivity {

    private ImageButton backBtn3;
    private TextView placeNameTV;
    private TextView locationTV;
    private Spinner categoriesSpinner;
    private Button updateDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_see_place);

        backBtn3 = findViewById(R.id.backBtn);
        placeNameTV = findViewById(R.id.placeNameTV);
        locationTV = findViewById(R.id.locationTV);
        categoriesSpinner = findViewById(R.id.categoriesSpinner);
        updateDataBtn = findViewById(R.id.updateDataBtn);
    }
}
