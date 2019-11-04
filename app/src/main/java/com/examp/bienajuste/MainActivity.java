package com.examp.bienajuste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        UserInfo info = new UserInfo(intent.getStringExtra("userPassword"),
                intent.getStringExtra("userName"),
                intent.getIntExtra("userAge",0),
                intent.getStringExtra("userEmail"),
                intent.getStringExtra("userAddress"),
                intent.getIntExtra("userFootsize",0),
                intent.getIntExtra("userGender",0)
        );

        User u = new User(intent.getStringExtra("userID"), info);

    }
}