package com.example.bienajuste;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private int userGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText ageText = (EditText) findViewById(R.id.ageText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText addressText = (EditText) findViewById(R.id.addressText);
        final EditText footsizeText = (EditText) findViewById(R.id.footsizeText);
        RadioGroup gendergroup;

        gendergroup = (RadioGroup) findViewById(R.id.genderGroup);
        gendergroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String userName = nameText.getText().toString();
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                int userAge = Integer.parseInt(ageText.getText().toString());
                String userEmail = emailText.getText().toString();
                String userAddress = addressText.getText().toString();
                Double userFootsize = Double.parseDouble(footsizeText.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register completed!")
                                        .setPositiveButton("Confirm",null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }

                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Try again",null)
                                        .create()
                                        .show();
                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userAge, userEmail, userAddress, userFootsize, userGender, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i){
            if(i==R.id.maleButton){
                userGender=1; //male
            }
            else if(i==R.id.femaleButton){
                userGender=2; //female
            }
            else if(i==R.id.otherButton){
                userGender=3; //other
            }
        }
    };
}
