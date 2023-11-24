package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ahorravoltios_01.models.Electricity;
import com.example.ahorravoltios_01.models.Water;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    TextView totalWater,totalElectricity,totalPay, monthWater,
            quantityWater, monthElectricity,quantityElectricity;

    Button more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        totalWater=findViewById(R.id.tv_total_water);
        totalElectricity=findViewById(R.id.tv_total_electricity);
        totalPay= findViewById(R.id.tv_total_pay);
        monthWater=findViewById(R.id.tv_month_max_water);
        quantityWater=findViewById(R.id.tv_max_quantity_water);
        monthElectricity=findViewById(R.id.tv_month_max_electricity);
        quantityElectricity=findViewById(R.id.tv_max_quantity_electricity);
        more=findViewById(R.id.buttonMore);

        Intent receive= getIntent();
        String id= receive.getStringExtra("idUser");

        File waterFile= new File(getFilesDir(),"water.txt");
        File electricityFile= new File(getFilesDir(),"electricity.txt");

        ArrayList<Water> waterList= listWater(waterFile,id);
        ArrayList<Electricity> electricityList= listElectricity(electricityFile,id);

        totalConsumeWater(waterList);
        totalConsumeElectricity(electricityList);

        int totalPay_value=totalPayWater(waterList)+totalPayElectricity(electricityList);
        totalPay.setText("$ "+totalPay_value+"");

        Intent moreStatistics= new Intent(getApplicationContext(), MoreStatisticsActivity.class);
        moreStatistics.putExtra("idUser",id);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(moreStatistics);
            }
        });

    }

    public void totalConsumeWater(ArrayList<Water>list){
         int total=0;
         String month="";
         int max=0;
         for (Water i: list){
             total+=i.getQuantity();
             if(max<i.getQuantity()){
                 max=i.getQuantity();
                 month=i.getMonth();
             }
         }
        totalWater.setText(total+" L");
        monthWater.setText(month);
        quantityWater.setText(max+"");
    }

    public void totalConsumeElectricity(ArrayList<Electricity>list){
        int total=0;
        String month="";
        int max=0;
        for (Electricity i: list){
            total+=i.getQuantity();
            if(max<i.getQuantity()){
                max=i.getQuantity();
                month=i.getMonth();
            }
        }
        totalElectricity.setText(total+" Kwh");
        quantityElectricity.setText(max+"");
        monthElectricity.setText(month);
    }

    public ArrayList<Water> listWater(File water,String user){
        ArrayList<Water> list= new ArrayList<>();

        try {
            FileReader fileReader=new FileReader(water);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String item;
            while ((item=bufferedReader.readLine())!=null){
                   String [] data= item.split(",");
                   int quantity= Integer.parseInt(data[0]);
                   int price= Integer.parseInt(data[1]);
                   String month= data[2];
                   String userId= data[3];
                   if (userId.equals(user)){
                       Water waterObj= new Water(quantity,price,month,userId);
                       list.add(waterObj);
                   }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<Electricity> listElectricity(File electricity,String user){
        ArrayList<Electricity> list= new ArrayList<>();

        try {
            FileReader fileReader=new FileReader(electricity);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String item;
            while ((item=bufferedReader.readLine())!=null){
                String [] data= item.split(",");
                int quantity= Integer.parseInt(data[0]);
                int price= Integer.parseInt(data[1]);
                String month= data[2];
                String userId= data[3];
                if (userId.equals(user)){
                    Electricity electricityObj= new Electricity(quantity,price,month,userId);
                    list.add(electricityObj);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public int totalPayWater(ArrayList<Water>list){

        int pay=0;
        for (Water i: list){
            pay+=i.getPrice();
        }
        return pay;
    }
    public int totalPayElectricity(ArrayList<Electricity>list){

        int pay=0;
        for (Electricity i: list){
            pay+=i.getPrice();
        }
        return pay;
    }



}