package com.example.iterepi.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iterepi.R;
import com.example.iterepi.model.Place;
import com.example.iterepi.view.store.AddCategoryDialog;

import java.util.ArrayList;
import java.util.List;

public class PlacesAdapter extends BaseAdapter {

    private List<Place> places;

    public PlacesAdapter() {
        places = new ArrayList<>();
    }

    public void addPlace(Place place){
        places.add(place);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int position) {
        return places.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.place_row,null,false);

        try {
            TextView placeNameTV = row.findViewById(R.id.placeNameTV);
            TextView placeLocationTV = row.findViewById(R.id.placeLocationTV);

            placeNameTV.setText(places.get(position).getName());
            placeLocationTV.setText(places.get(position).getLocation());

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(row.getContext(), AddCategoryDialog.class);
                    i.putExtra("place",places.get(position));
                    row.getContext().startActivity(i);
                }
            });
        }catch (Exception e){
            Log.e(">>>","error in trackAdapter");
        }
        return row;
    }
}
