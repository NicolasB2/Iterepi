package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iterepi.R;

public class SeeProductDialog extends AppCompatActivity {

    private ImageButton backBtn3;
    private TextView itemName;
    private ImageView purchaseImageIV;
    private TextView itemNameTV;
    private Spinner placeNameSpinner;
    private Spinner placeLocationSpinner;
    private TextView purchasePriceTV;
    private TextView purchaseQuantityTV;
    private TextView purchaseDescriptionTV;
    private Button updateDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_see_product);

        backBtn3 = findViewById(R.id.backBtn);
        itemName = findViewById(R.id.placeName);
        purchaseImageIV = findViewById(R.id.purchaseImageIV);
        itemNameTV = findViewById(R.id.locationTV);
        placeNameSpinner = findViewById(R.id.categoriesSpinner);
        placeLocationSpinner = findViewById(R.id.placeLocationSpinner);
        purchasePriceTV = findViewById(R.id.purchasePriceTV);
        purchaseQuantityTV = findViewById(R.id.purchaseQuantityTV);
        purchaseDescriptionTV = findViewById(R.id.purchaseDescriptionTV);
        updateDataBtn = findViewById(R.id.updateDataBtn);

    }
}
