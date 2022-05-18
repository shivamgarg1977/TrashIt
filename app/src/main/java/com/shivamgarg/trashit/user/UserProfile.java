package com.shivamgarg.trashit.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shivamgarg.trashit.R;
import com.shivamgarg.trashit.helperClasses.userAdapter.ProgressDataClass;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText name, phoneNumber, email, address;
    DatabaseReference ref;
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
        phoneNumber.setText(getIntent().getStringExtra("phoneNumber").toString());
        email.setText(getIntent().getStringExtra("email").toString());
        name.setText(getIntent().getStringExtra("fullName").toString());
        address.setText(getIntent().getStringExtra("address").toString());
        ref= FirebaseDatabase.getInstance().getReference("Users");
        save.setOnClickListener(this);

    }

    private void saveData() {

        ref.child(getIntent().getStringExtra("Uid")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserProfileData user=snapshot.getValue(UserProfileData.class);
                name.setText(user.getFullName().toString());
                phoneNumber.setText(user.getPhoneNumber().toString());
                email.setText(user.getEmail().toString());
                address.setText(user.getAddress().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                if(isValid()){
                    ref.child(getIntent().getStringExtra("Uid")).child("fullName").setValue(name.getText().toString());
                    ref.child(getIntent().getStringExtra("Uid")).child("email").setValue(email.getText().toString());
                    ref.child(getIntent().getStringExtra("Uid")).child("phoneNumber").setValue(phoneNumber.getText().toString());
                    ref.child(getIntent().getStringExtra("Uid")).child("address").setValue(address.getText().toString());
                    Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
                    saveData();

                }else{
                    Toast.makeText(this, "Nothing Changed", Toast.LENGTH_SHORT).show();
                }
        }
    }


    private boolean isValid() {
        return (!name.getText().toString().equals(getIntent().getStringExtra("fullName"))
        || !email.getText().toString().equals(getIntent().getStringExtra("email"))
        || !phoneNumber.getText().toString().equals(getIntent().getStringExtra("phoneNumber"))
        || !address.getText().toString().equals(null)
        );
    }


}

class UserProfileData{
    private String fullName,email,phoneNumber,address;
    public UserProfileData(){}


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}