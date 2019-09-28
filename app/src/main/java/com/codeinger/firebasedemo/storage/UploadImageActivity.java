package com.codeinger.firebasedemo.storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.codeinger.firebasedemo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.squareup.picasso.Picasso;
import com.tuyenmonkey.mkloader.MKLoader;
import com.yalantis.ucrop.UCrop;


import java.io.File;
import java.io.IOException;

public class UploadImageActivity extends AppCompatActivity {

    private Button upload;
    private MKLoader loader;
    private ImageView imageView;
    private final int PERMISSION_ALL = 2534;
    private ImagePicker imagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        upload = findViewById(R.id.upload);
        loader = findViewById(R.id.loader);
        imageView = findViewById(R.id.image);

        final String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
        };

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPermissions(UploadImageActivity.this, PERMISSIONS)){
                    ActivityCompat.requestPermissions(UploadImageActivity.this, PERMISSIONS, PERMISSION_ALL);
                }
                else {
                    imagePicker.choosePicture(true /*show camera intents*/);
                }
            }
        });

        imagePicker = new ImagePicker(this, /* activity non null*/
                null, /* fragment nullable*/
                new OnImagePickedListener() {
                    @Override
                    public void onImagePicked(Uri imageUri) {
                        UCrop.of(imageUri, getTempUri())
                                .withAspectRatio(1, 1)
                                .start(UploadImageActivity.this);
                    }
                });






    }


    private Uri getTempUri(){
        String dri = Environment.getExternalStorageDirectory()+File.separator+"Temp";
        File dirFile = new File(dri);
        dirFile.mkdir();

        String file = dri+File.separator+"temp.png";
        File tempFile = new File(file);
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Uri.fromFile(tempFile);
    }



    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_ALL:{
                if (grantResults.length > 0) {
                    boolean flag = true;
                    for (int i = 0 ; i < permissionsList.length ; i++ ) {
                        if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                            flag = false;
                        }
                    }

                    if(flag){
                        imagePicker.choosePicture(true /*show camera intents*/);
                    }
                    // Show permissionsDenied

                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode,requestCode, data);

        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            imageView.setImageURI(null);
            imageView.setImageURI(resultUri);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Log.i("dsjknjsdkn", "onActivityResult: "+cropError.getMessage());
        }
    }







}
