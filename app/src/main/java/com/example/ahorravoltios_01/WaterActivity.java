package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class WaterActivity extends AppCompatActivity {

    EditText quantity, price;
    Spinner months;
    Button registerWater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        quantity=findViewById(R.id.editTextWaterQuantity);
        price=findViewById(R.id.editTextWaterPrice);
        months=findViewById(R.id.spinnerMonthsWater);
        registerWater=findViewById(R.id.buttonRegisterWater);


    }
}