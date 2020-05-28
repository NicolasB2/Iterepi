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

    public static final String SELLER = "seller";
    public static final String BUYER = "buyer";

    private ImageButton backBtn;
    private ListView listMethodsLV;
    private ImageButton addMethodBtn;

    private PaymentMethodsController controller;
    private MethodsListAdapter adapter;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        backBtn = findViewById(R.id.backBtn);
        listMethodsLV = findViewById(R.id.listMethodsLV);
        addMethodBtn = findViewById(R.id.addMethodBtn);

        this.type = (String) getIntent().getExtras().getSerializable("type");

        controller = new PaymentMethodsController(this);
    }


    public ImageButton getBackBtn() {
        return backBtn;
    }

    public ListView getListMethodsLV() {
        return listMethodsLV;
    }

    public ImageButton getAddMethodBtn() {
        return addMethodBtn;
    }

    public PaymentMethodsController getController() {
        return controller;
    }

    public String getType() {
        return type;
    }

    public MethodsListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MethodsListAdapter adapter){
        this.adapter = adapter;
        listMethodsLV.setAdapter(adapter);
    }
}
