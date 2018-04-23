package com.example.mahmoudamin.gradpro;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

public class App extends AppCompatActivity {

    static final int CAMERA_REQUEST = 1;
    Button captureBtn;
    ImageView cameraIV;
    Uri imageUri;

    private Uri mImageUri = null;
    private StorageReference mStorage;
    private ProgressDialog mProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        captureBtn = (Button) findViewById(R.id.captureBtn);
        cameraIV = (ImageView) findViewById(R.id.cameraIV);

        mStorage = FirebaseStorage.getInstance().getReference();

        mProgress = new ProgressDialog(this);

        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                        launchCamera();
                }
        });
    }


    public void launchCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){


            final Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
            Log.i("Mina", String.valueOf(capturedImage));
            StorageReference filePath = mStorage.child("Captured_Images").child("Image1");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            capturedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            mProgress.setMessage("Uploading Captured Image");
            mProgress.show();
            mProgress.setCancelable(false);
            mProgress.setCanceledOnTouchOutside(false);

            filePath.putBytes(byteArray).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgress.dismiss();
                    cameraIV.setImageBitmap(capturedImage);
                    Toast.makeText(getApplicationContext(),"Image Uploaded successfully",Toast.LENGTH_LONG);
                }
            });


        }

    }
}
