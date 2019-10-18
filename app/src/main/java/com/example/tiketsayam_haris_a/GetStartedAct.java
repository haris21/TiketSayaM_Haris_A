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

public class GetStartedAct extends AppCompatActivity {
    public Button btnsign, btnnewaccount;
    public TextView textgetstarted;
    public Animation appgetstarted, buttonanim;
    public ImageView imggetstarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        // load variable
        btnsign = findViewById(R.id.btnsign);
        btnnewaccount = findViewById(R.id.btnNewAccount);
        textgetstarted = findViewById(R.id.textGetstarte);
        imggetstarted = findViewById(R.id.imgetstarted);

        //load animation
        appgetstarted = AnimationUtils.loadAnimation(this, R.anim.anim_hightocenter);
        buttonanim = AnimationUtils.loadAnimation(this, R.anim.anim_belowtocenter);

        //run animation
        textgetstarted.startAnimation(appgetstarted);
        imggetstarted.startAnimation(appgetstarted);
        btnsign.startAnimation(buttonanim);
        btnnewaccount.startAnimation(buttonanim);


        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formsign = new Intent(GetStartedAct.this, SignInAct.class);
                startActivity(formsign);
//                finish();
            }
        });

        btnnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formReg = new Intent(GetStartedAct.this, RegisterOneAct.class);
                startActivity(formReg);
//                finish();
            }
        });
    }
}
