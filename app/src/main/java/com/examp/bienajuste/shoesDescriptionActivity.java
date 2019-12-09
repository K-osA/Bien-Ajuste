package com.examp.bienajuste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class shoesDescriptionActivity extends AppCompatActivity {

    Integer quantity=0, shoesId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_description);
        final ImageView shoesDescriptionImage = (ImageView) findViewById(R.id.shoesDescriptionImage);
        final TextView shoesDescriptionText = (TextView) findViewById(R.id.shoesDescriptionText);
        final TextView quantityValue = (TextView) findViewById(R.id.quantityValue);
        final Button addcartButton = (Button) findViewById(R.id.addcartButton);
        final Button modelingButton = (Button) findViewById(R.id.modelingButton);
        final Button exitButton = (Button) findViewById(R.id.exitButton);
        final Button plusButton = (Button) findViewById(R.id.plusButton);
        final Button minusButton = (Button) findViewById(R.id.minusButton);

        Intent intent = getIntent();

        shoesId = intent.getIntExtra("shoesId",0);

        if(shoesId==1){
            shoesDescriptionImage.setImageDrawable(getResources().getDrawable(R.drawable.a));
            shoesDescriptionText.setText("헤리티지 룩, 지지력과 안정감을 더하다/에어 조던 4 레트로는 발을 단단하게 고정하여 안정성을 높이는 중창으로 경량의 쿠셔닝을 구현합니다. 1989년 오리지널을 기념하는 복고풍 디테일이 돋보입니다.");
        }

        else if(shoesId==2){
            shoesDescriptionImage.setImageDrawable(getResources().getDrawable(R.drawable.b));
            shoesDescriptionText.setText("현대 미술과 모던한 스포츠웨어의 만남/20세기의 다양한 미술 운동이 제시한 컬러와 텍스처에서 영감을 얻은 나이키 에어맥스 270 리액트는 가벼운 소재를 레이어 스타일로 적용하여 뛰어난 스타일과 착화감을 선사하는 현대적인 스타일로 탄생했습니다.");
        }

        else if(shoesId==3){
            shoesDescriptionImage.setImageDrawable(getResources().getDrawable(R.drawable.c));
            shoesDescriptionText.setText("유산소 트레이닝을 지원하다/나이키 플렉스 TR8 트레이닝화는 체육관 안팎에서 펼쳐지는 고강도 유산소 트레이닝에 최적화 되었습니다. 가벼운 메쉬로 공기흐름이 우수하며, 지지력을 보강해 좌 & 우 운동시 민첩성을 더합니다.");
        }
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityValue.setText(quantity.toString());

            }
        });
        minusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(quantity-1>=0) {
                    quantity--;
                    quantityValue.setText(quantity.toString());
                }
            }
        });

        addcartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(shoesId==1) {
                    Cart.one+=Integer.parseInt(quantityValue.getText().toString());
                    finish();
                }

                else if(shoesId==2) {
                    Cart.two+=Integer.parseInt(quantityValue.getText().toString());
                    finish();
                }
                else if(shoesId==3) {
                    Cart.three+=Integer.parseInt(quantityValue.getText().toString());
                    finish();
                }
            }
        });
        modelingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(shoesDescriptionActivity.this, Show3DModelActivity.class);
                intent.putExtra("shoesId",shoesId);
                shoesDescriptionActivity.this.startActivity(intent);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
