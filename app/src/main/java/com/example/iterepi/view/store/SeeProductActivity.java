package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.SeeProductController;
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

    private String placeId;
    private String categoryId;
    private String itemId;

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

        this.placeId = (String) getIntent().getExtras().getSerializable("placeId");
        this.categoryId = (String) getIntent().getExtras().getSerializable("categoryId");
        this.itemId = (String) getIntent().getExtras().getSerializable("itemId");

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

    public String getPlaceId() {
        return placeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getItemId() {
        return itemId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
