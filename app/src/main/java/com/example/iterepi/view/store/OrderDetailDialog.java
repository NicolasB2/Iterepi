package com.example.iterepi.view.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.model.Transaction;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderDetailDialog extends AppCompatActivity {

    private ImageButton closeBtn;

    private TextView idOrderTV;
    private TextView clientIdTV;
    private TextView orderValueTV;
    private TextView orderDateTV;
    private Spinner orderListSP;

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
        orderListSP = findViewById(R.id.stateListSP);

        listProductsRV = findViewById(R.id.listProductsRV);

        String [] order = new String [4];
        order[0] = Transaction.TO_PICK;
        order[1] = Transaction.TO_DELIVER;
        order[2] = Transaction.DELIVERED;
        order[3] = Transaction.PICKED_UP;


        ArrayAdapter<String> sp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, order);
        orderListSP.setAdapter(sp1);


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

    public RecyclerView getListProductsRV() {
        return listProductsRV;
    }

    public void setListProductsRV(RecyclerView listProductsRV) {
        this.listProductsRV = listProductsRV;
    }

    public Spinner getOrderStateTV() {
        return orderListSP;
    }

    public void setOrderStateTV(Spinner orderListSP) {
        this.orderListSP = orderListSP;
    }
}
