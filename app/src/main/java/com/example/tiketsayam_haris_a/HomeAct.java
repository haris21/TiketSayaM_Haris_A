package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAct extends AppCompatActivity {
    public LinearLayout btnpisa;
    CircleImageView btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnpisa = findViewById(R.id.btnPisa);
        btnProfile = findViewById(R.id.btnProfile);
        btnpisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetailpisa = new Intent(HomeAct.this, TicketDetailAct.class);
                startActivity(gotodetailpisa);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyprofile = new Intent(HomeAct.this, MyProfile.class);
                startActivity(gotomyprofile);
            }
        });
    }
}
