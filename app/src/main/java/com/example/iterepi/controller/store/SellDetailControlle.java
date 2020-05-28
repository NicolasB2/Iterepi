package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.iterepi.R;
import com.example.iterepi.model.Transaction;
import com.example.iterepi.view.store.SellDetailDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SellDetailControlle implements View.OnClickListener{

    private SellDetailDialog dialog;

    public SellDetailControlle(SellDetailDialog dialog) {

        this.dialog = dialog;
        this.dialog.getCloseBtn().setOnClickListener(this);


        String idTransaction = (String) dialog.getIntent().getExtras().getSerializable("transaction");

        Query query = FirebaseDatabase.getInstance().getReference().child("transactions").child(idTransaction);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Transaction transaction = dataSnapshot.getValue(Transaction.class);

                dialog.runOnUiThread(
                        ()->{
                            if(transaction!=null){

                                dialog.getIdSellTV().setText(transaction.getId());
                                dialog.getNameClientTV().setText(transaction.getBuyerName());
                                dialog.getSellValueTV().setText("$ "+transaction.getValue());
                                dialog.getShowDateHourSaleTV().setText(transaction.getPurchaseDate());
                                dialog.getShowPlaceStoreTV().setText(transaction.getSellerName());


                            }


                        }

                );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.closeBtn:
                dialog.finish();
                break;
        }
    }
}
