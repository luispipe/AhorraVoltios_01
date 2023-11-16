package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConsumeActivity extends AppCompatActivity {

    Button water,electricity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume);
        water=findViewById(R.id.buttonRegisterConsumeWater);
        electricity=findViewById(R.id.buttonRegisterConsumeEnergy);

        Intent receive= getIntent();
        String id= receive.getStringExtra("idUser");

        Intent registerWater= new Intent(getApplicationContext(),
                WaterActivity.class);
        registerWater.putExtra("idUser",id);

        Intent registerElectricity= new Intent(getApplicationContext(),
                ElectricityActivity.class);
        registerElectricity.putExtra("idUser",id);

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerWater);
            }
        });

        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerElectricity);
            }
        });

    }
}