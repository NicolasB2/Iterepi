package com.example.iterepi.view.store;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iterepi.R;
import com.example.iterepi.adapter.OrderIDAdapter;
import com.example.iterepi.adapter.SaleIDAdapter;
import com.example.iterepi.model.Seller;
import com.example.iterepi.model.Transaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class OrderFragment extends Fragment {

    private View view;
    private OrderIDAdapter orderItemAdapter;
    private ArrayList<String> saleIDs;
    private RecyclerView listOrderRV;
    private ImageView emptyOrderIV;
    private TextView emptyOrderTV;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store_order, container, false);
        listOrderRV = view.findViewById(R.id.listOrderRV);
        listOrderRV.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyOrderIV = view.findViewById(R.id.emptyOrder);
        emptyOrderTV = view.findViewById(R.id.emptyOrderTV);
        emptyOrderIV.setVisibility(View.GONE);
        emptyOrderTV.setVisibility(View.GONE);

        saleIDs = new ArrayList<>();

        Query query= FirebaseDatabase.getInstance().getReference().child("sellers").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Seller s = dataSnapshot.getValue(Seller.class);
                loadItems(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void loadItems(Seller s) {

        if(s!=null){
            if(s.getSalesID()!=null){

                Log.e(">>>","Id de su primer venta"+s.getSalesID());
                if(s.getSalesID().size()>0){

                    emptyOrderIV.setVisibility(View.GONE);
                    emptyOrderTV.setVisibility(View.GONE);

                    for(Map.Entry<String,String> entry : s.getSalesID().entrySet()){

                        Query query = FirebaseDatabase.getInstance().getReference().child("transactions").child(entry.getValue());
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Transaction state = dataSnapshot.getValue(Transaction.class);
                                if(!state.getState().equals(Transaction.TO_DELIVER)){

                                    String i = entry.getValue();
                                    saleIDs.add(i);
                                    orderItemAdapter = new OrderIDAdapter(saleIDs);
                                    listOrderRV.setAdapter(orderItemAdapter);

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }else{
                    emptyOrderTV.setVisibility(View.VISIBLE);
                    emptyOrderIV.setVisibility(View.VISIBLE);

                }

            }else{
                emptyOrderTV.setVisibility(View.VISIBLE);
                emptyOrderIV.setVisibility(View.VISIBLE);

            }

        }else{
            Log.e(">>>","Error: No Sale in SellFragment");
        }


    }
}
