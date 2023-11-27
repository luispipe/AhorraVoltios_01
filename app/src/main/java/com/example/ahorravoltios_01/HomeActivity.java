package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    Button consume, statistic, recommendations;

    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        consume=findViewById(R.id.buttonRegisterConsume);
        statistic=findViewById(R.id.buttonStatistics);
        recommendations=findViewById(R.id.buttonRecommendations);
        nav=findViewById(R.id.btNav);

        Intent registerConsume= new Intent(getApplicationContext(),
                ConsumeActivity.class);

        Intent receive= getIntent();
        String id= receive.getStringExtra("idUser");

        registerConsume.putExtra("idUser",id);

        Intent statistics_view= new Intent(getApplicationContext(),
                StatisticsActivity.class);
        statistics_view.putExtra("idUser",id);

        Intent recommendations_view= new Intent(getApplicationContext(),
                RecommendationActivity.class);


        consume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerConsume);
            }
        });

        statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(statistics_view);
            }
        });

        recommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(recommendations_view);
            }
        });

    }
}