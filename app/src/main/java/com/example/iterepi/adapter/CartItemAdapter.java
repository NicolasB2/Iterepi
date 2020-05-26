package com.example.iterepi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.model.Item;


import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Item> cartItems;
    private View.OnClickListener listener;

    public CartItemAdapter(ArrayList<Item> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nameProduct = cartItems.get(position).getName();
        String price = "Precio: " + cartItems.get(position).getPrice();
        String quantity = "Cantidad: " + cartItems.get(position).getQuantity();
        //TODO: Image missing

        holder.nameProductTV.setText(nameProduct);
        holder.priceTV.setText(price);
        holder.quantityTV.setText(quantity);
        //TODO: Image missing
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameProductTV, priceTV, quantityTV;
        private ImageButton closeBtn, plusBtn, minusBtn;
        private ImageView productIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameProductTV = itemView.findViewById(R.id.nameProductTV);
            priceTV = itemView.findViewById(R.id.priceTV);
            quantityTV = itemView.findViewById(R.id.quantityTV);
            closeBtn = itemView.findViewById(R.id.closeBtn);
            plusBtn = itemView.findViewById(R.id.plusBtn);
            minusBtn = itemView.findViewById(R.id.minusBtn);
            productIV = itemView.findViewById(R.id.productIV);

            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),"CLOSE",Toast.LENGTH_SHORT).show();
                }
            });

            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            minusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
