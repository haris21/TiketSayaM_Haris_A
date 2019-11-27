package com.example.tiketsayam_haris_a;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterTwo extends AppCompatActivity {
    public LinearLayout btnback;
    public Button btncontinue, btnuploadphoto;
    public CircleImageView photo;
    public EditText edtnama, edtpassion;
    Uri photo_location;
    Integer photomax = 1;
    DatabaseReference reference;
    StorageReference storage;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        //take username data from local
        getusernamelocal();

        //Load variable
        btnuploadphoto = findViewById(R.id.btnUploadphoto);
        photo = findViewById(R.id.photo);
        btnback = findViewById(R.id.btnBack);
        btncontinue = findViewById(R.id.btnContinue);
        edtnama = findViewById(R.id.edtName);
        edtpassion = findViewById(R.id.edtPassion);

        //functions
        //Back to previous page
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregisterone = new Intent(RegisterTwo.this, RegisterOneAct.class);
                startActivity(gotoregisterone);
            }
        });

        //Goto next page
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtnama.getText().toString().trim();
                String passion = edtpassion.getText().toString().trim();
                Boolean isemptyfiedls = false;

                if (TextUtils.isEmpty(name)) {
                    isemptyfiedls = false;
                    edtnama.setError("Full name can't empty..");
                }
                if (TextUtils.isEmpty(passion)) {
                    isemptyfiedls = true;
                    edtpassion.setError("Passion / Bio can't empty...");
                }
                if (photo_location == null) {
                    Toast.makeText(RegisterTwo.this, "Photo can't empty", Toast.LENGTH_SHORT).show();

                } else {

                    // change state to loading
                    btncontinue.setEnabled(false);
                    btncontinue.setText("Loading...");

                    //refrence firebase and storage
                    reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
                    storage = FirebaseStorage.getInstance().getReference().child("photousers").child(username_key_new);
                    //validation of file
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
                                        reference.getRef().child("nama_lengkap").setValue(edtnama.getText().toString());
                                        reference.getRef().child("bio").setValue(edtpassion.getText().toString());
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        Intent gotosuccess = new Intent(RegisterTwo.this, SuccessRegister.class);
                                        startActivity(gotosuccess);

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

                }
            }
        });

        //Open gallery o choose photo
        btnuploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphoto();
            }
        });
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
            Picasso.get().load(photo_location).centerCrop().fit().into(photo);
        }
    }

    // get local usename
    public void getusernamelocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
