package com.example.ahorravoltios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class UserRegisterActivity extends AppCompatActivity {

    Button register;
    TextInputLayout name;
    EditText nameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        register=findViewById(R.id.buttonUserRegister);
        name=findViewById(R.id.name_user);
        nameEdit=name.getEditText();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(nameEdit.getText().toString());

                System.out.println(name.getEditText().getText().toString());
            }
        });
    }
}