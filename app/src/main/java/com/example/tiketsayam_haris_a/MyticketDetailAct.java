package com.example.tiketsayam_haris_a;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyticketDetailAct extends AppCompatActivity {
    public LinearLayout btnback;
    public TextView namaticket, lokasi, jumlahticket, tanggalticket, jamticket, ketentuan;
    public Bundle bundle;
    public DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myticket_detail);

        //load variable
        namaticket = findViewById(R.id.namaTicket);
        lokasi = findViewById(R.id.lokasi);
//        jumlahticket = findViewById(R.id.jumlahTicket);
        tanggalticket = findViewById(R.id.tanggalTicket);
        jamticket = findViewById(R.id.jamTicket);
        ketentuan = findViewById(R.id.ketentuan);

        bundle = getIntent().getExtras();
        final String namawisata = bundle.getString("namawisata");

//        Toast.makeText(getApplicationContext(), "nama Wisata =" + namawisata, Toast.LENGTH_SHORT).show();

        reference = FirebaseDatabase.getInstance().getReference().child("wisata").child(namawisata);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namaticket.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                tanggalticket.setText(dataSnapshot.child("date_wisata").getValue().toString());
                jamticket.setText(dataSnapshot.child("time_wisata").getValue().toString());
                ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());
//                jumlahticket.setText(dataSnapshot.child("jumlahTiket").getValue().toString() + "Tickets");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnback = findViewById(R.id.btnBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
