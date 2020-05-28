package com.example.iterepi.adapter;

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
import com.example.iterepi.view.store.SaleFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SaleIDAdapter extends RecyclerView.Adapter<SaleIDAdapter.ViewHolder> implements View.OnClickListener  {


    SaleFragment fragment;
    private ArrayList<String> saleIDs;
    private View.OnClickListener listener;

    public SaleIDAdapter(SaleFragment fragment, ArrayList<String> saleIDs) {
        this.fragment = fragment;
        this.saleIDs = saleIDs;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_sell, parent, false);
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
                holder.nameClientTV.setText(transaction.getBuyerName());
                holder.saleID.setText("Venta ID: "+transaction.getSellerID());
                holder.valueTV.setText("Valor: "+transaction.getValue());
                holder.dateTV.setText("Date: "+transaction.getPurchaseDate());

                Glide.with(holder.itemView).load(transaction.getBuyerPhoto()).centerCrop().into(holder.imageClientIV);

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

        private TextView nameClientTV, valueTV, dateTV, saleID;
        private ImageView imageClientIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            saleID = itemView.findViewById(R.id.saleIdTV);
            nameClientTV = itemView.findViewById(R.id.nameClientTV);
            valueTV = itemView.findViewById(R.id.saleValueTV);
            dateTV = itemView.findViewById(R.id.saleDateTV);
            imageClientIV = itemView.findViewById(R.id.imagePerfilClientIV);


        }
    }
}
