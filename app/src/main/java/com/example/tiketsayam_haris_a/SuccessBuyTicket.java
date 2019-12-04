package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessBuyTicket extends AppCompatActivity {

    public Button btnmydashboard, btnviewticket;
    public ImageView imgsuccesbuy;
    public TextView texttitle1, texttitle2;
    public Animation anim1, anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy_ticket);
        //load variable
        btnmydashboard = findViewById(R.id.btnMyDashboard);
        btnviewticket = findViewById(R.id.btnViewTicket);
        texttitle1 = findViewById(R.id.texttitle1);
        texttitle2 = findViewById(R.id.texttitle2);
        imgsuccesbuy = findViewById(R.id.imgsuccesbuy);

        //load animation
        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim_hightocenter);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.anim_belowtocenter);

        imgsuccesbuy.startAnimation(anim1);
        texttitle1.startAnimation(anim1);
        texttitle2.startAnimation(anim1);

        btnviewticket.startAnimation(anim2);
        btnmydashboard.startAnimation(anim2);


        btnmydashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(SuccessBuyTicket.this, HomeAct.class);
                startActivity(gotohome);
            }
        });

        btnviewticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyprofile = new Intent(SuccessBuyTicket.this, MyProfile.class);
                startActivity(gotomyprofile);
            }
        });


    }
}
