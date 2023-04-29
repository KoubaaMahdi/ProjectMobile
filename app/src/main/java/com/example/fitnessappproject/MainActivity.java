package com.example.fitnessappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStart;

    private RadioGroup radioGroup;

    public void init(){
        // Get a reference to the "Start" button
        mBtnStart = findViewById(R.id.btn_start);
        radioGroup = findViewById(R.id.radio_group);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        // Set an OnClickListener on the "Start" button
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Determine which radio button is selected

                int selectedId = radioGroup.getCheckedRadioButtonId();

                // Start a different activity depending on which radio button is selected
                if (selectedId == R.id.radio_cardio) {
                    Intent cardioIntent = new Intent(MainActivity.this, CardioActivity.class);
                    startActivity(cardioIntent);
                } else if (selectedId == R.id.radio_strength) {
                    Intent strengthIntent = new Intent(MainActivity.this, StrengthActivity.class);
                    startActivity(strengthIntent);
                } else if (selectedId == R.id.radio_flexibility) {
                    Intent flexibilityIntent = new Intent(MainActivity.this, FlexibilityActivity.class);
                    startActivity(flexibilityIntent);
                } else {
                    // Do nothing
                }
            }
        });
    }
}
