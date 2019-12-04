package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends AppCompatActivity {
    public LinearLayout btndetailmyticket;
    public Button btneditprofile, btnbackhome, btnsignout;
    public TextView namaprofile, bioprofile;
    public CircleImageView photoprofile;
    public DatabaseReference reference, reference2;
    public RecyclerView myticketplace;
    public String USERNAME_KEY = "usernamekey";
    public String username_key = "";
    public String username_key_new;

    ArrayList<MyTicket> myTicket;
    TicketAdapter ticketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        //load local storage
        getusernamelocal();

        //load variable
        namaprofile = findViewById(R.id.namaProfile);
        bioprofile = findViewById(R.id.bioProfile);
        photoprofile = findViewById(R.id.photoProfile);
        btndetailmyticket = findViewById(R.id.btnDetailMyTicket);
        btneditprofile = findViewById(R.id.btnEditProfile);
        btnbackhome = findViewById(R.id.btnBackHome);
        myticketplace = findViewById(R.id.myTicketPlace);
        btnsignout = findViewById(R.id.btnSignOut);

        myticketplace.setLayoutManager(new LinearLayoutManager(this));
        myTicket = new ArrayList<MyTicket>();

        //load data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namaprofile.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bioprofile.setText(dataSnapshot.child("bio").getValue().toString());
                Picasso.get().load(dataSnapshot.child("url_photo_profile").getValue().toString()).fit().centerCrop().into(photoprofile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Function2
//        btndetailmyticket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent gotomydetailtikcet = new Intent(MyProfile.this, MyticketDetailAct.class);
//                startActivity(gotomydetailtikcet);
//            }
//        });

        reference2 = FirebaseDatabase.getInstance().getReference().child("MyTicket").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    MyTicket p = dataSnapshot1.getValue(MyTicket.class);
                    myTicket.add(p);
                }
                ticketAdapter = new TicketAdapter(MyProfile.this, myTicket);
                myticketplace.setAdapter(ticketAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoeditprofile = new Intent(MyProfile.this, EditMyProfileAct.class);
                startActivity(gotoeditprofile);
            }
        });

        btnbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(MyProfile.this, HomeAct.class);
                startActivity(gotohome);
            }
        });

        btnsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key, null);
                editor.apply();

                Intent gotosignin = new Intent(MyProfile.this, SignInAct.class);
                startActivity(gotosignin);
                finish();
            }
        });
    }

    public void getusernamelocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
