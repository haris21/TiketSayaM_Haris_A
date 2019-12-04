package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TicketDetailAct extends AppCompatActivity {
    public LinearLayout btnback;
    public Button btnbuyticket;
    public TextView titleticket, locationticket,
            phototicket, wifiticket, festivalticket, descticket;

    public ImageView headerticket;

    Bundle bundle;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        //load variable
        btnback = findViewById(R.id.btnBack);
        btnbuyticket = findViewById(R.id.btnBuyTicket);
        titleticket = findViewById(R.id.titleTicket);
        locationticket = findViewById(R.id.locationTicket);
        phototicket = findViewById(R.id.photoTicket);
        wifiticket = findViewById(R.id.wifiTicket);
        festivalticket = findViewById(R.id.festivalTicket);
        descticket = findViewById(R.id.descTicket);
        headerticket = findViewById(R.id.headerdTicket);

        //take data from intent
        bundle = getIntent().getExtras();
        final String jenisTiket = bundle.getString("jenisTiket");

        //load reference from firebase
        reference = FirebaseDatabase.getInstance().getReference().
                child("wisata").child(jenisTiket);

        //take data from firebase
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                titleticket.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                locationticket.setText(dataSnapshot.child("lokasi").getValue().toString());
                phototicket.setText(dataSnapshot.child("is_photo_spot").getValue().toString());
                wifiticket.setText(dataSnapshot.child("is_wifi").getValue().toString());
                festivalticket.setText(dataSnapshot.child("is_festival").getValue().toString());
                descticket.setText(dataSnapshot.child("short_desc").getValue().toString());
                Picasso.get().load(dataSnapshot.child("url_thumbnail").getValue().toString()).centerCrop().fit().into(headerticket);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //functions
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
                //put data to intent
                gotocheckout.putExtra("jenistiket", jenisTiket);
                startActivity(gotocheckout);
            }
        });
    }
}
