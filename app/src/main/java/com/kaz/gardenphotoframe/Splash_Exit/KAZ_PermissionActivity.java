package com.kaz.gardenphotoframe.Splash_Exit;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.kaz.gardenphotoframe.R;

public class KAZ_PermissionActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    private static final int REQUEST_PERMISSION_CODE = 101;
    ImageView allow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        allow = findViewById(R.id.allow);

        allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if ((ActivityCompat.checkSelfPermission(KAZ_PermissionActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) &&
                            (ActivityCompat.checkSelfPermission(KAZ_PermissionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        ActivityCompat.requestPermissions(KAZ_PermissionActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                    }
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted.", Toast.LENGTH_SHORT).show();


//                    Intent intent = new Intent(PermissionActivity.this, FirstActivity.class);
//                    startActivity(intent);
                    getSharedPreferences(getPackageName(), 0).edit().putBoolean("apppermi", true).apply();
                    startActivity(new Intent(KAZ_PermissionActivity.this, KAZ_FirstActivity.class));



                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}