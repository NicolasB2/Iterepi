package com.example.iterepi.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iterepi.R;
import com.google.android.material.textfield.TextInputLayout;

public class MakePurchaseActivity extends AppCompatActivity {

    private ImageView imageStoreIV;
    private TextView storeNameTV;
    private Spinner paymentMethodsSP;
    private Spinner pickUpSP;

    private TextInputLayout dateFT;
    private TextInputLayout hourFT;
    private TextInputLayout observationsFT;

    private TextView purchaseValue;
    private Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_make_purchase);
        imageStoreIV = findViewById(R.id.imageStoreIV);
        storeNameTV = findViewById(R.id.showStoreNameTV);
        paymentMethodsSP = findViewById(R.id.paymentMethodsSP);
        pickUpSP = findViewById(R.id.pickUpSP);

        dateFT = findViewById(R.id.dateTF);
        hourFT = findViewById(R.id.hourTF);
        observationsFT = findViewById(R.id.observationsTF);

        purchaseValue = findViewById(R.id.purchaseValueTV);
        payBtn = findViewById(R.id.payBtn);


    }

    public ImageView getImageStoreIV() {
        return imageStoreIV;
    }

    public void setImageStoreIV(ImageView imageStoreIV) {
        this.imageStoreIV = imageStoreIV;
    }

    public TextView getStoreNameTV() {
        return storeNameTV;
    }

    public void setStoreNameTV(TextView storeNameTV) {
        this.storeNameTV = storeNameTV;
    }

    public Spinner getPaymentMethodsSP() {
        return paymentMethodsSP;
    }

    public void setPaymentMethodsSP(Spinner paymentMethodsSP) {
        this.paymentMethodsSP = paymentMethodsSP;
    }

    public Spinner getPickUpSP() {
        return pickUpSP;
    }

    public void setPickUpSP(Spinner pickUpSP) {
        this.pickUpSP = pickUpSP;
    }

    public TextInputLayout getDateFT() {
        return dateFT;
    }

    public void setDateFT(TextInputLayout dateFT) {
        this.dateFT = dateFT;
    }

    public TextInputLayout getHourFT() {
        return hourFT;
    }

    public void setHourFT(TextInputLayout hourFT) {
        this.hourFT = hourFT;
    }

    public TextInputLayout getObservationsFT() {
        return observationsFT;
    }

    public void setObservationsFT(TextInputLayout observationsFT) {
        this.observationsFT = observationsFT;
    }

    public TextView getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(TextView purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public Button getPayBtn() {
        return payBtn;
    }

    public void setPayBtn(Button payBtn) {
        this.payBtn = payBtn;
    }
}
