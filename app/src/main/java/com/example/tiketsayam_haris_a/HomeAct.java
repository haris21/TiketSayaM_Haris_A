package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAct extends AppCompatActivity {
    public LinearLayout btnpisa, btntori, btnpagoda, btnborobudur, btnsphinx, btnmonas;
    public CircleImageView btnProfile;
    public TextView usernamehome, userbiohome, userbalancehome;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //call function take data from local storage
        getusernamelocal();

//        Toast.makeText(getApplicationContext(), username_key_new, Toast.LENGTH_SHORT).show();
        //load variable
        btnpisa = findViewById(R.id.btnPisa);
        btntori = findViewById(R.id.btnTori);
        btnpagoda = findViewById(R.id.btnPagoda);
        btnborobudur = findViewById(R.id.btnBorobudur);
        btnsphinx = findViewById(R.id.btnSphinx);
        btnmonas = findViewById(R.id.btnMonas);
        btnProfile = findViewById(R.id.btnProfile);
        usernamehome = findViewById(R.id.userNameHome);
        userbiohome = findViewById(R.id.userBioHome);
        userbalancehome = findViewById(R.id.userBalanceHome);

        //take reference from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //show user data from firebase to form
//                Toast.makeText(getApplicationContext(), dataSnapshot.child("nama_lengkap").getValue().toString(), Toast.LENGTH_SHORT).show();
                usernamehome.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                userbiohome.setText(dataSnapshot.child("bio").getValue().toString());
                userbalancehome.setText("$ " + dataSnapshot.child("user_balance").getValue().toString());
                Picasso.get().load(dataSnapshot.child("url_photo_profile")
                        .getValue().toString()).centerCrop().fit().into(btnProfile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //functions
        btnpisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetailpisa = new Intent(HomeAct.this, TicketDetailAct.class);
                gotodetailpisa.putExtra("jenisTiket", "Pisa");
                startActivity(gotodetailpisa);
            }
        });

        btntori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetailtori = new Intent(HomeAct.this, TicketDetailAct.class);
                gotodetailtori.putExtra("jenisTiket", "Torri");
                startActivity(gotodetailtori);

            }
        });

        btnpagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetailpagoda = new Intent(HomeAct.this, TicketDetailAct.class);
                gotodetailpagoda.putExtra("jenisTiket", "Pagoda");
                startActivity(gotodetailpagoda);

            }
        });

        btnborobudur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetailborobudur = new Intent(HomeAct.this, TicketDetailAct.class);
                gotodetailborobudur.putExtra("jenisTiket", "Candi");
                startActivity(gotodetailborobudur);

            }
        });

        btnsphinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetailsphinx = new Intent(HomeAct.this, TicketDetailAct.class);
                gotodetailsphinx.putExtra("jenisTiket", "Sphinx");
                startActivity(gotodetailsphinx);

            }
        });

        btnmonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetailmonas = new Intent(HomeAct.this, TicketDetailAct.class);
                gotodetailmonas.putExtra("jenisTiket", "Monas");
                startActivity(gotodetailmonas);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyprofile = new Intent(HomeAct.this, MyProfile.class);
                startActivity(gotomyprofile);
            }
        });
    }

    //function for to take data from local storage
    public void getusernamelocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}