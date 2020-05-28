package com.example.iterepi.controller.user;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iterepi.R;
import com.example.iterepi.adapter.CartItemAdapter;
import com.example.iterepi.adapter.MethodsListAdapter;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Card;
import com.example.iterepi.model.Cart;
import com.example.iterepi.model.Seller;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.util.UtilDomi;
import com.example.iterepi.view.user.AddPaymentMethodActivity;
import com.example.iterepi.view.user.PaymentMethodsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PaymentMethodsController  implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener {

    private  PaymentMethodsActivity activity;
    private HTTPSWebUtilDomi utilDomi;

    public PaymentMethodsController (PaymentMethodsActivity activity){
        this.activity = activity;
        this.activity.getAddMethodBtn().setOnClickListener(this);
        this.activity.getBackBtn().setOnClickListener(this);
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);

        String id = FirebaseAuth.getInstance().getUid();

        if(this.activity.getType().equals(activity.SELLER)){
            loadSellerCards(id);
        }else if (this.activity.getType().equals(activity.BUYER)){
            loadBuyerCards(id);
        }

    }

    private void loadSellerCards(String id) {
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("sellers").child(id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Seller seller = dataSnapshot.getValue(Seller.class);
                if(seller!=null){
                    loadCards(seller.getCards());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadBuyerCards(String id) {
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("buyers").child(id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Buyer buyer = dataSnapshot.getValue(Buyer.class);
                if(buyer!=null){
                    loadCards(buyer.getCards());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadCards(HashMap<String, Card> cards){
        activity.runOnUiThread(
                () -> {
                    if (cards != null) {
                        MethodsListAdapter a = new MethodsListAdapter(cards);
                        activity.setAdapter(a);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.addMethodBtn:
                i = new Intent(activity, AddPaymentMethodActivity.class);
                if(activity.getAdapter()!=null){
                    i.putExtra("cards",(Serializable) activity.getAdapter().getListOfCards());
                }
                i.putExtra("cards", new ArrayList<Card>());
                i.putExtra("type",activity.getType());
                activity.startActivity(i);
                activity.finish();
                break;
            case R.id.backBtn:
                activity.finish();
                break;
        }
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID){
            case 1:
                Gson gson = new Gson();
                Card[] cards = gson.fromJson(response, Card[].class);

                activity.runOnUiThread(
                        ()->{
                            if(cards!=null){

                                for(int i=0; i<cards.length; i++){
                                    Card c = cards[i];
                                    activity.getAdapter().addCard(c);

                                }
                            }

                        }


                );



        }



    }
}
