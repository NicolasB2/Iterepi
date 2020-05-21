package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.iterepi.R;

import org.w3c.dom.Text;

public class OrderDetailDialog extends AppCompatActivity {

    private ImageButton closeBtn;

    private TextView idOrderTV;
    private TextView clientIdTV;
    private TextView orderValueTV;
    private TextView orderDateTV;
    private TextView orderStateTV;

    private RecyclerView listProductsRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_order_detail);


        closeBtn = findViewById(R.id.closeBtn);

        idOrderTV = findViewById(R.id.idOrderTV);
        clientIdTV = findViewById(R.id.clientIdTV);
        orderValueTV = findViewById(R.id.orderValueTV);
        orderDateTV = findViewById(R.id.orderDateTV);
        orderStateTV = findViewById(R.id.orderStateTV);

        listProductsRV = findViewById(R.id.listProductsRV);


    }

    public ImageButton getCloseBtn() {
        return closeBtn;
    }

    public void setCloseBtn(ImageButton closeBtn) {
        this.closeBtn = closeBtn;
    }

    public TextView getIdOrderTV() {
        return idOrderTV;
    }

    public void setIdOrderTV(TextView idOrderTV) {
        this.idOrderTV = idOrderTV;
    }

    public TextView getClientIdTV() {
        return clientIdTV;
    }

    public void setClientIdTV(TextView clientIdTV) {
        this.clientIdTV = clientIdTV;
    }

    public TextView getOrderValueTV() {
        return orderValueTV;
    }

    public void setOrderValueTV(TextView orderValueTV) {
        this.orderValueTV = orderValueTV;
    }

    public TextView getOrderDateTV() {
        return orderDateTV;
    }

    public void setOrderDateTV(TextView orderDateTV) {
        this.orderDateTV = orderDateTV;
    }

    public TextView getOrderStateTV() {
        return orderStateTV;
    }

    public void setOrderStateTV(TextView orderStateTV) {
        this.orderStateTV = orderStateTV;
    }

    public RecyclerView getListProductsRV() {
        return listProductsRV;
    }

    public void setListProductsRV(RecyclerView listProductsRV) {
        this.listProductsRV = listProductsRV;
    }
}
