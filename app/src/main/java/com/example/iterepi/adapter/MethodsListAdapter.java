package com.example.iterepi.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.model.Card;
import com.example.iterepi.view.user.PaymentMethodsActivity;


import java.util.ArrayList;
import java.util.List;

public class MethodsListAdapter extends BaseAdapter {

    private List<Card> listOfCards;
    private PaymentMethodsActivity activity;

    public MethodsListAdapter(PaymentMethodsActivity activity) {
        this.activity = activity;
        listOfCards = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return listOfCards.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View item = inflater.inflate(R.layout.item_payment_method,null,false);

        try {
            TextView cardNumber =  item.findViewById(R.id.numCardTV);
            cardNumber.setText(listOfCards.get(position).getCardNumber());

            item.setOnClickListener(
                    (v)->{
                        Log.e(">>>","Para donde coño debo ir");
                        activity.finish();
                    }


            );

        }catch (Exception e){
            Log.e(">>>","Error in MethodsListAdapter");
        }
        return item;
    }


    public void addCard(Card card){
        listOfCards.add(card);
        notifyDataSetChanged();
    }

    public void clear(){
        listOfCards.clear();
    }

    public List<Card> getListOfCards() {
        return listOfCards;
    }

    public void setListOfCards(List<Card> listOfCards) {
        this.listOfCards = listOfCards;
    }
}
