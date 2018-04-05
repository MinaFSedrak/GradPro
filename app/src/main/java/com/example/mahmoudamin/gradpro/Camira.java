package com.example.mahmoudamin.gradpro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Camira extends AppCompatActivity {
      Button _btncam,_btnlogout;
    private static final int CAMERA_PIC_REQUEST = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camira);
        _btncam= (Button) findViewById(R.id.btncam);
        _btnlogout= (Button) findViewById(R.id.btnlogout);

        _btncam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(in, CAMERA_PIC_REQUEST);
            }
        });

        _btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Camira.this,MainActivity.class);
                startActivity(in);
            }
        });
    }

    }



