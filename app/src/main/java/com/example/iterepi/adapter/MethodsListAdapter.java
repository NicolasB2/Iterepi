package com.example.iterepi.adapter;

import android.app.Activity;
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
import java.util.HashMap;
import java.util.List;

public class MethodsListAdapter extends BaseAdapter {

    private ArrayList<Card> listOfCards;

    public MethodsListAdapter(HashMap<String,Card>listOfCards) {
        this.listOfCards = new ArrayList<>();
        if(listOfCards!=null){
            for (String id:listOfCards.keySet()){
                this.listOfCards.add(listOfCards.get(id));
            }
        }
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
        View row = inflater.inflate(R.layout.item_payment_method,null,false);

        try {
            TextView cardNumber =  row.findViewById(R.id.numCardTV);
            cardNumber.setText("  "+listOfCards.get(position).getCardNumber());

        }catch (Exception e){
            Log.e(">>>","Error in MethodsListAdapter");
        }
        return row;
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

}
