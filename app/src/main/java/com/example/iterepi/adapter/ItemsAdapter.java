package com.example.iterepi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.model.Item;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeeProductActivity;

public class ItemsAdapter extends BaseAdapter {

    private Item[] items;
    private SeeCategoryActivity activity;

    public ItemsAdapter(SeeCategoryActivity activity, Item[] items) {
        this.activity = activity;
        if(items == null){
            this.items = new Item[0];
        }else{
            this.items = items;
        }
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_row,null,false);

        TextView itemNameTV = row.findViewById(R.id.itemNameTV);
        TextView itemPriceTV = row.findViewById(R.id.itemPriceTV);
        TextView itemInventoryTV = row.findViewById(R.id.itemInventoryTV);

        Item item = items[position];
        if(item!=null){

            itemNameTV.setText(item.getName());
            itemPriceTV.setText(item.getPrice()+"");
            itemInventoryTV.setText(item.getQuantity()+"");

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(row.getContext(), SeeProductActivity.class);
                    i.putExtra("placePosition",activity.getPlacePosition());
                    i.putExtra("categoryPosition",activity.getCategoryPosition());
                    i.putExtra("itemPosition",position);
                    row.getContext().startActivity(i);
                }
            });
        }

        return row;
    }
}
