package com.example.iterepi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iterepi.R;

public class PurchaseDetailActivity extends AppCompatActivity {


    private TextView idPurchaseTV;
    private TextView showPurchaseTV;
    private TextView showStateTV;
    private TextView showDateHourPurchaseTV;
    private TextView showDateHourReceivedTV;
    private TextView showPlaceTV;
    private ListView listProductsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);

        idPurchaseTV = findViewById(R.id.idPurchaseTV);
        showPurchaseTV = findViewById(R.id.showPurchaseTV);
        showStateTV = findViewById(R.id.showStateTV);
        showDateHourPurchaseTV = findViewById(R.id.showDateHourPurchaseTV);
        showDateHourReceivedTV = findViewById(R.id.showDateHourReceivedTV);
        showPlaceTV = findViewById(R.id.showPlaceTV);
        listProductsLV = findViewById(R.id.listProductsLV);
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

    public ListView getListProductsLV() {
        return listProductsLV;
    }

    public void setListProductsLV(ListView listProductsLV) {
        this.listProductsLV = listProductsLV;
    }
}
