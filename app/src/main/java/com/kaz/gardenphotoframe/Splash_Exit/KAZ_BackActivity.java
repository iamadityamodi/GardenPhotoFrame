package com.kaz.gardenphotoframe.Splash_Exit;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.kaz.gardenphotoframe.R;

public class KAZ_BackActivity extends AppCompatActivity {

    ImageView no, yes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        getWindow().setFlags(1024, 1024);


        KAZ_SplashActivity.showAdmobNative_MED(findViewById(R.id.native_container), KAZ_BackActivity.this);


        no = findViewById(R.id.no);
        yes = findViewById(R.id.yes);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(KAZ_BackActivity.this, KAZ_SecondActivity.class);
                startActivity(intent);


            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KAZ_BackActivity.this, KAZ_ThanksActivity.class);
                startActivity(intent);
                KAZ_SplashActivity.showAdmobInterstitial(KAZ_BackActivity.this);



            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(KAZ_BackActivity.this, KAZ_SecondActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}