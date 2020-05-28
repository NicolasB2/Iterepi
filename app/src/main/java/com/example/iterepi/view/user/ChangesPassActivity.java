package com.example.iterepi.view.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iterepi.R;
import com.google.android.material.textfield.TextInputLayout;

public class ChangesPassActivity extends AppCompatActivity {

    private TextInputLayout currentPass;
    private TextInputLayout newPass;
    private TextInputLayout confNewPass;
    private ImageButton backBtn;
    private Button changePassBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        currentPass = findViewById(R.id.currentPassTV);
        newPass = findViewById(R.id.newPassTV);
        confNewPass = findViewById(R.id.confirmNewPassTV);
        changePassBtn = findViewById(R.id.changePassBtn);
        backBtn = findViewById(R.id.backBtn6);


    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public TextInputLayout getCurrentPass() {
        return currentPass;
    }

    public TextInputLayout getNewPass() {
        return newPass;
    }

    public TextInputLayout getConfNewPass() {
        return confNewPass;
    }

    public Button getChangePassBtn() {
        return changePassBtn;
    }
}
