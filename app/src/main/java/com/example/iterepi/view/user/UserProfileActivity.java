package com.example.iterepi.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iterepi.R;

public class UserProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private ImageButton backBtn;
    private ImageButton setImageBtn;

    private TextView nameTV;
    private TextView birthTV;
    private TextView genderTV;
    private TextView idTV;
    private TextView phoneNumberTV;
    private TextView emailTV;

    private Button changePassBtn;
    private Button update_dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profileImage = findViewById(R.id.profileImage);
        backBtn  = findViewById(R.id.backBtn);
        setImageBtn  = findViewById(R.id.setImageBtn);

        nameTV  = findViewById(R.id.quantityTV);
        birthTV  = findViewById(R.id.birthTV);
        genderTV  = findViewById(R.id.genderTV);
        idTV  = findViewById(R.id.idTV);
        phoneNumberTV  = findViewById(R.id.phoneNumberTV);
        emailTV  = findViewById(R.id.emailTV);

        changePassBtn  = findViewById(R.id.addProductBtn);
        update_dataBtn  = findViewById(R.id.update_dataBtn);
    }

    public ImageView getProfileImage() {
        return profileImage;
    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public ImageButton getSetImageBtn() {
        return setImageBtn;
    }

    public TextView getNameTV() {
        return nameTV;
    }

    public TextView getBirthTV() {
        return birthTV;
    }

    public TextView getGenderTV() {
        return genderTV;
    }

    public TextView getIdTV() {
        return idTV;
    }

    public TextView getPhoneNumberTV() {
        return phoneNumberTV;
    }

    public TextView getEmailTV() {
        return emailTV;
    }

    public Button getChangePassBtn() {
        return changePassBtn;
    }

    public Button getUpdate_dataBtn() {
        return update_dataBtn;
    }
}
