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
import com.example.iterepi.controller.store.OrderDetailController;
import com.example.iterepi.model.Transaction;

public class OrderDetailDialog extends AppCompatActivity {

    private ImageButton closeBtn;
    private Button updateStateBtn;

    private TextView idOrderTV;
    private TextView clientNameTV;
    private TextView orderValueTV;
    private TextView orderDateTV;
    private Spinner orderListSP;

    private RecyclerView listProductsRV;

    private OrderDetailController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_order_detail);


        closeBtn = findViewById(R.id.closeBtn);
        updateStateBtn = findViewById(R.id.updateStateBtn);
        idOrderTV = findViewById(R.id.idOrderTV);
        clientNameTV = findViewById(R.id.clientNameTV);
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

        this.controller = new OrderDetailController(this);

    }

    public Button getUpdateStateBtn() {
        return updateStateBtn;
    }

    public void setUpdateStateBtn(Button updateStateBtn) {
        this.updateStateBtn = updateStateBtn;
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

    public TextView getClientNameTV() {
        return clientNameTV;
    }

    public void setClientNameTV(TextView clientNameTV) {
        this.clientNameTV = clientNameTV;
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

    public Spinner getOrderListSP() {
        return orderListSP;
    }
}
