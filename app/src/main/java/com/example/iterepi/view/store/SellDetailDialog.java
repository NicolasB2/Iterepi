package com.example.iterepi.view.store;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.controller.store.SellDetailControlle;

public class SellDetailDialog extends AppCompatActivity {

    private TextView idSellTV;
    private TextView nameClientTV;
    private TextView sellValueTV;
    private TextView showDateHourSaleTV;
    private TextView showPlaceStoreTV;
    private ImageButton closeBtn;
    private RecyclerView listProductsSellRV;

    private SellDetailControlle controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sell_detail);

        idSellTV = findViewById(R.id.idSellTV);
        nameClientTV = findViewById(R.id.nameClientTV);
        sellValueTV = findViewById(R.id.sellValueTV);
        showDateHourSaleTV = findViewById(R.id.showDateHourSaleTV);
        showPlaceStoreTV = findViewById(R.id.showPlaceStoreTV);
        listProductsSellRV = findViewById(R.id.listProductsSellRV);
        closeBtn = findViewById(R.id.closeBtn);

        this.controller = new SellDetailControlle(this);

    }

    public ImageButton getCloseBtn() {
        return closeBtn;
    }

    public TextView getIdSellTV() {
        return idSellTV;
    }

    public void setIdSellTV(TextView idSellTV) {
        this.idSellTV = idSellTV;
    }

    public TextView getNameClientTV() {
        return nameClientTV;
    }

    public void setNameClientTV(TextView nameClientTV) {
        this.nameClientTV = nameClientTV;
    }

    public TextView getSellValueTV() {
        return sellValueTV;
    }

    public void setSellValueTV(TextView sellValueTV) {
        this.sellValueTV = sellValueTV;
    }

    public TextView getShowDateHourSaleTV() {
        return showDateHourSaleTV;
    }

    public void setShowDateHourSaleTV(TextView showDateHourSaleTV) {
        this.showDateHourSaleTV = showDateHourSaleTV;
    }

    public TextView getShowPlaceStoreTV() {
        return showPlaceStoreTV;
    }

    public void setShowPlaceStoreTV(TextView showPlaceStoreTV) {
        this.showPlaceStoreTV = showPlaceStoreTV;
    }

    public RecyclerView getListProductsSellRV() {
        return listProductsSellRV;
    }

    public void setListProductsSellRV(RecyclerView listProductsSellRV) {
        this.listProductsSellRV = listProductsSellRV;
    }
}
