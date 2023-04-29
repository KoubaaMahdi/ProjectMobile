package com.example.fitnessappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.fitnessappproject.DatabaseHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mRegisterButton;

    private TextView mLoginTextView;

    private SQLiteDatabase mDatabase;

    private DatabaseHelper databaseHelper;
    public void init (){
        // Initialize views
        mNameEditText = findViewById(R.id.name);
        mEmailEditText = findViewById(R.id.email);
        mPasswordEditText = findViewById(R.id.password);
        mRegisterButton = findViewById(R.id.register);
        mLoginTextView = findViewById(R.id.login);

        // Open database
        databaseHelper = new DatabaseHelper(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        // Register button click listener
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        mLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser() {
        // Get user input
        String name = mNameEditText.getText().toString().trim();
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();

        // Validate input
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (databaseHelper.checkUser(email,password)) {
            Toast.makeText(Register.this, "Username already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = databaseHelper.addUser(name, email,password);

        if (result > 0) {
            Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
        finish();
    }


}