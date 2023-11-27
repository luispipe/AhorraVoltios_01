package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ahorravoltios_01.models.Electricity;
import com.example.ahorravoltios_01.models.Water;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MoreStatisticsActivity extends AppCompatActivity {

    TableLayout water,electricity;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_statistics);
        water= findViewById(R.id.TableWater);
        electricity= findViewById(R.id.TableElectricity);
        username=findViewById(R.id.textViewUsername);

        Intent receive= getIntent();
        String userID= receive.getStringExtra("idUser");

        File waterFile= new File(getFilesDir(),"water.txt");
        File electricityFile= new File(getFilesDir(),"electricity.txt");

        ArrayList<Water> list= consumeWater(waterFile,userID);
        inflateWaterTable(list);

        ArrayList<Electricity> listElectricity= consumeElectricity(electricityFile,userID);
        inflateElectricityTable(listElectricity);

    }
/*
    public String nameUser(String id){

    }*/

    public void inflateWaterTable(ArrayList<Water>list){
        System.out.println("inflate");
        System.out.println(list.size());

        int total=0;
        String averageValue="";
        System.out.println("Antes");
        for (Water i: list){
            TableRow row=new TableRow(this);
            TextView quantity= new TextView(this);
            quantity.setWidth(97);
            quantity.setTextSize(14);
            quantity.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView price= new TextView(this);
            price.setTextSize(14);
            price.setWidth(96);
            price.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView month= new TextView(this);
            month.setWidth(105);
            month.setTextSize(14);
            month.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView average= new TextView(this);
            average.setWidth(90);
            average.setTextSize(14);
            average.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            quantity.setText(i.getQuantity()+"");
            price.setText(i.getPrice()+"");
            month.setText(i.getMonth());
            total= totalWater(list);
            averageValue=average(total,i.getQuantity());
            average.setText(averageValue+"%");

            System.out.println(total+" - "+i.getQuantity()+" - "+averageValue);
            row.addView(month);
            row.addView(quantity);
            row.addView(price);
            row.addView(average);

            water.addView(row);
        }
        System.out.println("Fin");
    }

    public void inflateElectricityTable(ArrayList<Electricity>list){
        int total=0;
        String averageValue="";
        System.out.println("Antes");
        for (Electricity i: list){
            TableRow row=new TableRow(this);
            TextView quantity= new TextView(this);
            quantity.setWidth(97);
            quantity.setTextSize(14);
            quantity.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView price= new TextView(this);
            price.setTextSize(14);
            price.setWidth(96);
            price.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView month= new TextView(this);
            month.setWidth(105);
            month.setTextSize(14);
            month.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView average= new TextView(this);
            average.setWidth(90);
            average.setTextSize(14);
            average.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            quantity.setText(i.getQuantity()+"");
            price.setText(i.getPrice()+"");
            month.setText(i.getMonth());
            total= totalElectricity(list);
            averageValue=average(total,i.getQuantity());
            average.setText(averageValue+"%");

            row.addView(month);
            row.addView(quantity);
            row.addView(price);
            row.addView(average);

            electricity.addView(row);
        }
    }

    public ArrayList<Water> consumeWater(File water,String id){
        ArrayList<Water> list= new ArrayList<>();
        try {
            FileReader reader= new FileReader(water);
            BufferedReader bufferedReader= new BufferedReader(reader);
            String line;
            System.out.println(reader.toString());
            while ((line=bufferedReader.readLine())!=null){
                String [] data= line.split(",");
                if (id.equals(data[3])){
                    System.out.println(line);
                    int quantity= Integer.parseInt(data[0]);
                    int price= Integer.parseInt(data[1]);
                    String month= data[2];
                    Water obj= new Water(quantity,price,month,id);
                    list.add(obj);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(list.size());
        return list;
    }

    public ArrayList<Electricity> consumeElectricity(File electricity,String id){
        ArrayList<Electricity> list= new ArrayList<>();
        try {
            FileReader reader= new FileReader(electricity);
            BufferedReader bufferedReader= new BufferedReader(reader);
            String line;
            System.out.println(reader.toString());
            while ((line=bufferedReader.readLine())!=null){
                String [] data= line.split(",");
                if (id.equals(data[3])){
                    System.out.println(line);
                    int quantity= Integer.parseInt(data[0]);
                    int price= Integer.parseInt(data[1]);
                    String month= data[2];
                    Electricity obj= new Electricity(quantity,price,month,id);
                    list.add(obj);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(list.size());
        return list;
    }

    public int totalWater (ArrayList<Water> list){
        int total=0;
        for (Water i: list){
            total+=i.getQuantity();
        }
        return total;
    }

  public int totalElectricity(ArrayList<Electricity> list){
      int total=0;
      for (Electricity i: list){
          total+=i.getQuantity();
      }
      return total;
    }

    public String average(int total, int quantity){
        if (total==0){
            return "Error";
        }else{
            double total_d= total;
            double quantity_d=quantity;
            double avg= (quantity_d/total_d)*100; //2.3698
            DecimalFormat df= new DecimalFormat("#.##");// 2.36
           // df.setRoundingMode(RoundingMode.FLOOR); 2.37
           return df.format(avg);
        }
    }

}