package com.examp.bienajuste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView shoesImage1 = (ImageView) findViewById(R.id.shoesImage1);
        final ImageView shoesImage2 = (ImageView) findViewById(R.id.shoesImage2);
        final ImageView shoesImage3 = (ImageView) findViewById(R.id.shoesImage3);
        final Button cartButton = (Button)findViewById(R.id.cartButton);

        Intent intent = getIntent();

        UserInfo info = new UserInfo(intent.getStringExtra("userPassword"),
                intent.getStringExtra("userName"),
                intent.getIntExtra("userAge",0),
                intent.getStringExtra("userEmail"),
                intent.getStringExtra("userAddress"),
                intent.getIntExtra("userFootsize",0),
                intent.getIntExtra("userGender",0)
        );

        User u = new User(intent.getIntExtra("cartID",0),info);
        Cart c = new Cart(intent.getIntExtra("cartID",0),intent.getIntExtra("one",0),intent.getIntExtra("two",0), intent.getIntExtra("three",0));
        shoesImage1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, shoesDescriptionActivity.class);
                intent.putExtra("shoesId",1);
                MainActivity.this.startActivity(intent);
            }
        });

        shoesImage2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, shoesDescriptionActivity.class);
                intent.putExtra("shoesId",2);
                MainActivity.this.startActivity(intent);
            }
        });

        shoesImage3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, shoesDescriptionActivity.class);
                intent.putExtra("shoesId",3);
                MainActivity.this.startActivity(intent);
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cartActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}