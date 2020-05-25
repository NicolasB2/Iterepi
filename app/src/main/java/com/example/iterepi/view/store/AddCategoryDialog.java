package com.example.iterepi.view.store;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.AddCategoryController;
import com.google.android.material.textfield.TextInputLayout;

public class AddCategoryDialog extends AppCompatActivity {


    private ImageButton closeBtn;
    private TextInputLayout categoryNameTF;
    private Spinner placeSP;
    private Button addCategoryBtn;
    private AddCategoryController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_category);

        closeBtn = findViewById(R.id.closeBtn);
        categoryNameTF = findViewById(R.id.categoryNameTF);
        placeSP = findViewById(R.id.placeSP);
        addCategoryBtn = findViewById(R.id.updateDataBtn);

        this.controller = new AddCategoryController(this);

    }

    public ImageButton getCloseBtn() {
        return closeBtn;
    }

    public void setCloseBtn(ImageButton closeBtn) {
        this.closeBtn = closeBtn;
    }

    public TextInputLayout getCategoryNameTF() {
        return categoryNameTF;
    }

    public void setCategoryNameTF(TextInputLayout categoryNameTF) {
        this.categoryNameTF = categoryNameTF;
    }

    public Spinner getPlaceSP() {
        return placeSP;
    }

    public void setPlaceSP(Spinner placeSP) {
        this.placeSP = placeSP;
    }

    public Button getAddCategoryBtn() {
        return addCategoryBtn;
    }

    public void setAddCategoryBtn(Button addPlaceBtn) {
        this.addCategoryBtn = addPlaceBtn;
    }
}
