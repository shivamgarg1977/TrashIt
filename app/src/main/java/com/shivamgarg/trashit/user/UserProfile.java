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
    TextInputEditText name, phoneNumber, email, address;
   ;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        name = findViewById(R.id.Profile_name_input_View);

        phoneNumber = findViewById(R.id.Profile_phone_input_View);

        email = findViewById(R.id.Profile_email_input_View);

        address = findViewById(R.id.Profile_Address_input_View);

        save = findViewById(R.id.save);

        phoneNumber.setText(getIntent().getStringExtra("phoneNumber"));
        name.setText(getIntent().getStringExtra("fullName"));
        email.setText(getIntent().getStringExtra("email"));

        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.save:

        }

    }



}

