package com.kaz.gardenphotoframe;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;



import com.kaz.gardenphotoframe.Save.KAZ_saveImageActivity;
import com.kaz.gardenphotoframe.Splash_Exit.KAZ_SecondActivity;
import com.kaz.gardenphotoframe.Splash_Exit.KAZ_SplashActivity;
import com.kaz.gardenphotoframe.Text.KAZ_FrameListActivity;

public class KAZ_HomeActivity extends AppCompatActivity {

    ImageView next, album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        KAZ_SplashActivity.showAdmobBanner(this, (ViewGroup) findViewById(R.id.banner));


        next = findViewById(R.id.next);
        album = findViewById(R.id.album);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KAZ_HomeActivity.this, KAZ_FrameListActivity.class);
                startActivity(intent);
                KAZ_SplashActivity.showAdmobInterstitial(KAZ_HomeActivity.this);


            }
        });

        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KAZ_HomeActivity.this, KAZ_saveImageActivity.class);
                startActivity(intent);
                KAZ_SplashActivity.showAdmobInterstitial(KAZ_HomeActivity.this);


            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(KAZ_HomeActivity.this, KAZ_SecondActivity.class));
        KAZ_SplashActivity.showAdmobInterstitial(KAZ_HomeActivity.this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}