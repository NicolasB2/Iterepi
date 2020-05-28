package com.example.iterepi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.model.Item;
import com.example.iterepi.model.Transaction;
import com.example.iterepi.view.user.TransactionFragment;

import java.util.ArrayList;

public class TransactionItemAdapter extends RecyclerView.Adapter<TransactionItemAdapter.ViewHolder> implements  View.OnClickListener{

    private TransactionFragment fragment;
    private ArrayList<Transaction> transactions;
    private View.OnClickListener listener;

    public TransactionItemAdapter( ArrayList<Transaction> transactions,TransactionFragment fragment) {
        this.fragment = fragment;
        this.transactions = transactions;
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

        String id = transactions.get(position).getId();
        String price = transactions.get(position).getCart().getTotalCart() + "";
        String state = transactions.get(position).getState();
        String date = transactions.get(position).getPurchaseDate();

        holder.purchaseIdTV.setText(id);
        holder.purchasePriceTV.setText(price);
        holder.purchaseStateTV.setText(state);
        holder.purchaseDateTV.setText(date);
    }

    @Override
    public int getItemCount() {
        return transactions != null ? transactions.size() : 0;
    }

    @Override
    public void onClick(View v) {

    }

//-----------------------------------------------------------------------------------------------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView purchaseIdTV, purchasePriceTV, purchaseStateTV, purchaseDateTV;
        private ImageView history_productIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            purchaseIdTV = itemView.findViewById(R.id.purchaseIdTV);
            purchasePriceTV = itemView.findViewById(R.id.purchasePriceTV);
            purchaseStateTV = itemView.findViewById(R.id.purchaseStateTV);
            purchaseDateTV = itemView.findViewById(R.id.purchaseDateTV);
            history_productIV  = itemView.findViewById(R.id.history_productIV);
        }
    }
}
