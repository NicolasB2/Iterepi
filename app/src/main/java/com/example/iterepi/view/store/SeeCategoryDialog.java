package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iterepi.R;

public class SeeCategoryDialog extends AppCompatActivity {

    private ImageButton backBtn3;
    private TextView categoryName;
    private ImageView purchaseImageIV;
    private TextView categoryNameTV;
    private Spinner placeNameSpinner;
    private Spinner productsSpinner;
    private ListView listItems;
    private Button updateDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_see_category);

        backBtn3 = findViewById(R.id.backBtn);
        categoryName = findViewById(R.id.placeNameTV);
        purchaseImageIV = findViewById(R.id.purchaseImageIV);
        categoryNameTV = findViewById(R.id.locationTV);
        placeNameSpinner = findViewById(R.id.categoriesSpinner);
        productsSpinner = findViewById(R.id.productsSpinner);
        listItems = findViewById(R.id.listItems);
        updateDataBtn = findViewById(R.id.updateDataBtn);
    }
}
