package com.example.mylap2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylap2.Model.ContactModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Random;

public class SubActivity extends AppCompatActivity {
    private EditText etId;
    private EditText etFullname;
    private EditText etPhone;
    private ImageView imgView;
    private Button btnOK;
    private Bitmap photo;
    private Button btnCancel;
    private String uri;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
       if(resultCode == RESULT_OK && requestCode == 200){

           Uri selectedImageUri = imageReturnedIntent.getData();
           uri = selectedImageUri.toString();
           Log.d("UriDebug", "Uri: " + uri);
           imgView.setImageURI(selectedImageUri);
        }
    }/*
    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ())
            file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);
        etId = findViewById(R.id.etId);
        etFullname = findViewById(R.id.etFullname);
        etPhone = findViewById(R.id.etPhone);
        imgView = findViewById(R.id.imageView2);
        btnOK = findViewById(R.id.button4);
        btnCancel = findViewById(R.id.button5);

        if (getIntent().getExtras() != null){
            String editId = getIntent().getStringExtra("Id");
            String editName = getIntent().getStringExtra("Name");
            String editPhone = getIntent().getStringExtra("Phone");
            uri = getIntent().getStringExtra("Uri");
            Log.d("UriDebug", "Uri: " + uri);
            etId.setText(editId);
            etFullname.setText(editName);
            etPhone.setText(editPhone);
            imgView.setImageURI(Uri.parse(uri));



        }

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String fullname = etFullname.getText().toString();
                String phone = etPhone.getText().toString();
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("fullname", fullname);
                intent.putExtra("phone", phone);
                if (uri != null) {
                    intent.putExtra("uri", uri);
                    Log.d("UriDebug", "Uri: " + uri);
                }
                setResult(150, intent);
                finish();
            }
        });
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
