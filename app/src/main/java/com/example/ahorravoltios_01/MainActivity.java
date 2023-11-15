package com.example.ahorravoltios_01;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahorravoltios_01.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button login;
    TextView register;

    EditText user,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.buttonLogin);
        register=findViewById(R.id.textViewRegister);
        user=findViewById(R.id.editTextUserLogin);
        password=findViewById(R.id.editTextTextPasswordLogin);

        File fileReader = new File(getFilesDir(),"user.txt");
        try {
            FileWriter writer= new FileWriter(fileReader,true);
        }catch (IOException e){
            throw new RuntimeException();
        }

        ArrayList<User> usersList= listUsers(fileReader);

        Intent home= new Intent(getApplicationContext(),
                HomeActivity.class);
        Intent registerUser= new Intent(getApplicationContext(),
                UserRegisterActivity.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (user.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Ambos campos deben estar completos",
                        Toast.LENGTH_LONG).show();
            }else{
                for(User i:usersList){
                    if (i.getName().equals(user.getText().toString())||
                            i.getEmail().equals(user.getText().toString())||
                            i.getPhone().equals(user.getText().toString())){
                        if(i.getPassword().equals(password.getText().toString())){
                        Toast.makeText(getApplicationContext(),
                    "Los datos son correcto",Toast.LENGTH_LONG).show();
                            try {
                                sleep(500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            startActivity(home);
                            break;
                        }else {
                           Toast.makeText(getApplicationContext(),
                   "La contraseña es incorrecta",Toast.LENGTH_LONG).show();
                        }
                    }else {
                       Toast.makeText(getApplicationContext(),
                   "Este usuario no esta registrado",Toast.LENGTH_LONG).show();
                    }
                }
            }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerUser);
            }
        });

    }


    public ArrayList<User> listUsers(File fileUser){
        ArrayList<User> list= new ArrayList<>();
        try {
            FileReader fileReader= new FileReader(fileUser);
            BufferedReader reader= new BufferedReader(fileReader);
            String user;

            while ((user=reader.readLine())!=null){
                String[] userArray = user.split(",");
                String id= userArray[0];
                String name= userArray[1];
                String email= userArray[2];
                String phone= userArray[3];
                String password= userArray[4];

                User userObj= new User(id,name,email,phone,password);
                list.add(userObj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }


}