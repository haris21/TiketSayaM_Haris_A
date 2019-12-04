package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOneAct extends AppCompatActivity {
    public LinearLayout btnback;
    public Button btncontinue;
    public EditText username, password, email;
    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);
        //load variabel
        btnback = findViewById(R.id.btnBack);
        btncontinue = findViewById(R.id.btnContinue);
        username = findViewById(R.id.edtUserName);
        password = findViewById(R.id.edtPassword);
        email = findViewById(R.id.edtEmail);

        //load function
        //goto prev page
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //goto next page
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrnme = username.getText().toString().trim();
                String eml = email.getText().toString().trim();
                String psswd = password.getText().toString().trim();
                if (TextUtils.isEmpty(usrnme)) {
                    boolean isEmptyFields = true;
                    username.setError("User name can't empty");
                }
                if (TextUtils.isEmpty(psswd)) {
                    boolean isEmptyFields = true;
                    password.setError("Password name can't empty");
                }
                if (TextUtils.isEmpty(eml)) {
                    boolean isEmptyFields = true;
                    email.setError("Email can't empty");
                } else {
                    // change state to loading
                    btncontinue.setEnabled(false);
                    btncontinue.setText("Loading...");
                    //save data to local storage
                    SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(username_key, username.getText().toString());
                    editor.apply();

                    //save databases
                    reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username.getText().toString());

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                            dataSnapshot.getRef().child("password").setValue(password.getText().toString());
                            dataSnapshot.getRef().child("email_address").setValue(email.getText().toString());
                            dataSnapshot.getRef().child("user_balance").setValue(200);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Intent gototoregistertwo = new Intent(RegisterOneAct.this, RegisterTwo.class);
                    startActivity(gototoregistertwo);
                }


            }
        });
    }
}

