package com.shivamgarg.trashit.common;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.shivamgarg.trashit.R;
import com.shivamgarg.trashit.user.UserData;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase database;

    ExtendedFloatingActionButton register;
    ImageView gotoLoginActivity;
    TextInputEditText inputFullName, inputEmail, inputPassword, inputPhoneNumber;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);
        gotoLoginActivity = findViewById(R.id.goto_login_activity);
        register = findViewById(R.id.common_sign_up_register);
        inputFullName = findViewById(R.id.signup_full_name);
        inputEmail = findViewById(R.id.signup_user_email);
        inputPassword = findViewById(R.id.signup_user_password);
        inputPhoneNumber = findViewById(R.id.signup_user_phone_number);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAuth();
            }
        });

    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private void performAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String phoneNumber = inputPhoneNumber.getText().toString();
        String fullName = inputFullName.getText().toString();

        if (!email.matches(emailPattern)) {
            inputEmail.setError("Enter Correct Email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("Min length is 6 ");
        } else {
            progressDialog.setMessage("Registering...");
            progressDialog.setTitle("Creating Account");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        UserData userData = new UserData(fullName, email, password, phoneNumber);
                        // To get id from Firebase Authentication Users
                        String UserId = task.getResult().getUser().getUid();
                        // Storing users data to realtime database
                        database.getReference().child("Users").child(UserId).setValue(userData);


                        Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, "Registration failed: " + task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }
}