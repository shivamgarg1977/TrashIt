package com.shivamgarg.trashit.common;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.shivamgarg.trashit.R;
import com.shivamgarg.trashit.user.UserDashBoard;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText username;
    TextInputEditText password;
    ExtendedFloatingActionButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        username=findViewById(R.id.login_user_email);
        password=findViewById(R.id.login_user_password);
        login=findViewById(R.id.common_login_register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameString=username.getText().toString();
                String passwordString=password.getText().toString();
                if(usernameString.equals("shivamgarg1977@gmail.com") && passwordString.equals("1234")){
                    Intent i = new Intent(LoginActivity.this,
                            UserDashBoard.class);
                    //Intent is used to switch from one activity to another.

                    startActivity(i);
                    //invoke the SecondActivity.
                }
            }
        });

    }

    public void onSignupClick(View view) {
        startActivity(new Intent(this,SignUpActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
}