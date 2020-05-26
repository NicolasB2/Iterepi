package com.example.iterepi.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.view.store.StoreHomeActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends BaseAdapter {

    private Category[] categories;

    public CategoriesAdapter(Category[] categories) {

        this.categories = categories;
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

        try {
            TextView categoryNameTV = row.findViewById(R.id.categoryNameTV);
            TextView categoryItemsTV = row.findViewById(R.id.categoryItemsTV);

            categoryNameTV.setText(categories[position].getName());
            categoryItemsTV.setText(categories[position].getItems().length+"");

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(row.getContext(), StoreHomeActivity.class);
                    i.putExtra("category",categories[position]);
                    row.getContext().startActivity(i);
                }
            });
        }catch (Exception e){
            Log.e(">>>","error in trackAdapter");
        }
        return row;
    }

}
