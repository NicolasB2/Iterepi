package com.example.iterepi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iterepi.R;
import com.example.iterepi.model.Seller;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.ViewHolderTrack> implements View.OnClickListener {

    private ArrayList<Seller> sellers;
    private View.OnClickListener listener;

    public SellerAdapter() {

        sellers = new ArrayList<>();


    }

    @NonNull
    @Override
    public SellerAdapter.ViewHolderTrack onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_row, null, false);
        view.setOnClickListener(this);
        return new ViewHolderTrack(view);
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {

            listener.onClick(v);

        }

    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTrack holder, int position) {

        holder.name.setText(sellers.get(position).getName());
        holder.putImage(sellers, position);


    }

    @Override
    public int getItemCount() {
        return sellers.size();
    }


    public class ViewHolderTrack extends RecyclerView.ViewHolder {

        CircleImageView photo;
        TextView name;


        public ViewHolderTrack(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.name);


        }

        public void putImage(ArrayList<Seller> sellers, int pos) {

            Glide.with(name).load(sellers.get(pos).getLogo()).centerCrop().into(photo);

        }


    }
}
