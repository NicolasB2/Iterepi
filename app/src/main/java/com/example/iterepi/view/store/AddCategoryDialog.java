package com.example.iterepi.view.store;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.AddCategoryController;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddCategoryDialog extends AppCompatActivity {


    private ImageButton closeBtn;
    private TextInputLayout categoryNameTF;
    private Spinner placeOfProductSP;

    private Button addCategoryBtn;
    private AddCategoryController controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_category);

        closeBtn = findViewById(R.id.closeBtn);
        categoryNameTF = findViewById(R.id.categoryNameTF);
        placeOfProductSP = findViewById(R.id.placeOfProductSP);
        addCategoryBtn = findViewById(R.id.updateDataBtn);

        this.controller = new AddCategoryController(this);

    }

    public ImageButton getCloseBtn() {
        return closeBtn;
    }

    public TextInputLayout getCategoryNameTF() {
        return categoryNameTF;
    }

    public Spinner getPlaceOfProductSP() {
        return placeOfProductSP;
    }

    public Button getAddCategoryBtn() {
        return addCategoryBtn;
    }
}
