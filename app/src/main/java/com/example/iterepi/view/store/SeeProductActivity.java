package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.SeeProductController;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Item;
import com.google.android.material.textfield.TextInputLayout;

public class SeeProductActivity extends AppCompatActivity {

    private TextView itemName;
    private ImageButton backBtn3;
    private ImageView purchaseImageIV;
    private Button updateDataBtn;

    private TextInputLayout nameProductTF;
    private TextInputLayout priceProductTF;
    private TextInputLayout inventoryQualityTF;
    private TextInputLayout descriptionProductTF;

    private int placePosition;
    private int categoryPosition;
    private int itemPosition;

    private Item item;

    private SeeProductController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_product);

        backBtn3 = findViewById(R.id.backBtn);
        itemName = findViewById(R.id.placeNameTV);
        purchaseImageIV = findViewById(R.id.purchaseImageIV);
        updateDataBtn = findViewById(R.id.updateDataBtn);

        nameProductTF = findViewById(R.id.nameProductTF);
        priceProductTF = findViewById(R.id.priceProductTF);
        inventoryQualityTF = findViewById(R.id.inventoryQualityTF);
        descriptionProductTF = findViewById(R.id.descriptionProduCtTF);

        this.placePosition = (Integer) getIntent().getExtras().getSerializable("placePosition");
        this.categoryPosition = (Integer) getIntent().getExtras().getSerializable("categoryPosition");
        this.itemPosition = (Integer) getIntent().getExtras().getSerializable("itemPosition");

        controller = new SeeProductController(this);
    }

    public ImageButton getBackBtn3() {
        return backBtn3;
    }

    public Button getUpdateDataBtn() {
        return updateDataBtn;
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

    public int getPlacePosition() {
        return placePosition;
    }

    public int getCategoryPosition() {
        return categoryPosition;
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
