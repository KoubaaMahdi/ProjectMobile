package com.example.fitnessappproject;
import android.util.Log;
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

public class Login extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private TextView mRegisterTextView;


    public void init(){
        // Initialize views
        mEmailEditText = findViewById(R.id.email);
        mPasswordEditText = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.login);
        mRegisterTextView = findViewById(R.id.register);

        // Initialize database helper


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

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
                        out.println("Login");
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
                                if (response.equals("true")) {
                                    // Login successful, start the main activity
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Login failed, show an error message
                                    Toast.makeText(Login.this, "Login failed, please try again", Toast.LENGTH_SHORT).show();
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
