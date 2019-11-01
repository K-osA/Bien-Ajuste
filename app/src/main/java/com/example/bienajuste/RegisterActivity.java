package com.example.bienajuste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText nameText = (EditText) findViewById(R.id.nameText);
        EditText idText = (EditText) findViewById(R.id.idText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);
        EditText ageText = (EditText) findViewById(R.id.ageText);
        EditText emailText = (EditText) findViewById(R.id.emailText);

        Button registerButton = (Button) findViewById(R.id.registerButton);
    }
}
