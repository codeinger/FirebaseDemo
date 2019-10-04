package com.codeinger.firebasedemo.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.codeinger.firebasedemo.R;
import com.codeinger.firebasedemo.authentication.facebookauthentication.FaceBookLoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(FirebaseAuth.getInstance().getCurrentUser()==null){
                    startActivity(new Intent(SplashActivity.this, FaceBookLoginActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, DashBoardActivity.class));
                    finish();
                }
            }
        },3000);


    }








}




