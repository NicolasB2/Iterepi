package com.example.iterepi.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;

public class LoginUserActivity extends AppCompatActivity {

    private Button facebookBtn;
    private Button googleBtn;
    private Button emailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
    }
}
