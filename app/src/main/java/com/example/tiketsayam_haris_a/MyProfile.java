package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MyProfile extends AppCompatActivity {
    public LinearLayout btndetailmyticket;
    public Button btneditprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        btndetailmyticket = findViewById(R.id.btnDetailMyTicket);
        btneditprofile = findViewById(R.id.btnEditProfile);
        btndetailmyticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomydetailtikcet = new Intent(MyProfile.this, MyticketDetailAct.class);
                startActivity(gotomydetailtikcet);
            }
        });

        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoeditprofile = new Intent(MyProfile.this, EditMyProfileAct.class);
                startActivity(gotoeditprofile);
            }
        });
    }
}
