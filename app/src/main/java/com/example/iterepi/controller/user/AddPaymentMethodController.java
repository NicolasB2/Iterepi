package com.example.iterepi.controller.user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;

import com.example.iterepi.R;
import com.example.iterepi.model.Card;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.user.AddPaymentMethodActivity;
import com.example.iterepi.view.user.PaymentMethodsActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddPaymentMethodController implements View.OnClickListener {

    private AddPaymentMethodActivity activity;
    private HTTPSWebUtilDomi utilDomi;
    private boolean checkCardNumber;
    private boolean checkExpirationDate;
    private boolean checkSecurityCode;
    private boolean checkNameUser;
    private boolean checkLastName;


    public AddPaymentMethodController( AddPaymentMethodActivity activity){
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        activity.getAddCardBtn().setOnClickListener(this);
        listeners();
    }



    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.addCardBtn:
                addCard();
                break;
            case R.id.closeBtn:
                activity.finish();
                break;
        }
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



            new Thread(
                    ()->{
                        Gson gson = new Gson();
                        String json = gson.toJson(card);
                        utilDomi.PUTrequest(1,"https://iterepi.firebaseio.com/buyers/"+id+"/cards/"+idCard+"/.json",json);
                    }

            ).start();

            //FirebaseDatabase.getInstance().getReference().child("buyers").child(id).child("cards").push().getKey();

            activity.finish();
        }


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
