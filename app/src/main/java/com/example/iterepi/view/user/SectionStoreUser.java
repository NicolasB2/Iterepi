package com.example.iterepi.view.user;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.ItemSellerAdapter;
import com.example.iterepi.controller.user.SectionStoreUserController;

import de.hdodenhof.circleimageview.CircleImageView;

public class SectionStoreUser extends AppCompatActivity {

    private TextView nameStoreTV;
    private ImageButton backBtn;
    private CircleImageView imageLogoStoreIV;
    private RecyclerView listProductsRV;
    private Spinner spinnerPlaces;
    private SectionStoreUserController controller;
    private ItemSellerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_store_user);

        nameStoreTV = findViewById(R.id.nameStoreTV);
        backBtn = findViewById(R.id.backBtn);
        spinnerPlaces = findViewById(R.id.spinnerPlaces);
        imageLogoStoreIV = findViewById(R.id.imageLogoStoreIV);
        adapter = new ItemSellerAdapter(this);
        listProductsRV = findViewById(R.id.listProductsRV);
        listProductsRV.setAdapter(adapter);

        listProductsRV = findViewById(R.id.listProductsRV);
        listProductsRV.setLayoutManager(new LinearLayoutManager(this));
        listProductsRV.setAdapter(adapter);
        DividerItemDecoration dI = new DividerItemDecoration(listProductsRV.getContext(), LinearLayoutManager.VERTICAL);
        listProductsRV.addItemDecoration(dI);

        controller = new SectionStoreUserController(this);
        adapter.setOnClickListener(controller);

    }

    public Spinner getSpinnerPlaces() {
        return spinnerPlaces;
    }

    public ItemSellerAdapter getAdapter() {
        return adapter;
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
