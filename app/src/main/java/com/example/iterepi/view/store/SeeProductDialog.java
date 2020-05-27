package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iterepi.R;
import com.google.android.material.textfield.TextInputLayout;

public class SeeProductDialog extends AppCompatActivity {

    private TextView itemName;
    private ImageButton backBtn3;
    private ImageView purchaseImageIV;

    private TextInputLayout nameProductTF;
    private TextInputLayout priceProductTF;
    private TextInputLayout inventoryQualityTF;
    private TextInputLayout descriptionProductTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_product);

        backBtn3 = findViewById(R.id.backBtn);
        itemName = findViewById(R.id.placeNameTV);
        purchaseImageIV = findViewById(R.id.purchaseImageIV);

        nameProductTF = findViewById(R.id.nameProductTF);
        priceProductTF = findViewById(R.id.priceProductTF);
        inventoryQualityTF = findViewById(R.id.inventoryQualityTF);
        descriptionProductTF = findViewById(R.id.descriptionProduCtTF);
    }

    public ImageButton getBackBtn3() {
        return backBtn3;
    }

    public TextView getItemName() {
        return itemName;
    }

    public ImageView getPurchaseImageIV() {
        return purchaseImageIV;
    }

    public TextInputLayout getNameProductTF() {
        return nameProductTF;
    }

    public TextInputLayout getPriceProductTF() {
        return priceProductTF;
    }

    public TextInputLayout getInventoryQualityTF() {
        return inventoryQualityTF;
    }

    public TextInputLayout getDescriptionProductTF() {
        return descriptionProductTF;
    }
}
