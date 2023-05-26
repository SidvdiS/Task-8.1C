package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itubeapp.data.UserDatabaseHelper;
import com.example.itubeapp.model.User;

public class SignUpActivity extends AppCompatActivity {

    EditText fullName, userName, password, confirmPswd;
    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName = findViewById(R.id.full_name);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPswd = findViewById(R.id.confirm_password);
        createAccount = findViewById(R.id.create_account);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = userName.getText().toString();
                String fullNameText = fullName.getText().toString();
                String passwordText = password.getText().toString();
                String confirmPswdText = confirmPswd.getText().toString();

                if(TextUtils.isEmpty(usernameText)){
                    showToast("Enter a username");
                    return;
                } else if (TextUtils.isEmpty(passwordText)) {
                    showToast("Enter a password");
                    return;
                } else if (!TextUtils.equals(passwordText,confirmPswdText)) {
                    showToast("Password and Confirm Password do not match");
                    return;
                }

                UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(SignUpActivity.this);
                Boolean checkUsername = userDatabaseHelper.checkUsername(usernameText);

                if(checkUsername){
                    showToast("Username already exists. Enter different Username");
                    return;
                }
                else{
                    User user = new User(usernameText,passwordText, fullNameText);
                    long insert = userDatabaseHelper.insertUser(user);


                    if(insert==-1){
                        showToast("Cannot create account");
                    } else {
                        showToast("Account created successfully");
                        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}