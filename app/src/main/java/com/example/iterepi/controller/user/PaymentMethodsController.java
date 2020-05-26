package com.example.iterepi.controller.user;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iterepi.R;
import com.example.iterepi.adapter.MethodsListAdapter;
import com.example.iterepi.model.Card;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.util.UtilDomi;
import com.example.iterepi.view.user.AddPaymentMethodActivity;
import com.example.iterepi.view.user.PaymentMethodsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.io.Serializable;

public class PaymentMethodsController  implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener {

    private  PaymentMethodsActivity activity;
    private HTTPSWebUtilDomi utilDomi;

    public PaymentMethodsController (PaymentMethodsActivity activity){
        this.activity = activity;
        this.activity.getAddMethodBtn().setOnClickListener(this);
        this.activity.getBackBtn().setOnClickListener(this);
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);

        //Trear el listado de las tarjetas

        //iD DEL COMPRADOR
        String idBuyer = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    String result = "https://iterepi.firebaseio.com/buyers/"+idBuyer+"/cards/.json";
                    utilDomi.GETrequest(1, result);
                }
        ).start();

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.addMethodBtn:
                i = new Intent(activity, AddPaymentMethodActivity.class);
                i.putExtra("cards",(Serializable) activity.getAdapter().getListOfCards());
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
