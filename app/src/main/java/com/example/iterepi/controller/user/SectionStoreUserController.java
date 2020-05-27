package com.example.iterepi.controller.user;

import com.bumptech.glide.Glide;
import com.example.iterepi.model.Seller;
import com.example.iterepi.view.user.SectionStoreUser;

public class SectionStoreUserController {

    private SectionStoreUser activity;
    private Seller seller;


    public SectionStoreUserController(SectionStoreUser activity) {

        this.activity = activity;

        seller = (Seller) activity.getIntent().getExtras().get("seller");

        activity.getNameStoreTV().setText(seller.getName());

        Glide.with(activity).load(seller.getLogo()).centerCrop().into(activity.getImageLogoStoreIV());


    }

}
