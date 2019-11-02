package com.example.bienajuste;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class RegisterActivity extends AppCompatActivity {
    private RadioGroup gendergroup;
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

        gendergroup = (RadioGroup) findViewById(R.id.genderGroup);
        gendergroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        Button registerButton = (Button) findViewById(R.id.registerButton);
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i){
            if(i==R.id.maleButton){
                //male
            }
            else if(i==R.id.femaleButton){
                //female
            }
            else if(i==R.id.otherButton){
                //other gender
            }
        }
    }
}
