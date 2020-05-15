package com.example.iterepi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.iterepi.R;

public class ItemProductUser extends AppCompatActivity {

    private TextView showNameProductTV;
    private TextView showPriceTV;
    private TextView showQualityTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_product_user);

        showNameProductTV = findViewById(R.id.showNameProductTV);
        showPriceTV = findViewById(R.id.showPriceTV);
        showQualityTV = findViewById(R.id.showQualityTV);

    }

    public TextView getShowNameProductTV() {
        return showNameProductTV;
    }

    public void setShowNameProductTV(TextView showNameProductTV) {
        this.showNameProductTV = showNameProductTV;
    }

    public TextView getShowPriceTV() {
        return showPriceTV;
    }

    public void setShowPriceTV(TextView showPriceTV) {
        this.showPriceTV = showPriceTV;
    }

    public TextView getShowQualityTV() {
        return showQualityTV;
    }

    public void setShowQualityTV(TextView showQualityTV) {
        this.showQualityTV = showQualityTV;
    }
}
