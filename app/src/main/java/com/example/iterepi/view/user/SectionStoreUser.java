package com.example.iterepi.view.user;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.controller.user.SectionStoreUserController;

import de.hdodenhof.circleimageview.CircleImageView;

public class SectionStoreUser extends AppCompatActivity {

    private TextView nameStoreTV;
    private ImageButton backBtn;
    private CircleImageView imageLogoStoreIV;
    private RecyclerView listProductsRV;
    private SectionStoreUserController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_store_user);

        nameStoreTV = findViewById(R.id.nameStoreTV);
        backBtn = findViewById(R.id.backBtn);
        imageLogoStoreIV = findViewById(R.id.imageLogoStoreIV);
        listProductsRV = findViewById(R.id.listProductsRV);

        controller = new SectionStoreUserController(this);

    }

    public TextView getNameStoreTV() {
        return nameStoreTV;
    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public CircleImageView getImageLogoStoreIV() {
        return imageLogoStoreIV;
    }

    public RecyclerView getListProductsRV() {
        return listProductsRV;
    }
}
