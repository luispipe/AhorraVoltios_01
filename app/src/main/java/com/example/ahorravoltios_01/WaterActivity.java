package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahorravoltios_01.models.Water;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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

        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");

        registerWater=findViewById(R.id.buttonRegisterWater);

        registerWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity.getText().toString().isEmpty() ||
                    price.getText().toString().isEmpty() ||
                    months.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                "Todos los campos deben estar completos",Toast.LENGTH_LONG).show();
                }else{
                    int quantityWater= Integer.parseInt(quantity.getText().toString());
                    int priceWater= Integer.parseInt(price.getText().toString());
                    String monthWater= months.getSelectedItem().toString();
                    Water consume= new Water(quantityWater,priceWater,monthWater,idUser);
                    saveWater(consume);
                    cleanView();
                    Toast.makeText(getApplicationContext(),
                            "Registro del consumo exitoso",Toast.LENGTH_LONG).show();
                }
                }
        });


    }

    public void saveWater(Water consume){
        File fileWater= new File(getFilesDir(),"water.txt");

        try {
            FileWriter writer= new FileWriter(fileWater,true);
            BufferedWriter bufferedWriter= new BufferedWriter(writer);
            bufferedWriter.write(
                         consume.getQuantity()+","+
                            consume.getPrice()+","+
                            consume.getMonth()+","+
                            consume.getIdUser()
            );
            bufferedWriter.newLine();
            bufferedWriter.close();
        }catch (Exception error){
            error.printStackTrace();
        }

    }

    public void cleanView(){
        quantity.setText("");
        price.setText("");
        months.setSelection(0);
    }


}