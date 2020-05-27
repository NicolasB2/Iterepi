package com.example.iterepi.view.store;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddProductDialog extends AppCompatActivity {

    private ImageButton closeBtn;
    private ImageButton addImageProductBtn;

    private TextInputLayout nameProductTF;
    private TextInputLayout priceProductTF;
    private TextInputLayout inventoryQualityTF;
    private TextInputLayout descriptionProductTF;

    private Spinner placeOfProductSP;
    private Spinner categoryOfProductSP;

    private Button addProductBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_product);

        closeBtn = findViewById(R.id.closeBtn);
        addImageProductBtn = findViewById(R.id.addImageProductBtn);

        priceProductTF = findViewById(R.id.priceProductTF);
        inventoryQualityTF = findViewById(R.id.inventoryQualityTF);
        descriptionProductTF = findViewById(R.id.descriptionProduCtTF);

        placeOfProductSP = findViewById(R.id.placeOfProductSP);
        categoryOfProductSP = findViewById(R.id.categoryOfProductSP);

        addProductBtn = findViewById(R.id.addProductBtn);

    }

    public ImageButton getCloseBtn() {
        return closeBtn;
    }

    public ImageButton getAddImageProductBtn() {
        return addImageProductBtn;
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

    public Spinner getPlaceOfProductSP() {
        return placeOfProductSP;
    }

    public Spinner getCategoryOfProductSP() {
        return categoryOfProductSP;
    }

    public Button getAddProductBtn() {
        return addProductBtn;
    }
}
