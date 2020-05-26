package com.example.iterepi.view.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.MethodsListAdapter;
import com.example.iterepi.controller.login.MainController;
import com.example.iterepi.controller.user.PaymentMethodsController;

public class PaymentMethodsActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private ListView listMethodsLV;
    private ImageButton addMethodBtn;

    private PaymentMethodsController controller;
    private MethodsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        backBtn = findViewById(R.id.backBtn);
        listMethodsLV = findViewById(R.id.listMethodsLV);
        addMethodBtn = findViewById(R.id.addMethodBtn);

        controller = new PaymentMethodsController(this);
        adapter = new MethodsListAdapter(this);
        listMethodsLV.setAdapter(adapter);
    }

    public MethodsListAdapter getAdapter() {
        return adapter;
    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(ImageButton backBtn) {
        this.backBtn = backBtn;
    }

    public ListView getListMethodsLV() {
        return listMethodsLV;
    }

    public void setListMethodsLV(ListView listMethodsLV) {
        this.listMethodsLV = listMethodsLV;
    }

    public ImageButton getAddMethodBtn() {
        return addMethodBtn;
    }

    public void setAddMethodBtn(ImageButton addMethodBtn) {
        this.addMethodBtn = addMethodBtn;
    }
}
