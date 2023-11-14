package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahorravoltios_01.models.User;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class UserRegisterActivity extends AppCompatActivity {

    Button register;
    TextInputLayout name,email,phone,password1,password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        register=findViewById(R.id.buttonUserRegister);
        name=findViewById(R.id.name_user);
        email=findViewById(R.id.email_user);
        phone=findViewById(R.id.phone_user);
        password1=findViewById(R.id.password1_user);
        password2=findViewById(R.id.password2_user);

        Intent home=new Intent(getApplicationContext(),
                HomeActivity.class);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUser()){
                    User user=createUser();
                    storageUser(user);
                    startActivity(home);
                }else{
                    Toast.makeText(getApplicationContext(),
                    "Asegurarse de que la información esta coorecta",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public boolean validateUser(){
        boolean state= true;

        if (name.getEditText().getText().toString().isEmpty()){
            name.setBoxBackgroundColor(Color.RED);
            state=false;
        }
        if (email.getEditText().getText().toString().isEmpty()){
            email.setBoxBackgroundColor(Color.RED);
            state=false;
        }
        if (phone.getEditText().getText().toString().isEmpty()){
            phone.setBoxBackgroundColor(Color.RED);
            state=false;
        }
        if (password1.getEditText().getText().toString().isEmpty()){
            password1.setBoxBackgroundColor(Color.RED);
            state=false;
        }
        if (password2.getEditText().getText().toString().isEmpty()){
            password2.setBoxBackgroundColor(Color.RED);
            state=false;
        }
        if (!password1.getEditText().getText().toString().equals(
            password2.getEditText().getText().toString())){
            password1.setBoxBackgroundColor(Color.RED);
            password2.setBoxBackgroundColor(Color.RED);
            state=false;
        }

        return state;
    }

    public User createUser(){
      String name_user=name.getEditText().getText().toString();
      String id=idGenerator(name_user);
      String email_user=email.getEditText().getText().toString();
      String phone_user=phone.getEditText().getText().toString();
      String password= password1.getEditText().getText().toString();

      User user= new User(id,name_user,email_user,phone_user,password);

      return user;
    }

    public String idGenerator(String name){
        String id="";
        for (int i=0; i<2;i++){
            int random1= (int)(Math.random()*name.length());
            int random2= (int)(Math.random()*10000);
            //Luis  0-4 --> aleatorio 2
            // i  --> id= i1522s9631
            id+=name.charAt(random1);
            id+=random2;
        }
        return id;
    }
    public void storageUser(User user){
        File fileUser= new File(getFilesDir(),"user.txt");

       try {
            FileWriter writer= new FileWriter(fileUser,true);
            BufferedWriter bufferedWriter=new BufferedWriter(writer);
            bufferedWriter.write( user.getIdUser()+","+
                                     user.getName()+","+
                                     user.getEmail()+","+
                                     user.getPhone()+","+
                                     user.getPassword()
                                );
            bufferedWriter.newLine();
            bufferedWriter.close();
       }catch (Exception e){
           e.printStackTrace();
       }


    }


}