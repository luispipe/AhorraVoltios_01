package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConsumeActivity extends AppCompatActivity {

    Button water;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume);
        water=findViewById(R.id.buttonRegisterConsumeWater);
        Intent registerWater= new Intent(getApplicationContext(),
                WaterActivity.class);

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerWater);
            }
        });

    }
}