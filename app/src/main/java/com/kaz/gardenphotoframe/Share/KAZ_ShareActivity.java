package com.kaz.gardenphotoframe.Share;



import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import java.util.List;

import com.kaz.gardenphotoframe.KAZ_HomeActivity;
import com.kaz.gardenphotoframe.R;
import com.kaz.gardenphotoframe.Save.KAZ_saveImageActivity;
import com.kaz.gardenphotoframe.Splash_Exit.KAZ_SplashActivity;

public class KAZ_ShareActivity extends AppCompatActivity {

    ImageView shareimage;
    ImageView watsapp,instagram,facebook,visitalbum;
    ImageView more;
    TextView textshader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);



        shareimage = findViewById(R.id.shareimage);
        watsapp=findViewById(R.id.watsapp);
        instagram=findViewById(R.id.instagram);
        facebook=findViewById(R.id.facebook);
        more=findViewById(R.id.more);
        visitalbum=findViewById(R.id.visitalbum);

        Bundle bundle = getIntent().getExtras();
        Uri myUri = Uri.parse(bundle.getString("BitmapImage"));
        shareimage.setImageURI(myUri);

        textshader=findViewById(R.id.textshader);



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

        watsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri imgUri = Uri.parse(String.valueOf(myUri));
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
                whatsappIntent.setType("image/");
                whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(KAZ_ShareActivity.this, "Whatsapp have not been installed", Toast.LENGTH_LONG).show();
                }
            }
        });


        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instagramIntent = new Intent(Intent.ACTION_SEND);
                Uri uri = Uri.parse(String.valueOf(myUri));
                instagramIntent.setType("image/*");
                instagramIntent.putExtra(Intent.EXTRA_STREAM, uri);
                instagramIntent.setPackage("com.instagram.android");

                PackageManager packManager = getPackageManager();
                List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(instagramIntent,  PackageManager.MATCH_DEFAULT_ONLY);

                boolean resolved = false;
                for(ResolveInfo resolveInfo: resolvedInfoList){
                    if(resolveInfo.activityInfo.packageName.startsWith("com.instagram.android")){
                        instagramIntent.setClassName(
                                resolveInfo.activityInfo.packageName,
                                resolveInfo.activityInfo.name );
                        resolved = true;
                        break;
                    }
                }
                if(resolved){
                    startActivity(instagramIntent);
                }else{
                    Toast.makeText(KAZ_ShareActivity.this, "Instagram App is not installed", Toast.LENGTH_LONG).show();
                }
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instagramIntent = new Intent(Intent.ACTION_SEND);
                Uri uri = Uri.parse(String.valueOf(myUri));
                instagramIntent.setType("image/*");
                instagramIntent.putExtra(Intent.EXTRA_STREAM, uri);
                instagramIntent.setPackage("com.facebook.katana");

                PackageManager packManager = getPackageManager();
                List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(instagramIntent,  PackageManager.MATCH_DEFAULT_ONLY);

                boolean resolved = false;
                for(ResolveInfo resolveInfo: resolvedInfoList){
                    if(resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")){
                        instagramIntent.setClassName(
                                resolveInfo.activityInfo.packageName,
                                resolveInfo.activityInfo.name );
                        resolved = true;
                        break;
                    }
                }
                if(resolved){
                    startActivity(instagramIntent);
                }else{
                    Toast.makeText(KAZ_ShareActivity.this, "facebook App is not installed", Toast.LENGTH_LONG).show();
                }
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, myUri);
                intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                intent.setType("text/plain");
                intent.setType("image/png");
                startActivity(Intent.createChooser(intent, "Share Via"));
            }
        });


        visitalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KAZ_ShareActivity.this, KAZ_saveImageActivity.class);
                startActivity(intent);
                KAZ_SplashActivity.showAdmobInterstitial(KAZ_ShareActivity.this);

            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent=new Intent(KAZ_ShareActivity.this, KAZ_HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}