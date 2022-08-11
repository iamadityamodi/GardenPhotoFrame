package com.kaz.gardenphotoframe.Splash_Exit;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


import com.kaz.gardenphotoframe.KAZ_HomeActivity;
import com.kaz.gardenphotoframe.R;

public class KAZ_SecondActivity extends AppCompatActivity {

    Button getstart2,rateus, more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start_activty);
        getWindow().setFlags(1024, 1024);


        KAZ_SplashActivity.showAdmobNative_MED(findViewById(R.id.native_container), KAZ_SecondActivity.this);
        KAZ_SplashActivity.showAdmobBanner(this, (ViewGroup) findViewById(R.id.banner));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        toolbar.setTitleTextColor(Color.WHITE);

        getstart2 = findViewById(R.id.getstart2);
        rateus = findViewById(R.id.rateus);
        more = findViewById(R.id.more);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Kumbharwad+App+Zone")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=Kumbharwad+App+Zone")));
                }
            }
        });

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(KAZ_Glob.app_link);
                Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                myAppLinkToMarket.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                try {
                    startActivity(myAppLinkToMarket);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(KAZ_SecondActivity.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
                }
            }
        });

        getstart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(KAZ_SecondActivity.this, KAZ_HomeActivity.class);
                startActivity(intent);
                KAZ_SplashActivity.showAdmobInterstitial(KAZ_SecondActivity.this);

            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(KAZ_SecondActivity.this, KAZ_BackActivity.class);
        startActivity(intent);
        KAZ_SplashActivity.showAdmobInterstitial(KAZ_SecondActivity.this);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}