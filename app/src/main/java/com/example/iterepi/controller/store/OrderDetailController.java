package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.iterepi.R;

import com.example.iterepi.model.Transaction;
import com.example.iterepi.view.store.OrderDetailDialog;
import com.example.iterepi.view.store.SaleActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.google.firebase.database.ValueEventListener;

import java.security.interfaces.DSAKey;

public class OrderDetailController implements View.OnClickListener {

    private OrderDetailDialog dialog;


    public OrderDetailController(OrderDetailDialog dialog) {
        this.dialog = dialog;
        this.dialog.getUpdateStateBtn().setOnClickListener(this);
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

                                dialog.getIdOrderTV().setText(transaction.getId());
                                dialog.getClientNameTV().setText(transaction.getBuyerName());
                                dialog.getOrderValueTV().setText("$ "+transaction.getValue());
                                dialog.getOrderDateTV().setText(transaction.getPurchaseDate());

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

        Intent i;

        switch (v.getId()){
            case R.id.updateStateBtn:

                String idTransaction = (String) dialog.getIntent().getExtras().getSerializable("transaction");

                Query query = FirebaseDatabase.getInstance().getReference().child("transactions").child(idTransaction);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Transaction transaction = dataSnapshot.getValue(Transaction.class);

                        dialog.runOnUiThread(
                                ()->{
                                    if(transaction!=null){
                                        transaction.setState(dialog.getOrderListSP().getSelectedItem().toString());

                                        FirebaseDatabase.getInstance().getReference().child("transactions").child(idTransaction).setValue(transaction);

                                    }


                                }

                        );

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                i = new Intent(dialog, SaleActivity.class);
                dialog.startActivity(i);
                dialog.finish();
                break;

            case R.id.closeBtn:
                dialog.finish();
            break;
        }
    }
}
