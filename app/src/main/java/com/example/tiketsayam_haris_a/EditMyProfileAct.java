package com.example.tiketsayam_haris_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class EditMyProfileAct extends AppCompatActivity {
    public LinearLayout btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);

        btnback = findViewById(R.id.btnBack);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyprofile = new Intent(EditMyProfileAct.this, MyProfile.class);
                startActivity(gotomyprofile);
            }
        });
    }
}
