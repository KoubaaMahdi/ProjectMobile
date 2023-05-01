package com.example.fitnessappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Register extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mRegisterButton;

    private TextView mLoginTextView;

    private SQLiteDatabase mDatabase;

   ;
    public void init (){
        // Initialize views
        mNameEditText = findViewById(R.id.name);
        mEmailEditText = findViewById(R.id.email);
        mPasswordEditText = findViewById(R.id.password);
        mRegisterButton = findViewById(R.id.register);
        mLoginTextView = findViewById(R.id.login);

        // Open database


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
    public void registerUser(){
        final String name = mNameEditText.getText().toString();
        final String email = mEmailEditText.getText().toString();
        final String password = mPasswordEditText.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create a socket connection to the server
                    Socket socket = new Socket("10.0.2.15", 8080);

                    // Send the login credentials to the server
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Register");
                    out.println(name);
                    out.println(email);
                    out.println(password);

                    // Get the response from the server
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    final String response = in.readLine();

                    // Close the socket connection
                    socket.close();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (response.equals("false")) {
                                // Login successful, start the main activity
                                Toast.makeText(Register.this, "Registration Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Login failed, show an error message
                                Toast.makeText(Register.this, "Account Already Exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }




}