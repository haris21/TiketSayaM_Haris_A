package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import java.util.Random;

public class TicketCheckOutACt extends AppCompatActivity {
    public LinearLayout btnback;
    public ImageView imgwarning;
    public Button btnpay, btnplus, btnminus;
    public TextView jmlhticket, texttotalharga, userbalance, namawisata, lokasiwisata, ketentuan;
    public Integer jumlahtiket = 0, balance = 0, totalhrga = 0, hargatiket = 0;
    public Bundle bundle;
    public DatabaseReference reference1, reference2, reference3;
    public String USERNAME_KEY = "usernamekey";
    public String username_key = "";
    public String username_key_new;
    public String dateWisata = "", timeWisata = "";
    public Integer noTransaksi = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_check_out_act);
        //call function take data from local storage
        getusernamelocal();

        //take data from intent
        bundle = getIntent().getExtras();
        final String jenisTiket = bundle.getString("jenistiket");

        //load variable
        btnback = findViewById(R.id.btnBack);
        btnpay = findViewById(R.id.btnPay);
        btnplus = findViewById(R.id.btnPlus);
        btnminus = findViewById(R.id.btnMinus);
        jmlhticket = findViewById(R.id.jmltiket);
        texttotalharga = findViewById(R.id.texttotalharga);
        userbalance = findViewById(R.id.userBalance);
        imgwarning = findViewById(R.id.imgWarning);
        namawisata = findViewById(R.id.namaWisata);
        lokasiwisata = findViewById(R.id.lokasiWisata);
        ketentuan = findViewById(R.id.ketentuanWisata);


        //set kondisi awal
        jmlhticket.setText(jumlahtiket.toString());
        btnminus.animate().alpha(0).setDuration(200).start();
        btnminus.setEnabled(false);
        texttotalharga.setText("US$ " + totalhrga + "");
        imgwarning.setVisibility(View.GONE);

        //take data from firebase
        //refrence wisata
        reference1 = FirebaseDatabase.getInstance().getReference().child("wisata").child(jenisTiket);
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //show data from firebase to form
                namawisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                lokasiwisata.setText(dataSnapshot.child("lokasi").getValue().toString());
                ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());
                dateWisata = dataSnapshot.child("date_wisata").getValue().toString();
                timeWisata = dataSnapshot.child("time_wisata").getValue().toString();
                hargatiket = Integer.valueOf(dataSnapshot.child("harga_tiket").getValue().toString());
                totalhrga = hargatiket * jumlahtiket;
                texttotalharga.setText("US$ " + totalhrga.toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //refrence user
        reference2 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                balance = Integer.valueOf(dataSnapshot.child("user_balance").getValue().toString());
                userbalance.setText("US$ " + balance + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
                texttotalharga.setText("US$ " + totalhrga.toString());

                if (totalhrga < balance) {
                    btnpay.animate().alpha(1).setDuration(200).start();
                    btnpay.setEnabled(true);
                    userbalance.setTextColor(Color.parseColor("#203DD1"));
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
                texttotalharga.setText("US$ " + totalhrga.toString());

                if (totalhrga > balance) {
                    btnpay.animate().alpha(0).setDuration(200).start();
                    btnpay.setEnabled(false);
                    userbalance.setTextColor(Color.RED);
                    imgwarning.animate().alpha(1).setDuration(200).start();
                    imgwarning.setVisibility(View.VISIBLE);
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gototicketdetail = new Intent(TicketCheckOutACt.this, TicketDetailAct.class);
                //put data to intent
                gototicketdetail.putExtra("jenisTiket", jenisTiket);
                startActivity(gototicketdetail);
            }
        });


        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reference myticket (save data user & my ticket)
                reference3 = FirebaseDatabase.getInstance().getReference().child("MyTicket").child(username_key_new).child(namawisata.getText().toString() + noTransaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child("nama_wisata").setValue(namawisata.getText().toString());
                        reference3.getRef().child("lokasi").setValue(lokasiwisata.getText().toString());
                        reference3.getRef().child("ketentuan").setValue(ketentuan.getText().toString());
                        reference3.getRef().child("jumlahTiket").setValue(jumlahtiket);
                        reference3.getRef().child("dateWisata").setValue(dateWisata);
                        reference3.getRef().child("timeWisata").setValue(timeWisata);

                        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int saldo = balance - totalhrga;
                                reference2.getRef().child("user_balance").setValue(saldo);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        Intent gotosusccesbuyticket = new Intent(TicketCheckOutACt.this, SuccessBuyTicket.class);
                        startActivity(gotosusccesbuyticket);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    public void getusernamelocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
