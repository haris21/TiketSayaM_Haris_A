package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TicketCheckOutACt extends AppCompatActivity {
    public LinearLayout btnback;
    public ImageView imgwarning;
    public Button btnpay, btnplus, btnminus;
    public TextView jmlhticket, texttotalharga, textsaldo;
    public Integer jumlahtiket = 1, balance = 200, totalhrga = 0, hargatiket = 75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_check_out_act);
        //load variable
        btnback = findViewById(R.id.btnBack);
        btnpay = findViewById(R.id.btnPay);
        btnplus = findViewById(R.id.btnPlus);
        btnminus = findViewById(R.id.btnMinus);
        jmlhticket = findViewById(R.id.jmltiket);
        texttotalharga = findViewById(R.id.texttotalharga);
        textsaldo = findViewById(R.id.textsaldo);
        imgwarning = findViewById(R.id.imgWarning);


        //set kondisi awal
        jmlhticket.setText(jumlahtiket.toString());
        btnminus.animate().alpha(0).setDuration(200).start();
        btnminus.setEnabled(false);
        texttotalharga.setText("USS " + totalhrga + "");
        textsaldo.setText("USS " + balance + "");
        totalhrga = hargatiket * jumlahtiket;
        texttotalharga.setText("USS " + totalhrga.toString());
        imgwarning.setVisibility(View.GONE);

        //set button
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahtiket -= 1;
                jmlhticket.setText(jumlahtiket.toString());
                if (jumlahtiket < 2) {
                    btnminus.animate().alpha(0).setDuration(200).start();
                    btnminus.setEnabled(false);
                }
                totalhrga = hargatiket * jumlahtiket;
                texttotalharga.setText("USS " + totalhrga.toString());

                if (totalhrga < balance) {
                    btnpay.animate().alpha(1).setDuration(200).start();
                    btnpay.setEnabled(true);
                    textsaldo.setTextColor(Color.parseColor("#203DD1"));
                    imgwarning.animate().alpha(0).setDuration(200).start();
                    imgwarning.setVisibility(View.GONE);
                }
            }
        });

        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahtiket += 1;
                jmlhticket.setText(jumlahtiket.toString());
                if (jumlahtiket > 1) {
                    btnminus.animate().alpha(1).setDuration(200).start();
                    btnminus.setEnabled(true);
                }
                totalhrga = hargatiket * jumlahtiket;
                texttotalharga.setText("USS " + totalhrga.toString());

                if (totalhrga > balance) {
                    btnpay.animate().alpha(0).setDuration(200).start();
                    btnpay.setEnabled(false);
                    textsaldo.setTextColor(Color.RED);
                    imgwarning.animate().alpha(1).setDuration(200).start();
                    imgwarning.setVisibility(View.VISIBLE);
                }
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototicketdetail = new Intent(TicketCheckOutACt.this, TicketDetailAct.class);
                startActivity(gototicketdetail);
            }
        });

        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosusccesbuyticket = new Intent(TicketCheckOutACt.this, SuccessBuyTicket.class);
                startActivity(gotosusccesbuyticket);
            }
        });
    }
}
