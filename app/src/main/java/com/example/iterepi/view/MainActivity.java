package com.example.iterepi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.iterepi.R;

public class MainActivity extends AppCompatActivity {

    private ImageView logoIV;
    private ImageView nameIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        logoIV = findViewById(R.id.logoIV);
        nameIV = findViewById(R.id.nameIV);
    }
}
