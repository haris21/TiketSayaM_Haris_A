package com.example.tiketsayam_haris_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MyticketDetailAct extends AppCompatActivity {
    public LinearLayout btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myticket_detail);

        btnback = findViewById(R.id.btnBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyprofile = new Intent(MyticketDetailAct.this, MyProfile.class);
                startActivity(gotomyprofile);
            }
        });
    }
}
