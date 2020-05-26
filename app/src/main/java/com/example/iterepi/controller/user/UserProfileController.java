package com.example.iterepi.controller.user;

import androidx.annotation.NonNull;

import com.example.iterepi.model.Buyer;
import com.example.iterepi.view.user.UserProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfileController {

    private UserProfileActivity activity;

    public UserProfileController() {
    }

    public UserProfileController(UserProfileActivity activity) {
        this.activity = activity;
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("buyers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Buyer b = dataSnapshot.getValue(Buyer.class);
                setUserInfo(b);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setUserInfo(Buyer buyer){
        activity.getNameTV().setText(buyer.getName());
        activity.getBirthTV().setText(buyer.getBirthday());
        activity.getEmailTV().setText(buyer.getEmail());
        activity.getGenderTV().setText(buyer.getGender()==0 ? "Femenino" : "Masculino");
        activity.getIdTV().setText(buyer.getIdentification());
        activity.getPhoneNumberTV().setText("N/A");
    }
}

