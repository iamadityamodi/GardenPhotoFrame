package com.kaz.gardenphotoframe.Splash_Exit;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.kaz.gardenphotoframe.R;

public class KAZ_WebActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webview=findViewById(R.id.webview);
        webview.loadUrl(KAZ_Glob.privacy_link);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(KAZ_WebActivity.this, KAZ_SecondActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}