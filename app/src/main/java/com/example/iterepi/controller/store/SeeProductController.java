package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.model.Item;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeeProductActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

public class SeeProductController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private SeeProductActivity activity;
    private HTTPSWebUtilDomi utilDomi;

    public SeeProductController(SeeProductActivity activity) {
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        this.utilDomi.setListener(this);
        this.activity.getBackBtn3().setOnClickListener(this);
        this.activity.getUpdateDataBtn().setOnClickListener(this);

        if(activity.getItem()==null){
            loadItem();
        }
    }

    private void loadItem() {
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id
                            +"/places/"+activity.getPlacePosition()
                            +"/categories/"+activity.getCategoryPosition()
                            +"/items/"+activity.getItemPosition()+".json";
                    utilDomi.GETrequest(1,request);
                }
        ).start();
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.backBtn:
                activity.finish();
                break;

            case R.id.updateDataBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String name = activity.getNameProductTF().getEditText().getText().toString();
                String price = activity.getPriceProductTF().getEditText().getText().toString();
                String inventory = activity.getInventoryQualityTF().getEditText().getText().toString();
                String description = activity.getDescriptionProductTF().getEditText().getText().toString();

                if (name.equals("")) {
                    Toast.makeText(activity, activity.getString(R.string.forgot_something) + " " + activity.getString(R.string.name), Toast.LENGTH_LONG).show();
                    break;
                }
                if (price.equals("")) {
                    Toast.makeText(activity, activity.getString(R.string.forgot_something) + " " + activity.getString(R.string.price), Toast.LENGTH_LONG).show();
                    break;
                }
                if (inventory.equals("")) {
                    Toast.makeText(activity, activity.getString(R.string.forgot_something) + " " + activity.getString(R.string.inventory_quantity), Toast.LENGTH_LONG).show();
                    break;
                }
                if (description.equals("")) {
                    Toast.makeText(activity, activity.getString(R.string.forgot_something) + " " + activity.getString(R.string.description), Toast.LENGTH_LONG).show();
                    break;
                } else {

                    activity.getItem().setName(name);
                    activity.getItem().setDescription(description);

                    try {
                        activity.getItem().setPrice(Double.parseDouble(price));
                    } catch (Exception e) {
                        Toast.makeText(activity, "Error: " + activity.getString(R.string.price), Toast.LENGTH_LONG).show();
                        break;
                    }

                    try {
                        activity.getItem().setQuantity(Integer.parseInt(inventory));
                    } catch (Exception e) {
                        Toast.makeText(activity, "Error: " + activity.getString(R.string.price), Toast.LENGTH_LONG).show();
                        break;
                    }

                    new Thread(
                            () -> {
                                String request = "https://iterepi.firebaseio.com/sellers/" + user_id
                                        + "/places/" + activity.getPlacePosition()
                                        + "/categories/" + activity.getCategoryPosition()
                                        + "/items/" + activity.getItemPosition() + ".json";
                                Gson gson = new Gson();
                                String json = gson.toJson(activity.getItem());
                                utilDomi.PUTrequest(1, request, json);
                            }
                    ).start();

                    Toast.makeText(activity, activity.getString(R.string.update_successful), Toast.LENGTH_LONG).show();
                    Intent s = new Intent(activity, SeeCategoryActivity.class);
                    s.putExtra("placePosition", activity.getPlacePosition());
                    s.putExtra("categoryPosition", activity.getCategoryPosition());
                    activity.startActivity(s);
                    activity.finish();

                }
                break;
        }

    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID) {
            case 1:
                Gson gson = new Gson();
                Item item = gson.fromJson(response, Item.class);

                activity.runOnUiThread(
                        ()->{
                            if(item!=null){
                                activity.setItem(item);
                                activity.getItemName().setText(item.getName());
                                activity.getNameProductTF().getEditText().setText(item.getName());
                                activity.getPriceProductTF().getEditText().setText(item.getPrice()+"");
                                activity.getInventoryQualityTF().getEditText().setText(item.getQuantity()+"");
                                activity.getDescriptionProductTF().getEditText().setText(item.getDescription());
                            }
                        }
                );
                break;
        }
    }
}
