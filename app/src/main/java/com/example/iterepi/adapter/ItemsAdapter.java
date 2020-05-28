package com.example.iterepi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Item;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeeProductActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemsAdapter extends BaseAdapter {

    private ArrayList<Item> items;
    private SeeCategoryActivity activity;

    public ItemsAdapter(SeeCategoryActivity activity, HashMap<String, Item> items) {
        this.activity = activity;
        this.items = new ArrayList<>();
        if(items!=null){
            for (String id:items.keySet()){
                this.items.add(items.get(id));
            }
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
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
        ImageView photo = row.findViewById(R.id.photo);


        Item item = items.get(position);
        if(item!=null){

            itemNameTV.setText(item.getName());
            itemPriceTV.setText(item.getPrice()+"");
            itemInventoryTV.setText(item.getQuantity()+"");

            if (item.getPhoto() != null) {

                Glide.with(itemInventoryTV).load(item.getPhoto()).centerCrop().into(photo);

            }

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(row.getContext(), SeeProductActivity.class);
                    i.putExtra("placeId",activity.getPlaceId());
                    i.putExtra("categoryId",activity.getCategoryId());
                    i.putExtra("itemId",item.getId());
                    row.getContext().startActivity(i);
                    ((Activity)row.getContext()).finish();
                }
            });
        }

        return row;
    }
}
