package com.kaz.gardenphotoframe.Save;



import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



import java.io.File;
import java.util.ArrayList;

import com.kaz.gardenphotoframe.KAZ_HomeActivity;
import com.kaz.gardenphotoframe.KAZ_MultiTouchListener;
import com.kaz.gardenphotoframe.R;
import com.kaz.gardenphotoframe.Splash_Exit.KAZ_SplashActivity;

public class KAZ_saveImageActivity extends AppCompatActivity {

    public static int pos;
    GridView gv;
    ArrayList<String> spacecrafts = new ArrayList<>();
    KAZ_CustomAdapter adapter;
    TextView textshader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_image);



        gv = findViewById(R.id.gv);
        textshader = findViewById(R.id.textshader);

        gv.setAdapter(new KAZ_CustomAdapter(KAZ_saveImageActivity.this, spacecrafts));

        getData();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                adapter.getItemId(position);
                pos = position;
                @SuppressLint("ResourceType") Dialog dialog = new Dialog(KAZ_saveImageActivity.this, 16973839);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int i = (int) (((double) displayMetrics.heightPixels) * 1.0d);
                int i2 = (int) (((double) displayMetrics.widthPixels) * 1.0d);
                dialog.requestWindowFeature(1);
                dialog.getWindow().setFlags(1024, 1024);
                dialog.setContentView(R.layout.dialogimage);
                dialog.getWindow().setLayout(i2, i);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ImageView iv_image = dialog.findViewById(R.id.iv_image);
                iv_image.setOnTouchListener(new KAZ_MultiTouchListener());
                iv_image.setImageURI(Uri.parse(spacecrafts.get(pos)));
                dialog.show();
            }
        });


        TextPaint paint = textshader.getPaint();
        float width = paint.measureText("Tianjin, China");

        Shader textShader = new LinearGradient(0, 0, width, textshader.getTextSize(),
                new int[]{
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#6944f8"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#6944f8"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#6944f8"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#f23d85"),
                        Color.parseColor("#6944f8"),
                        Color.parseColor("#f23d85"),
                }, null, Shader.TileMode.MIRROR);
        textshader.getPaint().setShader(textShader);


    }


    private void getData() {
        File downloadsFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "KAZ All Frames");
        if (downloadsFolder.exists()) {

            File[] files = downloadsFolder.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];

                spacecrafts.add(String.valueOf(file));
            }
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(KAZ_saveImageActivity.this, KAZ_HomeActivity.class));
        KAZ_SplashActivity.showAdmobInterstitial(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}