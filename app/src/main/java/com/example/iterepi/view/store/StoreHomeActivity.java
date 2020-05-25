package com.example.iterepi.view.store;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;

public class StoreHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu_store,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.store_item_accounts:
                Toast.makeText(this,"accounts",Toast.LENGTH_LONG).show();
                return true;
            case R.id.store_item_doubts_frequent:
                Toast.makeText(this,"doubts_frequent",Toast.LENGTH_LONG).show();
                return true;
            case R.id.store_item_logout:
                Toast.makeText(this,"logout",Toast.LENGTH_LONG).show();
                return true;
            case R.id.store_item_my_places:
                Toast.makeText(this,"my_places",Toast.LENGTH_LONG).show();
                return true;
            case R.id.store_item_my_sales:
                Toast.makeText(this,"my_sales",Toast.LENGTH_LONG).show();
                return true;
            case R.id.store_item_terms_and_conditions:
                Toast.makeText(this,"terms",Toast.LENGTH_LONG).show();
                Log.e(">>>>>>>>>","entro");
                return true;
            default:
                Log.e(">>>>>>>>>","entro");
                return super.onOptionsItemSelected(item);
        }
    }
}
