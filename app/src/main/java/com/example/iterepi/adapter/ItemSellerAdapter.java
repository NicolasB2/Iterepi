package com.example.iterepi.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Item;
import com.example.iterepi.view.user.SectionStoreUser;

import java.util.ArrayList;

public class ItemSellerAdapter extends RecyclerView.Adapter<ItemSellerAdapter.ViewHolderTrack> implements View.OnClickListener {

    private ArrayList<Item> items;
    private View.OnClickListener listener;
    private SectionStoreUser activity;

    public ItemSellerAdapter(SectionStoreUser activity) {

        this.activity = activity;
        items = new ArrayList<>();


    }

    @NonNull
    @Override
    public ItemSellerAdapter.ViewHolderTrack onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_row, null, false);
        view.setOnClickListener(this);
        return new ViewHolderTrack(view);
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {

            listener.onClick(v);

        }

    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTrack holder, int position) {

        Item item = items.get(position);

        holder.name.setText(item.getName());

        int quantity = item.getQuantity();

        if (quantity >= 1) {
            holder.state.setText(activity.getString(R.string.available));
        } else {

            holder.state.setText(activity.getString(R.string.sold_out));
            holder.addToCart.setEnabled(false);
        }

        holder.putImage(items, position);

        holder.price.setText(item.getPrice() + "");

        holder.addToCart.setOnClickListener(v -> {

            Log.e("ADDING", "ADDING TO CART " + item.getName());

            activity.getController().addItemToCart(item, 1);


        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolderTrack extends RecyclerView.ViewHolder {

        TextView name;
        ImageView photo;
        TextView price;
        TextView state;
        Button addToCart;


        public ViewHolderTrack(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.showNameProductTV);
            photo = itemView.findViewById(R.id.photo);
            price = itemView.findViewById(R.id.showPriceProductTV);
            state = itemView.findViewById(R.id.showStateProductTV);
            addToCart = itemView.findViewById(R.id.addToCart);


        }

        public void putImage(ArrayList<Item> items, int pos) {

            Glide.with(name).load(items.get(pos).getPhoto()).centerCrop().into(photo);

        }


    }
}