package com.example.tiketsayam_haris_a;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditMyProfileAct extends AppCompatActivity {
    public LinearLayout btnback;
    public Button btnuploadphotoeditprofile, btnsaveeditprofile;
    public EditText namaeditprofile, passioneditprofile, passwordeditprofile, usernameeditprofile, emaileditprofile;
    CircleImageView photoeditprofile;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new;
    Uri photo_location;
    Integer photomax = 1;
    StorageReference storage;
    private DatabaseReference reference;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);

        //load data from local storage
        getlocalname();

        //load variable
        btnback = findViewById(R.id.btnBack);
        btnuploadphotoeditprofile = findViewById(R.id.btnuploadphotoeditprofile);
        btnsaveeditprofile = findViewById(R.id.btnSaveEditProfile);
        photoeditprofile = findViewById(R.id.photoEditProfile);
        namaeditprofile = findViewById(R.id.namaEditProfile);
        passioneditprofile = findViewById(R.id.passionEditProfile);
        usernameeditprofile = findViewById(R.id.usernameEditProfile);
        passwordeditprofile = findViewById(R.id.passwordEditProfile);
        emaileditprofile = findViewById(R.id.emailEditProfile);


        //load data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Picasso.get().load(dataSnapshot.child("url_photo_profile").getValue().toString()).centerCrop().fit().into(photoeditprofile);
                namaeditprofile.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                passioneditprofile.setText(dataSnapshot.child("bio").getValue().toString());
                usernameeditprofile.setText(dataSnapshot.child("username").getValue().toString());
                passwordeditprofile.setText(dataSnapshot.child("password").getValue().toString());
                emaileditprofile.setText(dataSnapshot.child("email_address").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //new data change to firebise
        btnsaveeditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = namaeditprofile.getText().toString().trim();
                String passion = passioneditprofile.getText().toString().trim();
                String username = usernameeditprofile.getText().toString().trim();
                String password = passwordeditprofile.getText().toString().trim();
                String email = emaileditprofile.getText().toString().trim();

                Boolean isemptyfiedls = false;

                if (TextUtils.isEmpty(name)) {
                    isemptyfiedls = false;
                    namaeditprofile.setError("Full name can't empty..");
                }
                if (TextUtils.isEmpty(passion)) {
                    isemptyfiedls = true;
                    passioneditprofile.setError("Passion / Bio can't empty...");
                }
                if (TextUtils.isEmpty(username)) {
                    isemptyfiedls = false;
                    usernameeditprofile.setError("User name can't empty..");
                }
                if (TextUtils.isEmpty(password)) {
                    isemptyfiedls = false;
                    passwordeditprofile.setError("Password can't empty..");
                }
                if (TextUtils.isEmpty(email)) {
                    isemptyfiedls = false;
                    emaileditprofile.setError("Email can't empty..");
                } else {
//                    Toast.makeText(getApplicationContext(), "masih belum selesai", Toast.LENGTH_SHORT).show();
                    btnsaveeditprofile.setEnabled(false);
                    btnsaveeditprofile.setText("Loading...");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("nama_lengkap").setValue(usernameeditprofile.getText().toString());
                            dataSnapshot.getRef().child("bio").setValue(passioneditprofile.getText().toString());
                            dataSnapshot.getRef().child("username").setValue(usernameeditprofile.getText().toString());
                            dataSnapshot.getRef().child("password").setValue(passwordeditprofile.getText().toString());
                            dataSnapshot.getRef().child("email_adddress").setValue(emaileditprofile.getText().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    storage = FirebaseStorage.getInstance().getReference().child("photousers").child(username_key_new);
                    if (photo_location != null) {

                        // refrence to save data
                        final StorageReference storageReference1 = storage.child((System.currentTimeMillis() + "." + getFileExtension(photo_location)));
                        storageReference1.putFile(photo_location).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            //save data to firebase and storage
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String uriPhoto = uri.toString();
                                        reference.getRef().child("url_photo_profile").setValue(uriPhoto);
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
//                                        Intent gotosuccess = new Intent(RegisterTwo.this, SuccessRegister.class);
//                                        startActivity(gotosuccess);

                                    }
                                });

                            }
                        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                //if complete upload/save goto next page
//                                Intent gotosuccess = new Intent(RegisterTwo.this, SuccessRegister.class);
//                                startActivity(gotosuccess);

                            }
                        });
                    }

                    Intent gotoprofile = new Intent(EditMyProfileAct.this, MyProfile.class);
                    startActivity(gotoprofile);
                }
            }
        });

        //function button
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnuploadphotoeditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphoto();
            }
        });
    }

    public void getlocalname() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }

    //Get string file to save to firebase
    String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    //Open intent to looking for photo
    public void findphoto() {
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photomax);
    }

    // Result photo and take it to page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == photomax && resultCode == RESULT_OK && data != null && data.getData() != null) {
            photo_location = data.getData();
            Picasso.get().load(photo_location).centerCrop().fit().into(photoeditprofile);
        }
    }
}
