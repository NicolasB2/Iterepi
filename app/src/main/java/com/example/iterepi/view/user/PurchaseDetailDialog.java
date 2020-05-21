package com.example.iterepi.view.user;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;

public class PurchaseDetailDialog extends AppCompatActivity {


    private TextView idPurchaseTV;
    private TextView showPurchaseTV;
    private TextView showStateTV;
    private TextView showDateHourPurchaseTV;
    private TextView showDateHourReceivedTV;
    private TextView showPlaceTV;
    private RecyclerView listProductsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_purchase_detail);

        idPurchaseTV = findViewById(R.id.idOrderTV);
        showPurchaseTV = findViewById(R.id.purchaseNameTV);
        showStateTV = findViewById(R.id.purchasePriceTV);
        showDateHourPurchaseTV = findViewById(R.id.purchaseQuantityTV);
        showDateHourReceivedTV = findViewById(R.id.purchaseDescriptionTV);
        showPlaceTV = findViewById(R.id.CategoryTV);
        listProductsRV = findViewById(R.id.listProductsRV);
    }

    public TextView getIdPurchaseTV() {
        return idPurchaseTV;
    }

    public void setIdPurchaseTV(TextView idPurchaseTV) {
        this.idPurchaseTV = idPurchaseTV;
    }

    public TextView getShowPurchaseTV() {
        return showPurchaseTV;
    }

    public void setShowPurchaseTV(TextView showPurchaseTV) {
        this.showPurchaseTV = showPurchaseTV;
    }

    public TextView getShowStateTV() {
        return showStateTV;
    }

    public void setShowStateTV(TextView showStateTV) {
        this.showStateTV = showStateTV;
    }

    public TextView getShowDateHourPurchaseTV() {
        return showDateHourPurchaseTV;
    }

    public void setShowDateHourPurchaseTV(TextView showDateHourPurchaseTV) {
        this.showDateHourPurchaseTV = showDateHourPurchaseTV;
    }

    public TextView getShowDateHourReceivedTV() {
        return showDateHourReceivedTV;
    }

    public void setShowDateHourReceivedTV(TextView showDateHourReceivedTV) {
        this.showDateHourReceivedTV = showDateHourReceivedTV;
    }

    public TextView getShowPlaceTV() {
        return showPlaceTV;
    }

    public void setShowPlaceTV(TextView showPlaceTV) {
        this.showPlaceTV = showPlaceTV;
    }

    public RecyclerView getListProductsRV() {
        return listProductsRV;
    }

    public void setListProductsRV(RecyclerView listProductsRV) {
        this.listProductsRV = listProductsRV;
    }
}
