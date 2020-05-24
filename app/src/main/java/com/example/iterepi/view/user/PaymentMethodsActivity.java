package com.example.iterepi.view.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.iterepi.R;

public class PaymentMethodsActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private RecyclerView listMethods;
    private ImageButton addMethodBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        backBtn = findViewById(R.id.backBtn);
        listMethods = findViewById(R.id.listProductsRV);
        addMethodBtn = findViewById(R.id.addMethodBtn);

    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(ImageButton backBtn) {
        this.backBtn = backBtn;
    }

    public RecyclerView getListMethods() {
        return listMethods;
    }

    public void setListMethods(RecyclerView listMethods) {
        this.listMethods = listMethods;
    }

    public ImageButton getAddMethodBtn() {
        return addMethodBtn;
    }

    public void setAddMethodBtn(ImageButton addMethodBtn) {
        this.addMethodBtn = addMethodBtn;
    }
}
