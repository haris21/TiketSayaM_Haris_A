package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.content.SharedPreferences;
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

    public String USERNAME_KEY = "usernamekey";
    public String username_key = "";
    public String username_key_new;

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
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashScreen.this, GetStartedAct.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 2000);

        getusernamelocal();
    }

    public void getusernamelocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
        if (username_key_new.isEmpty()) {
//            Toast.makeText(getApplicationContext(), username_key_new, Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent gotosign = new Intent(SplashScreen.this, GetStartedAct.class);
                    startActivity(gotosign);
                    finish();
                }
            }, 2000);

        } else {
//            Toast.makeText(getApplicationContext(), username_key_new, Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent gotohome = new Intent(SplashScreen.this, HomeAct.class);
                    startActivity(gotohome);
                    finish();
                }
            }, 2000);
        }
    }
}
