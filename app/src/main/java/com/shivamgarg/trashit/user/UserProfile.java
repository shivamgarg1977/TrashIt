package com.shivamgarg.trashit.user;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shivamgarg.trashit.R;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText name,phoneNumber,email,address;
    TextView nameT,phoneNumberT,emailT,addressT;
    String nameS,phoneNumberS,emailS,addressS;
    TextInputLayout nameL,phoneNumberL,emailL,addressL;
    Button save,edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        name=findViewById(R.id.Profile_name_input_View);
        nameT=findViewById(R.id.Profile_name_text_View);
        nameL=findViewById(R.id.profile_name_input_layout);

        phoneNumber=findViewById(R.id.Profile_phone_input_View);
        phoneNumberT=findViewById(R.id.Profile_phone_text_View);
        phoneNumberL=findViewById(R.id.profile_phone_input_layout);

        email=findViewById(R.id.Profile_email_input_View);
        emailT=findViewById(R.id.Profile_email_text_View);
        emailL=findViewById(R.id.profile_email_input_layout);

        address=findViewById(R.id.Profile_Address_input_View);
        addressT=findViewById(R.id.Profile_Address_text_View);
        addressL=findViewById(R.id.profile_address_input_layout);

        save=findViewById(R.id.save);
        edit=findViewById(R.id.edit);

        save.setOnClickListener(this);
        edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.save:
                saveStrings();
                inputLayoutVisibility(false);
                textVisibility(true);
            case R.id.edit:
                inputLayoutVisibility(true);
                textVisibility(true);
        }

    }

    private void saveStrings() {
        nameS=name.getText().toString();
        addressS=address.getText().toString();
        emailS=email.getText().toString();
        phoneNumberS=phoneNumber.getText().toString();
        nameT.setText(nameS);
        addressT.setText(addressS);
        emailT.setText(emailS);
        phoneNumberT.setText(phoneNumberS);
    }

    public void inputLayoutVisibility(Boolean a){
        if(a){
            nameL.setVisibility(View.VISIBLE);
            phoneNumberL.setVisibility(View.VISIBLE);
            emailL.setVisibility(View.VISIBLE);
            addressL.setVisibility(View.VISIBLE);
        }else{
            nameL.setVisibility(View.GONE);
            phoneNumberL.setVisibility(View.INVISIBLE);
            emailL.setVisibility(View.INVISIBLE);
            addressL.setVisibility(View.INVISIBLE);

        }

    }
    public void textVisibility(Boolean a){
        if(a){
            nameT.setVisibility(View.VISIBLE);
            phoneNumberT.setVisibility(View.VISIBLE);
            emailT.setVisibility(View.VISIBLE);
            addressT.setVisibility(View.VISIBLE);
        }else{
            nameT.setVisibility(View.INVISIBLE);
            phoneNumberT.setVisibility(View.INVISIBLE);
            emailT.setVisibility(View.INVISIBLE);
            addressT.setVisibility(View.INVISIBLE);

        }

    }
}