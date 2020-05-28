package com.example.iterepi.view.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.CartItemAdapter;
import com.example.iterepi.adapter.TransactionItemAdapter;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.model.Item;
import com.example.iterepi.model.Transaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class TransactionFragment extends Fragment {

    private View view;
    private TransactionItemAdapter adapter;
    private ArrayList<Transaction> transactions;
    private RecyclerView listHistoryRV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transactions, container, false);

        listHistoryRV = view.findViewById(R.id.listHistoryRV);
        transactions = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference()
                .child("buyers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Buyer b = dataSnapshot.getValue(Buyer.class);
                loadTransactions(b);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void loadTransactions(Buyer b) {
        if (b != null) {
            if (b.getTransactions() != null) {
                if (b.getTransactions().size() > 0) {
                    for(Map.Entry<String, Transaction> entry : b.getTransactions().entrySet()){
                        Transaction t = entry.getValue();
                        transactions.add(t);
                        adapter = new TransactionItemAdapter(transactions, this);
                        listHistoryRV.setAdapter(adapter);
                    }
                } else {
                    //0 transactions
                }
                
            } else {
                //0 transactions
            }
        } else {
            Log.e(">>>>", "CartFragment: Error loading user");
        }

    }
}
