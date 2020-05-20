package com.example.iterepi.view.store;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;

public class SellDetailDialog extends AppCompatActivity {

    private TextView idSellTV;
    private TextView nameClientTV;
    private TextView idClientTV;
    private TextView sellValueTV;
    private TextView showDateHourSaleTV;
    private TextView showDateHourDeliveredTV;
    private TextView showPlaceStoreTV;
    private RecyclerView listProductsSellRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sell_detail);

        idSellTV = findViewById(R.id.idSellTV);
        nameClientTV = findViewById(R.id.nameClientTV);
        idClientTV = findViewById(R.id.idclientTV);
        sellValueTV = findViewById(R.id.sellValueTV);
        showDateHourSaleTV = findViewById(R.id.showDateHourSaleTV);
        showDateHourDeliveredTV = findViewById(R.id.showDateHourDelivered);
        showPlaceStoreTV = findViewById(R.id.showPlaceStoreTV);
        listProductsSellRV = findViewById(R.id.listProductsSellRV);
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

    public TextView getIdClientTV() {
        return idClientTV;
    }

    public void setIdClientTV(TextView idClientTV) {
        this.idClientTV = idClientTV;
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

    public TextView getShowDateHourDeliveredTV() {
        return showDateHourDeliveredTV;
    }

    public void setShowDateHourDeliveredTV(TextView showDateHourDeliveredTV) {
        this.showDateHourDeliveredTV = showDateHourDeliveredTV;
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
