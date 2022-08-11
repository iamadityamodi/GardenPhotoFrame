package com.kaz.gardenphotoframe.Splash_Exit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.kaz.gardenphotoframe.R;


public class KAZ_AdsUtils {

    public static String admobAppID = "";
    public static String admobAppOpen = "";
    public static String admobBanner = "";
    public static String admobInterstitial = "";
    public static String admobNativeadvanced = "";
    public static String preadmobAppID = "pfadmobAppID";
    public static String preadmobAppOpen = "pfadmobAppOpen";
    public static String preadmobBanner = "pfadmobBanner";
    public static String preadmobInterstitial = "pfadmobInterstitial";
    public static String preadmobNativeadvanced = "pfadmobNativeadvanced";


    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {

            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);

            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);

            if ((networkInfo2 == null || !networkInfo2.isConnectedOrConnecting()) && (networkInfo == null || !networkInfo.isConnectedOrConnecting())) {

                return false;

            }

            return true;

        }
        return false;
    }

    public static String getPrefString(Context context, String str) {

        if (context == null) {

            return "blank";

        }


        return context.getSharedPreferences(context.getString(R.string.app_name) + context.getPackageName(), 0).getString(str, "blank");


    }

    public static Integer getInteger(Context context, String str) {

        return Integer.valueOf(context.getSharedPreferences(context.getString(R.string.app_name) + context.getPackageName(), 0).getInt(str, 0));

    }


    public static void setInteger(Context context, String str, Integer num) {
        context.getSharedPreferences(context.getString(R.string.app_name) + context.getPackageName(), 0).edit().putInt(str, num.intValue()).commit();
    }


    public static void setPrefString(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(context.getString(R.string.app_name) + context.getPackageName(), 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static boolean getPrefBoolean(Context context, String str) {
        if (context != null) {
            return context.getSharedPreferences(context.getString(R.string.app_name), 0).getBoolean(str, false);
        }
        return false;
    }

    public static void setPrefBoolean(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(context.getString(R.string.app_name), 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static void showAdmobBanner(Activity activity, String admob_b, final ViewGroup banner_container) {


        String str = "-12345";
        long l = Long.parseLong(str);

        final AdView mAdView = new AdView(activity);
        mAdView.setAdSize(AdSize.LARGE_BANNER);
        mAdView.setAdUnitId(admob_b);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                banner_container.removeAllViews();
                Log.e("onAdFailedToLoad", "onAdFailedToLoad: " + loadAdError.getCode());

            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                banner_container.removeAllViews();
                banner_container.addView(mAdView);
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });

    }


}