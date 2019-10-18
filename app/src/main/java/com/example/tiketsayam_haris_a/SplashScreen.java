package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    public Animation appSplash, appsplashtext;
    public TextView applogotext;
    public ImageView applogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //identify var
        applogotext = findViewById(R.id.appLogoText);
        applogo = findViewById(R.id.appLogo);

        //load animation
        appSplash = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        appsplashtext = AnimationUtils.loadAnimation(this, R.anim.anim_belowtocenter);


        //run animation
        applogo.startAnimation(appSplash);
        applogotext.startAnimation(appsplashtext);

        //animation with timer 2 second
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, GetStartedAct.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
