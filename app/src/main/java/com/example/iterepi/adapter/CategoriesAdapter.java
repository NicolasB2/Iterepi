package com.example.iterepi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Place;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeePlaceActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoriesAdapter extends BaseAdapter {

    private ArrayList<Category>categories;
    private SeePlaceActivity activity;

    public CategoriesAdapter(SeePlaceActivity activity, HashMap<String,Category> categories) {
        this.activity = activity;
        this.categories = new ArrayList<>();

        if(categories!=null){
            for (String id:categories.keySet()){
                this.categories.add(categories.get(id));
            }
        }
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.category_row,null,false);

            TextView categoryNameTV = row.findViewById(R.id.categoryNameTV);
            TextView categoryItemsTV = row.findViewById(R.id.itemPriceTV);

            Category category = categories.get(position);
            if(category!=null){

                categoryNameTV.setText(category.getName());
                if(category.getItems()==null){
                    categoryItemsTV.setText(0+"");
                }else{
                    categoryItemsTV.setText(category.getItems().size()+"");
                }


                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(row.getContext(), SeeCategoryActivity.class);
                        i.putExtra("placeId",activity.getPlaceId());
                        i.putExtra("categoryId",category.getId());
                        row.getContext().startActivity(i);
                        ((Activity)row.getContext()).finish();
                    }
                });
            }

        return row;
    }

}
