package com.examp.bienajuste;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class cartActivity extends AppCompatActivity {
    Integer tempv1,tempv2,tempv3,temptV;
    Integer tempcone, tempctwo, tempcthree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Integer tV, v1, v2, v3;

        final LinearLayout linear1 = (LinearLayout) findViewById(R.id.linear1);
        final LinearLayout linear2 = (LinearLayout) findViewById(R.id.linear2);
        final LinearLayout linear3 = (LinearLayout) findViewById(R.id.linear3);


        final TextView totalValue = (TextView) findViewById(R.id.totalValue);
        final Button exitButton = (Button) findViewById(R.id.exitButton);
        final TextView applyButton = (TextView) findViewById(R.id.applyButton);

        final TextView quantityValue1 = (TextView) findViewById(R.id.quantityValue1);
        final TextView quantityValue2 = (TextView) findViewById(R.id.quantityValue2);
        final TextView quantityValue3 = (TextView) findViewById(R.id.quantityValue3);

        final TextView priceValue1 = (TextView) findViewById(R.id.priceValue1);
        final TextView priceValue2 = (TextView) findViewById(R.id.priceValue2);
        final TextView priceValue3 = (TextView) findViewById(R.id.priceValue3);

        final Button plusButton1 = (Button) findViewById(R.id.plusButton1);
        final Button plusButton2 = (Button) findViewById(R.id.plusButton2);
        final Button plusButton3 = (Button) findViewById(R.id.plusButton3);

        final Button minusButton1 = (Button) findViewById(R.id.minusButton1);
        final Button minusButton2 = (Button) findViewById(R.id.minusButton2);
        final Button minusButton3 = (Button) findViewById(R.id.minusButton3);

        v1 = Cart.one*169000;
        v2 = Cart.two*179000;
        v3 = Cart.three*79000;

        tV=Cart.one*169000+Cart.two*179000+Cart.three*79000;
        totalValue.setText(tV.toString());

        quantityValue1.setText(Cart.one.toString());
        quantityValue2.setText(Cart.two.toString());
        quantityValue3.setText(Cart.three.toString());

        priceValue1.setText(v1.toString());
        priceValue2.setText(v2.toString());
        priceValue3.setText(v3.toString());

        if(Integer.parseInt(quantityValue1.getText().toString())==0)
            linear1.setVisibility(View.GONE);
        else
            linear1.setVisibility(View.VISIBLE);
        if(Integer.parseInt(quantityValue2.getText().toString())==0)
            linear2.setVisibility(View.GONE);
        else
            linear2.setVisibility(View.VISIBLE);
        if(Integer.parseInt(quantityValue3.getText().toString())==0)
            linear3.setVisibility(View.GONE);
        else
            linear3.setVisibility(View.VISIBLE);
        
        tempv1=v1;
        tempv2=v2;
        tempv3=v3;
        temptV=tV;
        
        tempcone=Cart.one;
        tempctwo=Cart.two;
        tempcthree=Cart.three;
        
        plusButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer v1, tV;
                tempcone++;
                v1 = tempcone*169000;
                tV=tempcone*169000+tempctwo*179000+tempcthree*79000;

                quantityValue1.setText(tempcone.toString());
                priceValue1.setText(v1.toString());
                totalValue.setText(tV.toString());
            }
        });
        plusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer v2, tV;
                tempctwo++;
                v2 = tempctwo*169000;
                tV=tempcone*169000+tempctwo*179000+tempcthree*79000;

                quantityValue2.setText(tempctwo.toString());
                priceValue2.setText(v2.toString());
                totalValue.setText(tV.toString());
            }
        });
        plusButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer v3, tV;
                tempcthree++;
                v3 = tempcthree*169000;
                tV=tempcone*169000+tempctwo*179000+tempcthree*79000;

                quantityValue3.setText(tempcthree.toString());
                priceValue3.setText(v3.toString());
                totalValue.setText(tV.toString());
            }
        });
        minusButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer v1, tV;
                if(tempcone-1>=0) {
                    tempcone--;
                    v1 = tempcone * 169000;
                    tV = tempcone * 169000 + tempctwo * 179000 + tempcthree * 79000;

                    quantityValue1.setText(tempcone.toString());
                    priceValue1.setText(v1.toString());
                    totalValue.setText(tV.toString());
                }
            }
        });
        minusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer v2, tV;
                if(tempctwo-1>=0) {
                    tempctwo--;
                    v2 = tempctwo * 169000;
                    tV = tempcone * 169000 + tempctwo * 179000 + tempcthree * 79000;

                    quantityValue2.setText(tempctwo.toString());
                    priceValue2.setText(v2.toString());
                    totalValue.setText(tV.toString());
                }
            }
        });
        minusButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer v3, tV;
                if(tempcthree-1>=0) {
                    tempcthree--;
                    v3 = tempcthree * 169000;
                    tV = tempcone * 169000 + tempctwo * 179000 + tempcthree * 79000;

                    quantityValue3.setText(tempcthree.toString());
                    priceValue3.setText(v3.toString());
                    totalValue.setText(tV.toString());
                }
            }
        });
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.one=Integer.parseInt(quantityValue1.getText().toString());
                Cart.two=Integer.parseInt(quantityValue2.getText().toString());
                Cart.three=Integer.parseInt(quantityValue3.getText().toString());


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog dialog;
                                AlertDialog.Builder builder = new AlertDialog.Builder(cartActivity.this);
                                builder.setMessage("Cart Applied & Server Saved!")
                                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        });
                                dialog=builder.create();
                                dialog.show();

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(cartActivity.this);
                                builder.setMessage("Something Wrong")
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
                DB_Handler DB_Handler = new DB_Handler("update", Cart.cartID, Cart.one, Cart.two, Cart.three, responseListener);
                RequestQueue queue = Volley.newRequestQueue(cartActivity.this);
                queue.add(DB_Handler);



            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
