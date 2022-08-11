package com.kaz.gardenphotoframe.Splash_Exit;



import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;



import com.kaz.gardenphotoframe.R;

public class KAZ_FirstActivity extends AppCompatActivity {


    Button start, share, pp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        KAZ_SplashActivity.showAdmobNative_MED(findViewById(R.id.native_container), KAZ_FirstActivity.this);
        KAZ_SplashActivity.showAdmobBanner(this, (ViewGroup) findViewById(R.id.banner));




        getWindow().setFlags(1024, 1024);

        start = findViewById(R.id.getstart1);
        share = findViewById(R.id.share);
        pp = findViewById(R.id.pp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        toolbar.setTitleTextColor(Color.WHITE);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, KAZ_Glob.app_name);
                String shareMessage = "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + KAZ_Glob.app_link;
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));

            }
        });

        pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KAZ_FirstActivity.this, KAZ_WebActivity.class));
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(KAZ_FirstActivity.this, KAZ_SecondActivity.class);
                startActivity(intent);
                KAZ_SplashActivity.showAdmobInterstitial(KAZ_FirstActivity.this);

            }
        });
    }




    @Override
    public void onBackPressed() {
        startActivity(new Intent(KAZ_FirstActivity.this, KAZ_BackActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}