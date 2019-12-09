package com.examp.bienajuste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MeasureActivity extends AppCompatActivity {
    private static final String TAG = "TEJava";
    private static double footSize = 0;
    private File tempFile, compFile;
    private Boolean isCamera = false;

    Intent intent = getIntent();
    private String path = intent.getExtras().getString("name");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);

        final Button a4Button = (Button) findViewById(R.id.a4Button);
        final Button lineButton = (Button) findViewById(R.id.lineButton);
        final Button returnButton = (Button) findViewById(R.id.returnButton);

        a4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawA4();
            }
        });

        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawLine();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MeasureIntent = new Intent(MeasureActivity.this, RegisterActivity.class);
                MeasureIntent.putExtra("footSize", footSize);
                finish();
            }
        });

        ImageView imageView = findViewById(R.id.imageView);
        ImageResizeUtils.resizeFile(tempFile, tempFile, 1280, isCamera);

    }

    private void drawA4() {

    }

    private void drawLine() {

    }

}
