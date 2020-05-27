package com.example.iterepi.controller.store;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.iterepi.R;
import com.example.iterepi.model.Category;
import com.example.iterepi.model.Item;
import com.example.iterepi.model.Place;
import com.example.iterepi.model.Seller;
import com.example.iterepi.util.HTTPSWebUtilDomi;
import com.example.iterepi.view.store.AddProductDialog;
import com.example.iterepi.view.store.SeeCategoryActivity;
import com.example.iterepi.view.store.SeePlaceActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.List;

public class AddProductController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener {

    private AddProductDialog activity;
    private HTTPSWebUtilDomi utilDomi;

    public static final int SEARCH_CALLBACK = 1;
    public static final int SEND_CALLBACK = 2;

    private Seller seller;

    public AddProductController(AddProductDialog activity) {
        this.activity = activity;
        this.utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        activity.getAddProductBtn().setOnClickListener(this);
        activity.getCloseBtn().setOnClickListener(this);

        loadSeller();
        loadPlacesSpinner();
    }

    private void loadPlacesSpinner(){
        activity.getPlaceOfProductSP().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                activity.runOnUiThread(
                        ()->{
                            if(seller!=null){
                                ArrayAdapter<Category> adp1 = new ArrayAdapter<Category>(activity, android.R.layout.simple_list_item_1, (List<Category>) seller.getPlaces().get(position).getCategories().values());
                                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                activity.getCategoryOfProductSP().setAdapter(adp1);
                            }
                        }
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }
    private void loadSeller() {
        String user_id = FirebaseAuth.getInstance().getUid();

        new Thread(
                ()->{
                    String request = "https://iterepi.firebaseio.com/sellers/"+user_id+"/.json";
                    utilDomi.GETrequest(SEARCH_CALLBACK,request);
                }
        ).start();
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.addProductBtn:

                String user_id = FirebaseAuth.getInstance().getUid();
                String id = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_id).child("places").push().getKey();
                String name = activity.getNameProductTF().getEditText().getText().toString();
                String price = activity.getPriceProductTF().getEditText().getText().toString();
                String inventory = activity.getInventoryQualityTF().getEditText().getText().toString();
                String description = activity.getDescriptionProductTF().getEditText().getText().toString();
                Place place = (Place) activity.getPlaceOfProductSP().getSelectedItem();
                Category category = (Category) activity.getCategoryOfProductSP().getSelectedItem();

                if(id != null){
                    if (name.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.name),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if (price.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.price),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if (inventory.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.inventory_quantity),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if (description.equals("")){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.description),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if(place==null){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.place),Toast.LENGTH_LONG).show();
                        break;
                    }
                    if(category==null){
                        Toast.makeText(activity,activity.getString(R.string.forgot_something)+" "+activity.getString(R.string.category),Toast.LENGTH_LONG).show();
                        break;
                    }

                    else{

                        Item item = new Item();
                        item.setId(id);
                        item.setName(name);
                        item.setDescription(description);

                        try {
                            item.setPrice(Double.parseDouble(price));
                        }catch (Exception e){
                            Toast.makeText(activity,"Error: "+activity.getString(R.string.price),Toast.LENGTH_LONG).show();
                            break;
                        }

                        try {
                            item.setQuantity(Integer.parseInt(inventory));
                        }catch (Exception e){
                            Toast.makeText(activity,"Error: "+activity.getString(R.string.price),Toast.LENGTH_LONG).show();
                            break;
                        }

                        new Thread(
                                ()->{
                                    Gson gson = new Gson();
                                    String json = gson.toJson(item);
                                    utilDomi.PUTrequest(SEND_CALLBACK,"https://iterepi.firebaseio.com/sellers/"+user_id
                                            +"/places/"+activity.getPlaceOfProductSP().getSelectedItemPosition()
                                            +"/categories/"+activity.getCategoryOfProductSP().getSelectedItemPosition()
                                            +"/items/"+category.getItems()+".json",json);
                                }

                        ).start();

                        Intent s = new Intent(activity, SeeCategoryActivity.class);
                        s.putExtra("placePosition",activity.getPlaceOfProductSP().getSelectedItemPosition());
                        s.putExtra("categoryPosition",activity.getCategoryOfProductSP().getSelectedItemPosition());
                        activity.startActivity(s);
                        activity.finish();
                        break;
                    }
                }

            case R.id.closeBtn:
                activity.finish();
                break;
        }
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID){
            case SEND_CALLBACK:
                break;
            case SEARCH_CALLBACK:
                Gson gson = new Gson();
                this.seller = gson.fromJson(response, Seller.class);

                activity.runOnUiThread(
                        ()->{
                            if(seller!=null){
                                ArrayAdapter<Place> adp1 = new ArrayAdapter<Place>(this.activity, android.R.layout.simple_list_item_1, (List<Place>) seller.getPlaces().values());
                                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                this.activity.getPlaceOfProductSP().setAdapter(adp1);
                            }
                        }
                );

                break;
        }
    }
}
