package com.kaz.gardenphotoframe.Splash_Exit;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kaz.gardenphotoframe.R;

import java.util.Map;

public class KAZ_SplashActivity extends AppCompatActivity {


    Handler handler;


    public static InterstitialAd mInterstitialAdMob;
    public AppOpenAd appOpenAd = null;
    public AppOpenAd.AppOpenAdLoadCallback loadCallback;
    Integer status;
    ValueEventListener mFirebaseListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            Log.e("hk", " epfdata " + dataSnapshot.toString());
            if (dataSnapshot.exists()) {


                status = dataSnapshot.child("admobload").getValue(int.class);
                KAZ_AdsUtils.setPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobAppID, (String) dataSnapshot.child("admobAppID").getValue(String.class));
                KAZ_AdsUtils.setPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobAppOpen, (String) dataSnapshot.child("admobAppOpen").getValue(String.class));
                KAZ_AdsUtils.setPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobBanner, (String) dataSnapshot.child("admobBanner").getValue(String.class));
                KAZ_AdsUtils.setPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobInterstitial, (String) dataSnapshot.child("admobInterstitial").getValue(String.class));
                KAZ_AdsUtils.setPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobNativeadvanced, (String) dataSnapshot.child("admobNativeadvanced").getValue(String.class));
                KAZ_AdsUtils.admobAppID = KAZ_AdsUtils.getPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobAppID);
                KAZ_AdsUtils.admobAppOpen = KAZ_AdsUtils.getPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobAppOpen);
                KAZ_AdsUtils.admobBanner = KAZ_AdsUtils.getPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobBanner);
                KAZ_AdsUtils.admobInterstitial = KAZ_AdsUtils.getPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobInterstitial);
                KAZ_AdsUtils.admobNativeadvanced = KAZ_AdsUtils.getPrefString(KAZ_SplashActivity.this, KAZ_AdsUtils.preadmobNativeadvanced);
                KAZ_SplashActivity.this.fetchAd();
                loadAdmobAd();


            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            KAZ_SplashActivity.this.fetchAd();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();

        MobileAds.initialize(this);
        MobileAds.initialize(this, new
                OnInitializationCompleteListener() {


                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        Map<String, AdapterStatus> adapterStatusMap = initializationStatus.getAdapterStatusMap();
                        for (String str : adapterStatusMap.keySet()) {
                            AdapterStatus adapterStatus = adapterStatusMap.get(str);
                            Log.d("MyApp", String.format("Adapter name: %s, Description: %s, Latency: %d", str, adapterStatus.getDescription(), Integer.valueOf(adapterStatus.getLatency())));
                        }
                    }
                });
        if (KAZ_AdsUtils.isOnline(this)) {
            firebaseAd();
        } else {
            goto_act();
        }

    }


    @Override
    public void onBackPressed() {
    }




    private void firebaseAd() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        FirebaseAnalytics.getInstance(this);
        reference.getRoot().addValueEventListener(this.mFirebaseListener);
    }

    public void fetchAd() {
        if (isAdAvailable()) {
            return;
        }

        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);

                // Handle the error.
                Log.d("=====", "error in loading");
                goto_act();
            }

            @Override
            public void onAdLoaded(@NonNull AppOpenAd ad) {
                super.onAdLoaded(appOpenAd);

                appOpenAd = ad;
                FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        goto_act();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                    }
                };

                appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                appOpenAd.show(KAZ_SplashActivity.this);
            }
        };
        AdRequest request = getAdRequest();
        AppOpenAd.load(KAZ_SplashActivity.this, KAZ_AdsUtils.admobAppOpen, request,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    private void goto_act() {

        if (!getSharedPreferences(getPackageName(), 0).getBoolean("apppermi", false)) {
            startActivity(new Intent(KAZ_SplashActivity.this, KAZ_PermissionActivity.class));
        } else {
            startActivity(new Intent(KAZ_SplashActivity.this, KAZ_FirstActivity.class));
        }

    }

    public static void showAdmobBanner(Activity activity, final ViewGroup banner_container) {


        String str = "-12345";
        long l = Long.parseLong(str);

        final AdView mAdView = new AdView(activity);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(KAZ_AdsUtils.admobBanner);
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


    public boolean isAdAvailable() {
        return appOpenAd != null;
    }

    private void loadAdmobAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, KAZ_AdsUtils.admobInterstitial, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                mInterstitialAdMob = interstitialAd;
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        Log.e("====", "onAdDismissedFullScreenContent: ");
                        loadAdmobAd();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        Log.e("====", "onAdFailedToShowFullScreenContent: " + adError.getMessage());
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        Log.e("====", "onAdShowedFullScreenContent: ");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.e("====", "onAdFailedToLoad: " + loadAdError.getResponseInfo());
            }


        });
    }

    public static void showAdmobInterstitial(Activity activity) {
        if (mInterstitialAdMob != null) {
            mInterstitialAdMob.show(activity);
        }
    }

    public static void showAdmobNative_MED(final ViewGroup ad_Container, final Activity activity) {
        View view = activity.getLayoutInflater().inflate(R.layout.ad_unit_admob_med, null);
        final NativeAdView adView = view.findViewById(R.id.adview);
        ad_Container.removeAllViews();
        ad_Container.addView(view);
        adView.setVisibility(View.INVISIBLE);

        AdLoader.Builder builder = new AdLoader.Builder(activity, KAZ_AdsUtils.admobNativeadvanced).forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                //assign id
                adView.setMediaView((com.google.android.gms.ads.nativead.MediaView) adView.findViewById(R.id.ad_media));
                adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
                adView.setBodyView(adView.findViewById(R.id.ad_body));
                adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
                adView.setIconView(adView.findViewById(R.id.ad_app_icon));
                adView.setPriceView(adView.findViewById(R.id.ad_price));
                adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
                adView.setStoreView(adView.findViewById(R.id.ad_store));
                adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
                //assign ad contents
                adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
                ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
                ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
                ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
                if (nativeAd.getIcon() == null) {
                    adView.getIconView().setVisibility(View.GONE);
                } else {
                    ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
                    adView.getIconView().setVisibility(View.VISIBLE);
                }
                if (nativeAd.getPrice() == null) {
                    adView.getPriceView().setVisibility(View.GONE);
                } else {
                    adView.getPriceView().setVisibility(View.GONE);
                    ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
                }
                if (nativeAd.getStore() == null) {
                    adView.getStoreView().setVisibility(View.GONE);
                } else {
                    adView.getStoreView().setVisibility(View.GONE);
                    ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
                }
                if (nativeAd.getStarRating() == null) {
                    adView.getStarRatingView().setVisibility(View.GONE);
                } else {
                    ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
                    adView.getStarRatingView().setVisibility(View.GONE);
                }
                if (nativeAd.getAdvertiser() == null) {
                    adView.getAdvertiserView().setVisibility(View.GONE);
                } else {
                    ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
                    adView.getAdvertiserView().setVisibility(View.GONE);
                }
                adView.setNativeAd(nativeAd);
                adView.setVisibility(View.VISIBLE);
            }
        });
        VideoOptions videoOptions = new VideoOptions.Builder().build();
        com.google.android.gms.ads.formats.NativeAdOptions adOptions = new com.google.android.gms.ads.formats.NativeAdOptions.Builder().setVideoOptions(videoOptions).build();
        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                Log.e("dsity_admob_native_m", "onAdFailedToLoad: " + adError.getMessage());
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e("dsity_admob_native_m", "onAdLoaded: ");

            }
        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }





}