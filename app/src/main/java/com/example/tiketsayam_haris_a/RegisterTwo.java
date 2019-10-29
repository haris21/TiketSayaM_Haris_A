package com.example.tiketsayam_haris_a;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterTwo extends AppCompatActivity {
    public LinearLayout btnback;
    public Button btncontinue, btnuploadphoto;
    public CircleImageView photo;
    Uri photo_location;
    Integer photomax = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        //Load variable
        btnuploadphoto = findViewById(R.id.btnUploadphoto);
        photo = findViewById(R.id.photo);
        btnback = findViewById(R.id.btnBack);
        btncontinue = findViewById(R.id.btnContinue);

        //functions
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregisterone = new Intent(RegisterTwo.this, RegisterOneAct.class);
                startActivity(gotoregisterone);
            }
        });


        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosuccess = new Intent(RegisterTwo.this, SuccessRegister.class);
                startActivity(gotosuccess);
            }
        });

        btnuploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphoto();
            }
        });
    }

    String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void findphoto() {
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photomax);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == photomax && resultCode == RESULT_OK && data != null && data.getData() != null) {
            photo_location = data.getData();
            Picasso.get().load(photo_location).centerCrop().fit().into(photo);
        }

    }
}
