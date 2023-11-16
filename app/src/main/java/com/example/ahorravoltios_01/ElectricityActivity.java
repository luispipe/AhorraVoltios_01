package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahorravoltios_01.models.Electricity;
import com.example.ahorravoltios_01.models.Water;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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

        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");
        registerElectricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity.getText().toString().isEmpty() ||
                        price.getText().toString().isEmpty() ||
                        months.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Todos los campos deben estar completos",Toast.LENGTH_LONG).show();
                }else{
                    int quantityElectricity= Integer.parseInt(quantity.getText().toString());
                    int priceElectricity= Integer.parseInt(price.getText().toString());
                    String monthElectricity= months.getSelectedItem().toString();
                    Electricity consume= new Electricity(quantityElectricity,priceElectricity,monthElectricity,idUser);
                    saveElectricity(consume);
                    cleanView();
                    Toast.makeText(getApplicationContext(),
                            "Registro del consumo exitoso",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void saveElectricity(Electricity consume){
        File fileElectricity= new File(getFilesDir(),"electricity.txt");

        try {
            FileWriter writer= new FileWriter(fileElectricity,true);
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