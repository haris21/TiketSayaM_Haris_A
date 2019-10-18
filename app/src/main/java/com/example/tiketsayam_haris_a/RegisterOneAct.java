package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterOneAct extends AppCompatActivity {
    public LinearLayout btnback;
    public Button btncontinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);

        btnback = findViewById(R.id.btnBack);
        btncontinue = findViewById(R.id.btnContinue);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosignin = new Intent(RegisterOneAct.this, SignInAct.class);
                startActivity(gotosignin);
            }
        });

        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototoregistertwo=new Intent(RegisterOneAct.this,RegisterTwo.class);
                startActivity(gototoregistertwo);
            }
        });
    }
}
