package com.example.iterepi.controller;

import android.view.View;

import com.example.iterepi.R;
import com.example.iterepi.model.Buyer;
import com.example.iterepi.view.login.RegisterUserEmailActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterUserEmailController implements View.OnClickListener {

    private RegisterUserEmailActivity activity;


    public RegisterUserEmailController(RegisterUserEmailActivity activity) {

        this.activity = activity;

        activity.getRegisterBtn().setOnClickListener(this);


        activity.getBirthdayTF().setEndIconOnClickListener(v -> {

            openCalendar();

        });


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.registerUserBtn:

                userRegister();

                break;


        }

    }


    public void openCalendar() {

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText(R.string.select_birthday);
        MaterialDatePicker datePicker = builder.build();
        datePicker.show(activity.getSupportFragmentManager(), "DATE_PICKER");

        datePicker.addOnPositiveButtonClickListener(o -> {

            activity.getBirthdayTF().getEditText().setText(datePicker.getHeaderText());

        });

    }

    public void userRegister() {

        String name = activity.getNameTF().getEditText().getText().toString();
        String birthday = activity.getBirthdayTF().getEditText().getText().toString();
        String email = activity.getEmailTF().getEditText().getText().toString();
        String password = "";
        String cedula = activity.getPasswordTF().getEditText().getText().toString();
        int gender = -1;

        if (activity.getRadioGroup().getCheckedRadioButtonId() == R.id.checkMan) {
            gender = Buyer.MAN;
        } else {
            gender = Buyer.WOMAN;
        }

        if (name.equals("") || name == null) {
            putError(activity.getNameTF(), activity.getText(R.string.empty_field).toString());
        } else {
            removeError(activity.getNameTF());
        }

        if (birthday.equals("") || birthday == null) {
            putError(activity.getBirthdayTF(), activity.getText(R.string.empty_field).toString());
        } else {
            removeError(activity.getBirthdayTF());
        }

        if (cedula.equals("") || cedula == null) {
            putError(activity.getIdentificationTF(), activity.getString(R.string.empty_field));
        } else {
            removeError(activity.getIdentificationTF());
        }

        if (gender == -1) {
            Snackbar.make(activity.getRegisterBtn(), activity.getString(R.string.select_gender), Snackbar.LENGTH_SHORT).show();
        }


        String passTF = activity.getPasswordTF().getEditText().getText().toString();
        String confPassTF = activity.getConfPasswordTF().getEditText().getText().toString();


        if (passTF.matches(".*\\d.*") && passTF.matches(".*[a-zA-Z].*")) {

            removeError(activity.getPasswordTF());

            if (!passTF.equals(confPassTF)) {

                putError(activity.getConfPasswordTF(), activity.getString(R.string.pass_dont_match));

            } else {
                removeError(activity.getConfPasswordTF());
                password = activity.getPasswordTF().getEditText().getText().toString();
            }


            if (activity.getCheckTerms().isChecked()) {


            } else {

                Snackbar.make(activity.getRegisterBtn(), activity.getText(R.string.must_accept_terms), Snackbar.LENGTH_LONG).show();

            }

        } else {

            putError(activity.getPasswordTF(), activity.getString(R.string.pass_must_contain));

        }

    }

    public void putError(TextInputLayout txtLay, String error) {

        txtLay.setError(error);

    }

    public void removeError(TextInputLayout txtLay) {

        txtLay.setError("");
        txtLay.setErrorEnabled(false);

    }
}
