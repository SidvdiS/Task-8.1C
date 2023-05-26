package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itubeapp.data.UserDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    EditText userName, password;
    Button login, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.sign_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempUser, tempPswd;
                tempUser = userName.getText().toString();
                tempPswd = password.getText().toString();
                if(TextUtils.isEmpty(tempUser)){
                    showToast("Enter a username!");
                    userName.setError("Username is compulsory");
                    return;
                } else if (TextUtils.isEmpty(tempPswd)) {
                    showToast("Enter a password!");
                    password.setError("Password is compulsory");
                    return;
                }
                UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(MainActivity.this);
                boolean isValidlogin = userDatabaseHelper.isValidLogin(tempUser, tempPswd);
                if(isValidlogin){
                    SharedPreferences sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                }else {
                    showToast("Username not registered. Click on Sign Up and create account.");
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}