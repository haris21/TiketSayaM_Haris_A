package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class TicketDetailAct extends AppCompatActivity {
    public LinearLayout btnback;
    public Button btnbuyticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        btnback = findViewById(R.id.btnBack);
        btnbuyticket = findViewById(R.id.btnBuyTicket);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(TicketDetailAct.this, HomeAct.class);
                startActivity(gotohome);
            }
        });

        btnbuyticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocheckout = new Intent(TicketDetailAct.this, TicketCheckOutACt.class);
                startActivity(gotocheckout);
            }
        });
    }
}
