package com.examp.bienajuste;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerButton = (TextView) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();
                if(userID.equals("") || userPassword.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Check Empty")
                            .setNegativeButton("Confirm",null)
                            .create()
                            .show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                String userID = jsonResponse.getString("userID");
                                String userPassword = jsonResponse.getString("userPassword");
                                String userName = jsonResponse.getString("userName");
                                int userAge = jsonResponse.getInt("userAge");
                                String userEmail = jsonResponse.getString("userEmail");
                                String userAddress = jsonResponse.getString("userAddress");
                                double userFootsize = jsonResponse.getDouble("userFootsize");
                                int userGender = jsonResponse.getInt("userGender");

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPassword",userPassword);
                                intent.putExtra("userName",userName);
                                intent.putExtra("userAge",userAge);
                                intent.putExtra("userEmail",userEmail);
                                intent.putExtra("userAddress",userAddress);
                                intent.putExtra("userFootsize",userFootsize);
                                intent.putExtra("userGender",userGender);

                                LoginActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed, Check ID or Password")
                                        .setNegativeButton("Try again",null)
                                        .create()
                                        .show();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                DB_Handler DB_Handler = new DB_Handler(userID,userPassword,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(DB_Handler);
            }
        });
    }
}
