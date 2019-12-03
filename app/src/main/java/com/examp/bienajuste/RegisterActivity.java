package com.examp.bienajuste;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    private int userGender=0;
    private boolean validate=false;

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
        final Button validateButton = (Button) findViewById(R.id.validateButton);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        RadioGroup gendergroup;

        gendergroup = (RadioGroup) findViewById(R.id.genderGroup);
        gendergroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                if(validate) return;
                if(userID.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("ID is empty")
                            .setPositiveButton("Confirm",null)
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("You can use ID!")
                                        .setPositiveButton("Confirm",null)
                                        .create()
                                        .show();
                                idText.setEnabled(false);
                                validate=true;
                                idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Already used ID")
                                        .setNegativeButton("Confirm",null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                DB_Handler DB_Handler = new DB_Handler(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(DB_Handler);

            }
        });


        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String userName = nameText.getText().toString();
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String ChkuserAge = ageText.getText().toString();
                String userEmail = emailText.getText().toString();
                String userAddress = addressText.getText().toString();
                String ChkuserFootsize = footsizeText.getText().toString();

                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Need check ID")
                            .setNegativeButton("Confirm",null)
                            .create()
                            .show();
                    return;
                }

                if(userName.equals("") || userID.equals("") || userPassword.equals("") || ChkuserAge.equals("") || userEmail.equals("") || userAddress.equals("") || ChkuserFootsize.equals("") || userGender==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Check Empty")
                            .setNegativeButton("Confirm",null)
                            .create()
                            .show();
                    return;
                }
                int userAge = Integer.parseInt(ageText.getText().toString());
                double userFootsize = Double.parseDouble(footsizeText.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                AlertDialog dialog;
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register completed!")
                                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                RegisterActivity.this.startActivity(intent);
                                            }
                                        });
                                dialog=builder.create();
                                dialog.show();

                            }

                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Confirm",null)
                                        .create()
                                        .show();
                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                DB_Handler DB_Handler = new DB_Handler(userID, userPassword, userName, userAge, userEmail, userAddress, userFootsize, userGender, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(DB_Handler);
            }
        });
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i){
            //if userGender=0 not selected
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
