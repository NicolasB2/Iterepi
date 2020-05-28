package com.example.iterepi.controller.user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Card;
import com.example.iterepi.model.Seller;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.user.AddPaymentMethodActivity;
import com.example.iterepi.view.user.PaymentMethodsActivity;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddPaymentMethodController implements View.OnClickListener{

    private AddPaymentMethodActivity activity;

    private boolean checkCardNumber;
    private boolean checkExpirationDate;
    private boolean checkSecurityCode;
    private boolean checkNameUser;
    private boolean checkLastName;


    public AddPaymentMethodController( AddPaymentMethodActivity activity){
        this.activity = activity;
        activity.getAddCardBtn().setOnClickListener(this);
        activity.getBackBtn().setOnClickListener(this);

        listeners();
    }



    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.addCardBtn:
                addCard();
                break;
            case R.id.backBtn:
                activity.finish();
                break;
        }
    }

    public void addCard(){

        String numberCard = activity.getCardNumberTF().getEditText().getText().toString();
        String expirationDate = activity.getExpirationDateTF().getEditText().getText().toString();
        String securityCode = activity.getSecurityCodTF().getEditText().getText().toString();
        String nameUser = activity.getNameTF().getEditText().getText().toString();
        String lastNameUser = activity.getLastNameTF().getEditText().getText().toString();

        if(numberCard.isEmpty()){
            putError(activity.getCardNumberTF(), activity.getText(R.string.empty_field).toString());
            checkCardNumber = false;
        }

        if(expirationDate.isEmpty()){
            activity.getExpirationDateTF().setHelperText(activity.getString(R.string.empty_field));
            Snackbar.make(activity.getAddCardBtn(), activity.getString(R.string.expiration_date), Snackbar.LENGTH_SHORT).show();
            checkExpirationDate = false;
        }

        if(securityCode.isEmpty()){
            putError(activity.getSecurityCodTF(), activity.getText(R.string.empty_field).toString());
            checkSecurityCode = false;
        }

        if(nameUser.isEmpty()){
            putError(activity.getNameTF(), activity.getText(R.string.empty_field).toString());
            checkNameUser = false;
        }
        if(lastNameUser.isEmpty()){
            putError(activity.getLastNameTF(), activity.getText(R.string.empty_field).toString());
            checkLastName = false;
        }

        //All data validated
        if(checkCardNumber && checkExpirationDate && checkSecurityCode && checkNameUser && checkLastName){

            //Id del comprador
            String id = FirebaseAuth.getInstance().getUid();
            String idCard = FirebaseDatabase.getInstance().getReference().child("buyers").child(id).child("cards").push().getKey();

            Card card = new Card();
            card.setIdCard(idCard);
            card.setCardNumber(numberCard);
            card.setExpirationDate(expirationDate);
            card.setSecurityCode(securityCode);
            card.setNameUser(nameUser);
            card.setLastNameUser(lastNameUser);


            if(activity.getType().equals(activity.SELLER)){
                loadSellerCards(id,card);
            }else if (activity.getType().equals(activity.BUYER)){
                loadBuyerCards(id,card);
            }

            Intent a = new Intent(activity, PaymentMethodsActivity.class);
            a.putExtra("type",activity.getType());
            activity.startActivity(a);
            activity.finish();
        }


    }

    private void loadSellerCards(String id,Card card) {
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("sellers").child(id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Seller seller = dataSnapshot.getValue(Seller.class);
                if(seller!=null){
                    FirebaseDatabase.getInstance().getReference()
                            .child("sellers").child(id)
                            .child("cards").child(card.getIdCard()).setValue(card);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadBuyerCards(String id,Card card) {
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("buyers").child(id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Buyer buyer = dataSnapshot.getValue(Buyer.class);
                if(buyer!=null){
                    FirebaseDatabase.getInstance().getReference()
                            .child("buyers").child(id)
                            .child("cards").child(card.getIdCard()).setValue(card);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void openCalendar() {

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        DatePickerDialog dpd = new DatePickerDialog(activity.getAddCardBtn().getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                int fixedMonth = month + 1;
                String dateSelected = dayOfMonth + "/" + fixedMonth + "/" + year;
                activity.getExpirationDateTF().getEditText().setText(dateSelected);

            }
        }, year, month, day);

        dpd.show();

    }

    public void putError(TextInputLayout txtLay, String error) {
        txtLay.setError(error);
    }

    public void removeError(TextInputLayout txtLay) {

        txtLay.setError("");
        txtLay.setErrorEnabled(false);

    }

    private void listeners() {

        activity.getExpirationDateTF().setEndIconOnClickListener(v -> {
            openCalendar();
        });
        activity.getExpirationDateTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t =  activity.getExpirationDateTF().getEditText().getText().toString();
                if(!t.isEmpty()){
                    activity.getExpirationDateTF().setHelperText("");
                    checkExpirationDate = true;
                }
            }
        });

        activity.getCardNumberTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getCardNumberTF().getEditText().getText().toString();
                if(!t.isEmpty()){
                    removeError(activity.getCardNumberTF());
                    checkCardNumber = true;
                }
            }
        });

        activity.getSecurityCodTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getSecurityCodTF().getEditText().getText().toString();
                if(!t.isEmpty()){
                    removeError(activity.getSecurityCodTF());
                    checkSecurityCode = true;
                }
            }
        });

        activity.getNameTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getNameTF().getEditText().getText().toString();
                if(!t.isEmpty()){
                    removeError(activity.getNameTF());
                    checkNameUser = true;
                }
            }
        });

        activity.getLastNameTF().getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String t = activity.getLastNameTF().getEditText().getText().toString();
                if(!t.isEmpty()){
                    removeError(activity.getLastNameTF());
                    checkLastName = true;
                }
            }
        });

    }

}
