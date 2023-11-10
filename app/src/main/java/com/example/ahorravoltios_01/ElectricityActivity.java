package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ElectricityActivity extends AppCompatActivity {

    EditText quantity,price;
    Spinner months;
    Button registerElectricity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);

        quantity= findViewById(R.id.editTextElectricityQuantity);
        price=findViewById(R.id.editTextElectricityPrice);
        months=findViewById(R.id.spinnerMonthsElectricity);
        registerElectricity=findViewById(R.id.buttonRegisterElectricity);


    }
}