package com.example.iterepi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Transaction;
import com.example.iterepi.view.store.OrderDetailDialog;
import com.example.iterepi.view.store.SellDetailDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderIDAdapter extends RecyclerView.Adapter<OrderIDAdapter.ViewHolder> implements View.OnClickListener  {



    private ArrayList<String> saleIDs;
    private View.OnClickListener listener;

    public OrderIDAdapter(ArrayList<String> saleIDs) {
        this.saleIDs = saleIDs;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_order, parent, false);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Query query = FirebaseDatabase.getInstance().getReference().child("transactions").child(saleIDs.get(position));
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Transaction transaction = dataSnapshot.getValue(Transaction.class);
                if(transaction!=null){
                    if(!transaction.getState().equals(Transaction.TO_DELIVER)){

                        holder.nameClientTV.setText(transaction.getBuyerName());
                        holder.orderID.setText("Pedido ID: "+transaction.getId());
                        holder.valueTV.setText("Valor: "+transaction.getValue());
                        holder.stateTV.setText("State: "+transaction.getState());

                        Glide.with(holder.itemView).load(transaction.getBuyerPhoto()).centerCrop().into(holder.imageClientIV);
                        holder.itemView.setOnClickListener(
                                (v)->{
                                    Intent i = new Intent( holder.itemView.getContext(), OrderDetailDialog.class);
                                    i.putExtra("transaction", transaction.getId());
                                    holder.itemView.getContext().startActivity(i);
                                }
                        );


                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return saleIDs.size();
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    //VIEWHOLDER CLASS

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameClientTV, valueTV, stateTV, orderID;
        private ImageView imageClientIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderID = itemView.findViewById(R.id.saleIdTV);
            nameClientTV = itemView.findViewById(R.id.nameClientTV);
            valueTV = itemView.findViewById(R.id.saleValueTV);
            stateTV = itemView.findViewById(R.id.saleDateTV);
            imageClientIV = itemView.findViewById(R.id.imagePerfilClientIV);


        }
    }
}
