package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignInAct extends AppCompatActivity {
    public TextView btnsign2;
    public Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnsign2 = findViewById(R.id.btnsign2);
        btnlogin = findViewById(R.id.btnlogin);

        btnsign2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formReg = new Intent(SignInAct.this, RegisterOneAct.class);
                startActivity(formReg);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoHome = new Intent(SignInAct.this, HomeAct.class);
                startActivity(gotoHome);
            }
        });
    }
}
