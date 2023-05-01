package com.example.serverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper mDbHelper;
    private String serverIP;
    private int serverPort;

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> mUserList;
    private DatabaseHelper mDatabaseHelper;
    public void init(){
        mDbHelper = new DatabaseHelper(this);
        serverIP="10.0.2.15";
        serverPort=8080;
        mListView = findViewById(R.id.user_list_view);
        mDatabaseHelper = new DatabaseHelper(this);
        mUserList = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mUserList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mListView.setAdapter(mAdapter);
        // Get all users from the database
        Cursor cursor = mDatabaseHelper.getReadableDatabase().query(DatabaseHelper.TABLE_USERS,
                new String[]{DatabaseHelper.COLUMN_ID,DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_EMAIL,DatabaseHelper.COLUMN_PASSWORD},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL));
            String Password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD));

            mUserList.add("Account nb:  "+id+"\n Name:   "+name + "\n Email:    " + email + "\n Password:   "+Password);
        }


        // Notify the adapter that the data set has changed
        mAdapter.notifyDataSetChanged();

        ServerThread serverThread = new ServerThread();
        serverThread.start();
    }

    class ServerThread extends Thread {
        private ServerSocket serverSocket;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(serverPort);

                while (true) {
                    final Socket clientSocket = serverSocket.accept();

                            // Get the input and output streams from the socket
                            InputStream inputStream = clientSocket.getInputStream();
                            OutputStream outputStream = clientSocket.getOutputStream();

                            // Create a reader and writer for the streams
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                            // Read the login credentials from the client

                            String type = reader.readLine();
                            if(type.equals("Login")){
                                String email =  reader.readLine();
                                String password = reader.readLine();
                                // Check if the user is in the database
                                boolean userExists = mDbHelper.checkUser(email, password);

                                // Send the response to the client
                                writer.write(userExists ? "true\n" : "false\n");
                                // writer.write("true");
                                writer.flush();
                            }
                            else{
                                String name = reader.readLine();
                                String email =  reader.readLine();
                                String password = reader.readLine();
                                // Check if the user is in the database
                                boolean userExists = mDbHelper.checkUser(email, password);
                                mDbHelper.addUser(name,email,password);


                                // Send the response to the client
                                writer.write(userExists ? "true" : "false");
                                // writer.write("true");
                                writer.flush();
                            }

                            // Close the streams and socket
                            writer.close();
                            reader.close();
                            outputStream.close();
                            inputStream.close();
                            clientSocket.close();



                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
