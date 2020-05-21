package com.example.pikr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pikr.R;
import com.example.pikr.models.Login;

public class LoginActivity extends AppCompatActivity {
    private Login currentLogin;

    private void changeActionBar(){
        setTitle("Sign in");
    }

    private void signIn(){
        Button signInButton = findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText emailEntry = findViewById(R.id.email);
                EditText passwordEntry = findViewById(R.id.password);
                if(!(currentLogin.getEmail() == null || currentLogin.getEmail().equals("empty") || currentLogin.getPassword().equals("empty"))) {
                    if (emailEntry.getText().toString().equals(currentLogin.getEmail()) && passwordEntry.getText().toString().equals(currentLogin.getPassword())) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Email or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "No profile has been created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loadLogin(){
        assert currentLogin.getProfile() != null;
        if(!(currentLogin.getEmail() != null || currentLogin.getPassword() != null || currentLogin.getEmail().equals("empty") || currentLogin.getPassword().equals("empty"))){
            EditText emailBox = findViewById(R.id.email);
            EditText passwordBox = findViewById(R.id.password);
            emailBox.setText(currentLogin.getEmail());
            passwordBox.setText(currentLogin.getPassword());
        }
    }

    private void register(){
        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActionBar()!=null) getActionBar().setTitle("Sign in");
        setContentView(R.layout.activity_login);
        currentLogin = new Login(getApplicationContext());
        loadLogin();
        changeActionBar();
        signIn();
        register();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
