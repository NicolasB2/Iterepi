package com.example.iterepi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeePlaceActivity;

public class CategoriesAdapter extends BaseAdapter {

    private Category[] categories;
    private SeePlaceActivity activity;

    public CategoriesAdapter(SeePlaceActivity activity, Category[] categories) {
        this.activity = activity;
        if(categories == null){
            this.categories = new Category[0];
        }else{
            this.categories = categories;
        }

    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int position) {
        return categories[position];
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

            Category category = categories[position];
            if(category!=null){

                categoryNameTV.setText(category.getName());
                categoryItemsTV.setText(category.numItems()+"");

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(row.getContext(), SeeCategoryActivity.class);
                        i.putExtra("placePosition",activity.getPlaceId());
                        i.putExtra("categoryPosition",position);
                        row.getContext().startActivity(i);
                    }
                });
            }

        return row;
    }

}
