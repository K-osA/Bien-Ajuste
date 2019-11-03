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
        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");
        String userName = intent.getStringExtra("userName");
        int userAge = intent.getIntExtra("userAge",0);
        String userEmail = intent.getStringExtra("userEmail");
        String userAddress = intent.getStringExtra("userAddress");
        int userFootsize= intent.getIntExtra("userFootsize",0);
        int userGender = intent.getIntExtra("userGender",0);
        int isManager_int = intent.getIntExtra("isManager",0);
        boolean isManager=false;

        if(isManager_int == 1)
            isManager=true;

    }
}
