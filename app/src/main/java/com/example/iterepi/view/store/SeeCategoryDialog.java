package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iterepi.R;
import com.google.android.material.textfield.TextInputLayout;

public class SeeCategoryDialog extends AppCompatActivity {

    private ImageButton backBtn3;
    private TextView categoryName;
    private ImageView purchaseImageIV;
    private TextView purchaseNameTV;
    private Spinner placeNameSpinner;
    private Spinner productsSpinner;
    private ListView listItems;
    private Button updateDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_see_category);

        backBtn3 = findViewById(R.id.backBtn3);
        categoryName = findViewById(R.id.categoryName);
        purchaseImageIV = findViewById(R.id.purchaseImageIV);
        purchaseNameTV = findViewById(R.id.purchaseNameTV);
        placeNameSpinner = findViewById(R.id.placeNameSpinner);
        productsSpinner = findViewById(R.id.productsSpinner);
        listItems = findViewById(R.id.listItems);
        updateDataBtn = findViewById(R.id.updateDataBtn);
    }
}
