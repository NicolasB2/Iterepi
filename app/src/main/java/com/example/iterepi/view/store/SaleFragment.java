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
import com.example.iterepi.adapter.SaleIDAdapter;
import com.example.iterepi.model.Seller;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class SaleFragment extends Fragment implements View.OnClickListener {

    private View view;
    private SaleIDAdapter saleItemAdapter;
    private ArrayList<String> saleIDs;
    private RecyclerView listSaleRV;
    private ImageView emptySaleIV;
    private TextView emptySaleTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_store_sell, container, false);
        listSaleRV = view.findViewById(R.id.listSaleRV);
        listSaleRV.setLayoutManager(new LinearLayoutManager(getContext()));
        emptySaleIV = view.findViewById(R.id.emptySale);
        emptySaleTV = view.findViewById(R.id.emptySaleTV);
        emptySaleIV.setVisibility(View.GONE);
        emptySaleTV.setVisibility(View.GONE);

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


    public void loadItems(Seller s){

        if(s!=null){
            if(s.getSalesID()!=null){

                Log.e(">>>","Id de su primer venta"+s.getSalesID());
                if(s.getSalesID().size()>0){
                    emptySaleIV.setVisibility(View.GONE);
                    emptySaleTV.setVisibility(View.GONE);

                    for(Map.Entry<String,String> entry : s.getSalesID().entrySet()){

                        String i = entry.getValue();
                        saleIDs.add(i);
                        saleItemAdapter = new SaleIDAdapter(saleIDs);
                        listSaleRV.setAdapter(saleItemAdapter);

                    }

                }else{
                    emptySaleTV.setVisibility(View.VISIBLE);
                    emptySaleIV.setVisibility(View.VISIBLE);

                }

            }else{
                emptySaleTV.setVisibility(View.VISIBLE);
                emptySaleIV.setVisibility(View.VISIBLE);

            }

        }else{
            Log.e(">>>","Error: No Sale in SellFragment");
        }


    }


    @Override
    public void onClick(View v) {

    }
}
