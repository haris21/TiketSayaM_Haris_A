package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterTwo extends AppCompatActivity {
    public LinearLayout btnback2;
    public Button btncontinue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        btnback2 = findViewById(R.id.btnBack2);
        btnback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregisterone = new Intent(RegisterTwo.this, RegisterOneAct.class);
                startActivity(gotoregisterone);
            }
        });

        btncontinue2 = findViewById(R.id.btnContinue2);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosuccess = new Intent(RegisterTwo.this, SuccessRegister.class);
                startActivity(gotosuccess);
            }
        });
    }
}
