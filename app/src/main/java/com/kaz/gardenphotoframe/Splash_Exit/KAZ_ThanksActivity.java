package com.kaz.gardenphotoframe.Splash_Exit;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kaz.gardenphotoframe.R;

public class KAZ_ThanksActivity extends AppCompatActivity {

    ImageView ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        getWindow().setFlags(1024, 1024);

        ok = findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                finishAffinity();
            }
        });
    }


    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}