package com.example.iterepi.view.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.example.iterepi.controller.login.RestorePasswordController;
import com.google.android.material.textfield.TextInputLayout;

public class RestorePasswordActivity extends AppCompatActivity {

    private TextInputLayout emailTF;
    private Button restoreBtn;
    private ImageButton backBtn;
    private RestorePasswordController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_pass_with);

        emailTF = findViewById(R.id.emailTF);
        restoreBtn = findViewById(R.id.restorePassBtn);
        backBtn = findViewById(R.id.backBtn);

        controller = new RestorePasswordController(this);

    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public TextInputLayout getEmailTF() {
        return emailTF;
    }

    public Button getRestoreBtn() {
        return restoreBtn;
    }
}
