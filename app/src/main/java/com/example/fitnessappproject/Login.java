package com.example.fitnessappproject;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private TextView mRegisterTextView;

    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDbHelper;

    public void init(){
        // Initialize views
        mEmailEditText = findViewById(R.id.email);
        mPasswordEditText = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.login);
        mRegisterTextView = findViewById(R.id.register);

        // Initialize database helper
        mDbHelper = new DatabaseHelper(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        // Open database
        mDatabase = mDbHelper.getReadableDatabase();

        // Login button click listener
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Register text view click listener
        mRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        // Get user input
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
            // Check if user exists
            if (mDbHelper.checkUser(email, password)) {
                // User found, start main activity
                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Login.this, MainActivity.class);
                startActivity(intent1);
            } else {
                // User not found, display error message
                Toast.makeText(Login.this, "Invalid email or password. Please try again or register.", Toast.LENGTH_SHORT).show();
            }




}}
