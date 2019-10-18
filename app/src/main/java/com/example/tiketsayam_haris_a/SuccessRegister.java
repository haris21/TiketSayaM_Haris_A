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

public class SuccessRegister extends AppCompatActivity {

    public Button btnexplore;
    public Animation anim1, anim2;
    public TextView texttitlereg1, texttitlereg2;
    public ImageView imgsuccesreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        //load variable
        btnexplore = findViewById(R.id.btnExplorer);
        texttitlereg1 = findViewById(R.id.textTitleReg);
        texttitlereg2 = findViewById(R.id.textTitleReg2);
        imgsuccesreg = findViewById(R.id.imgSuccesReg);

        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim_hightocenter);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.anim_belowtocenter);


        //load animation
        texttitlereg1.startAnimation(anim1);
        texttitlereg2.startAnimation(anim1);
        imgsuccesreg.startAnimation(anim1);
        btnexplore.startAnimation(anim2);


        btnexplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoHome = new Intent(SuccessRegister.this, HomeAct.class);
                startActivity(gotoHome);
            }
        });
    }
}
