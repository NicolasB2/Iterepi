package com.example.iterepi.view.store;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddCategoryDialog extends AppCompatActivity {


    private ImageButton closeBtn;
    private TextInputLayout categoryNameTF;
    private Spinner placeSP;
    private Button addPlaceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_category);

        closeBtn = findViewById(R.id.closeBtn);
        categoryNameTF = findViewById(R.id.categoryNameTF);
        placeSP = findViewById(R.id.placeSP);
        addPlaceBtn = findViewById(R.id.updateDataBtn);

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

    public Button getAddPlaceBtn() {
        return addPlaceBtn;
    }

    public void setAddPlaceBtn(Button addPlaceBtn) {
        this.addPlaceBtn = addPlaceBtn;
    }
}
