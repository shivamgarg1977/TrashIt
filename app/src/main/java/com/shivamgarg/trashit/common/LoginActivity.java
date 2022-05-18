package com.shivamgarg.trashit.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shivamgarg.trashit.R;
import com.shivamgarg.trashit.user.UserDashBoard;
import com.shivamgarg.trashit.user.UserData;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    TextInputEditText username;
    TextInputEditText password;
    ExtendedFloatingActionButton login;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String UserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth=FirebaseAuth.getInstance();


        username=findViewById(R.id.login_user_email);
        password=findViewById(R.id.login_user_password);
        login=findViewById(R.id.common_login_register);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorizeUser();
            }
        });
    }

    private void authorizeUser() {
        if(!username.getText().toString().matches(emailPattern)){
            username.setError("Enter Correct Email");
        }else if(password.getText().toString().isEmpty() || password.getText().toString().length()<6){
            password.setError("Min length is 6 ");
        }else {
            mAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent=new Intent(LoginActivity.this,UserDashBoard.class);
                        UserId = task.getResult().getUser().getUid();
                        intent.putExtra("Uid",UserId);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Login failed:"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void onSignupClick(View view) {
        startActivity(new Intent(this,SignUpActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            Intent intent=new Intent(this,UserDashBoard.class);
            intent.putExtra("Uid",UserId);
            startActivity(intent);
        }
    }
}