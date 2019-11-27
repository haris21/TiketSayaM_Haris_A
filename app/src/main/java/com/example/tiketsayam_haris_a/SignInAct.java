package com.example.tiketsayam_haris_a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInAct extends AppCompatActivity {
    public TextView btnreg;
    public Button btnlogin;
    EditText edtusername, edtpassword;

    DatabaseReference reference;


    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Load variable
        btnreg = findViewById(R.id.btnReg);
        btnlogin = findViewById(R.id.btnlogin);
        edtusername = findViewById(R.id.edtUserName);
        edtpassword = findViewById(R.id.edtPassword);

        //fucntion
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formReg = new Intent(SignInAct.this, RegisterOneAct.class);
                startActivity(formReg);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //var for check
                String usrnme = edtusername.getText().toString().trim();
                String usrpsswd = edtpassword.getText().toString().trim();
                Boolean isemptyfeld = false;

                if (TextUtils.isEmpty(usrnme)) {
                    isemptyfeld = true;
                    edtusername.setError("user can't empty");
                }
                if (TextUtils.isEmpty(usrpsswd)) {
                    isemptyfeld = true;
                    edtpassword.setError("password can't empty");
                } else {

                    reference = FirebaseDatabase.getInstance().getReference().child("Users").child(edtusername.getText().toString());

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Toast.makeText(SignInAct.this, "User name ada", Toast.LENGTH_SHORT).show();
                                // Take data from firebase
                                String psswordfrbs = dataSnapshot.child("password").getValue().toString();
                                String userfrbs = dataSnapshot.child("username").getValue().toString();

                                //Take data drom xml/ form
                                String xusername = edtusername.getText().toString();
                                String xpssowrd = edtpassword.getText().toString();

                                if ((userfrbs.equals(xusername)) && (psswordfrbs.equals(xpssowrd))) {
                                    //save data to local storage
                                    SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(username_key, edtusername.getText().toString());
                                    editor.apply();

                                    // change state to loading
                                    btnlogin.setEnabled(false);
                                    btnlogin.setText("Loading...");

                                    // goto next page
                                    Intent gotoHome = new Intent(SignInAct.this, HomeAct.class);
                                    startActivity(gotoHome);
                                } else {
                                    Toast.makeText(SignInAct.this, "User  name or passsword is wrong", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(SignInAct.this, "Data not match", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "Databases Error", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }
}
