package com.example.iterepi.view.user;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;

public class SectionStoreUser extends AppCompatActivity {

    private TextView nameStoreTV;
    private ImageButton backBtn;
    private ImageView imageLogoStoreIV;
    private RecyclerView listProductsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_store_user);

        nameStoreTV = findViewById(R.id.nameStoreTV);
        backBtn = findViewById(R.id.backBtn);
        imageLogoStoreIV = findViewById(R.id.imageLogoStoreIV);
        listProductsRV = findViewById(R.id.listProductsRV);

    }

    public TextView getNameStoreTV() {
        return nameStoreTV;
    }

    public void setNameStoreTV(TextView nameStoreTV) {
        this.nameStoreTV = nameStoreTV;
    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(ImageButton backBtn) {
        this.backBtn = backBtn;
    }

    public ImageView getImageLogoStoreIV() {
        return imageLogoStoreIV;
    }

    public void setImageLogoStoreIV(ImageView imageLogoStoreIV) {
        this.imageLogoStoreIV = imageLogoStoreIV;
    }

    public RecyclerView getListProductsRV() {
        return listProductsRV;
    }

    public void setListProductsRV(RecyclerView listProductsRV) {
        this.listProductsRV = listProductsRV;
    }
}
