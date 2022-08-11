package com.kaz.gardenphotoframe;



import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.kaz.gardenphotoframe.Share.KAZ_ShareActivity;
import com.kaz.gardenphotoframe.Splash_Exit.KAZ_SplashActivity;
import com.kaz.gardenphotoframe.Sticker.KAZ_StickerAdapter;
import com.kaz.gardenphotoframe.Text.KAZ_DemoStickerView;
import com.kaz.gardenphotoframe.Text.KAZ_FontListAdapter;
import com.kaz.gardenphotoframe.Text.KAZ_FrameListActivity;
import com.kaz.gardenphotoframe.Text.KAZ_StickerImageViewKAZ;
import com.kaz.gardenphotoframe.Text.KAZ_TextureAdapter;


public class KAZ_EditActivity extends AppCompatActivity implements View.OnClickListener {


    //    TODO TEXT
    private ImageView ivBack, ivDone, ivDoneForEnterText;
    private Dialog dialogForText;
    private ImageView ivClearText, ivSize, ivFont, ivOpacity, ivInnerTexture, ivShadow, ivTextColor, ivShadowColor;
    private TextView tvEnterText;
    private SeekBar sbSize, sbOpacity, sbTextShadow;
    private EditText etForEnterText;
    private GridView gvFontList;
    private Dialog dialogForFontList;
    private ArrayList<String> fontList;
    private KAZ_FontListAdapter KAZFontListAdapter;
    private ArrayList<String> textureList;
    private KAZ_HorizontalListView hlvTexture;
    private KAZ_TextureAdapter KAZTextureAdapter;
    private LinearLayout llShadowContainer;
    private int mSelectedColor;
    private boolean blnIsFromTextColor = false;
    public static Bitmap finalBitmapText;
    private ArrayList<String> textureList_text;

    //

    private static final String TAG = "SecondActivity";
        ImageView frameimg;
    ImageView image;
    Uri imageuri;
    ImageView flip, brightness1, filter, text, frame, stickerbtn, save;
    boolean imag = false;
    SeekBar seekBar;
    private int brightness;
    private ContentResolver cResolver;
    private Window window;
    ImageView auto_fix, b_n_w, flip_vertical, contrast, cross_process, documentary, dual_tone, fill_light, fish_eye;
    ImageView peacock_frame_1, peacock_frame_2, peacock_frame_3, peacock_frame_4, peacock_frame_5, peacock_frame_6, peacock_frame_7, peacock_frame_8, peacock_frame_9, peacock_frame_10, peacock_frame_11, peacock_frame_12;
    HorizontalScrollView horizonatalscrollview, horizonatalscrollviewframe;

    private KAZ_StickerImageViewKAZ sticker;
    private int view_id;
    ArrayList<Integer> stickerviewId = new ArrayList<>();
    private ArrayList<String> stickerlist;
    FrameLayout container;
    KAZ_HorizontalListView recycle;
    private KAZ_StickerAdapter stickerAdapter;
    private LinearLayout add_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



//        TODO TEXT
        TextCodeFind();

//


        add_text = (LinearLayout) findViewById(R.id.add_text);

        horizonatalscrollview = findViewById(R.id.horizonatalscrollview);
        image = findViewById(R.id.image);
        frameimg = findViewById(R.id.frameimg);
        flip = findViewById(R.id.flip);
        brightness1 = findViewById(R.id.brightness);
        seekBar = findViewById(R.id.seekbar);
        filter = findViewById(R.id.filter);
        text = findViewById(R.id.text);
        container = findViewById(R.id.container);
        auto_fix = findViewById(R.id.auto_fix);
        b_n_w = findViewById(R.id.b_n_w);
        flip_vertical = findViewById(R.id.flip_vertical);
        contrast = findViewById(R.id.contrast);
        cross_process = findViewById(R.id.cross_process);
        documentary = findViewById(R.id.documentary);
        dual_tone = findViewById(R.id.dual_tone);
        fill_light = findViewById(R.id.fill_light);
        fish_eye = findViewById(R.id.fish_eye);
        stickerbtn = findViewById(R.id.sticker);

        frame = findViewById(R.id.frame);
        horizonatalscrollviewframe = findViewById(R.id.horizonatalscrollviewframe);
        peacock_frame_1 = findViewById(R.id.peacock_frame_1);
        peacock_frame_2 = findViewById(R.id.peacock_frame_2);
        peacock_frame_3 = findViewById(R.id.peacock_frame_3);
        peacock_frame_4 = findViewById(R.id.peacock_frame_4);
        peacock_frame_5 = findViewById(R.id.peacock_frame_5);
        peacock_frame_6 = findViewById(R.id.peacock_frame_6);
        peacock_frame_7 = findViewById(R.id.peacock_frame_7);
        peacock_frame_8 = findViewById(R.id.peacock_frame_8);
        peacock_frame_9 = findViewById(R.id.peacock_frame_9);
        peacock_frame_10 = findViewById(R.id.peacock_frame_10);
        peacock_frame_11 = findViewById(R.id.peacock_frame_11);
        peacock_frame_12 = findViewById(R.id.peacock_frame_12);

        recycle = findViewById(R.id.recycle);
        save = findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_text.setVisibility(View.GONE);
                removeBorder();
                Toast.makeText(KAZ_EditActivity.this, "Save Image", Toast.LENGTH_SHORT).show();


                Bitmap bm = null;
                container.setDrawingCacheEnabled(true);
                container.buildDrawingCache();
                bm = container.getDrawingCache();
                Log.e("===", "Bitmap: " + bm);

                saveImage(bm);

                Uri uriimg = getImageUri(KAZ_EditActivity.this, bm);

                Intent intent = new Intent(KAZ_EditActivity.this, KAZ_ShareActivity.class);
                intent.putExtra("BitmapImage", uriimg.toString());
                startActivity(intent);
                KAZ_SplashActivity.showAdmobInterstitial(KAZ_EditActivity.this);



            }
        });


        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_text.setVisibility(View.GONE);
                horizonatalscrollviewframe.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.GONE);
                horizonatalscrollview.setVisibility(View.GONE);
                recycle.setVisibility(View.GONE);
            }
        });


        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_text.setVisibility(View.GONE);
                if (imag != true) {
                    image.setRotationX(360);
                    image.setRotationY(180);
                    imag = true;
                } else {
                    image.setRotationX(0);
                    image.setRotationY(0);
                    imag = false;
                }
                seekBar.setVisibility(View.GONE);
                horizonatalscrollview.setVisibility(View.GONE);
                horizonatalscrollviewframe.setVisibility(View.GONE);
                recycle.setVisibility(View.GONE);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_text.setVisibility(View.GONE);
                seekBar.setVisibility(View.GONE);
                horizonatalscrollview.setVisibility(View.VISIBLE);
                horizonatalscrollviewframe.setVisibility(View.GONE);
                recycle.setVisibility(View.GONE);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setVisibility(View.GONE);
                horizonatalscrollview.setVisibility(View.GONE);
                horizonatalscrollviewframe.setVisibility(View.GONE);
                recycle.setVisibility(View.GONE);

                add_text.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        openDialogForText();
                    }
                }, 1000);

            }
        });




        brightness1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setVisibility(View.VISIBLE);
                horizonatalscrollview.setVisibility(View.GONE);
                horizonatalscrollviewframe.setVisibility(View.GONE);
                recycle.setVisibility(View.GONE);
                add_text.setVisibility(View.GONE);
            }
        });

        stickerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_text.setVisibility(View.GONE);
                recycle.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.GONE);
                horizonatalscrollview.setVisibility(View.GONE);
                horizonatalscrollviewframe.setVisibility(View.GONE);
            }
        });


        listTexture("allsticker");
        stickerAdapter = new KAZ_StickerAdapter(stickerlist, KAZ_EditActivity.this);
        recycle.setAdapter(stickerAdapter);

        recycle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AssetManager assetManager = getAssets();
                InputStream istr = null;
                try {
                    istr = assetManager.open(stickerlist.get(position));
                    sticker = new KAZ_StickerImageViewKAZ(KAZ_EditActivity.this, onTouchSticker);
                    Bitmap bitmap = BitmapFactory.decodeStream(istr);
                    sticker.setImageBitmap(bitmap);
                    Random r = new Random();
                    view_id = r.nextInt();
                    if (view_id < 0) {
                        view_id = view_id - (view_id * 2);
                    }
                    sticker.setId(view_id);
                    stickerviewId.add(view_id);
                    sticker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sticker.setControlItemsHidden(false);
                        }
                    });
                    container.addView(sticker);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        peacock_frame_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_1);
            }
        });

        peacock_frame_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_2);
            }
        });

        peacock_frame_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_3);
            }
        });

        peacock_frame_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_4);
            }
        });

        peacock_frame_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_5);
            }
        });

        peacock_frame_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_6);
            }
        });

        peacock_frame_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_7);
            }
        });

        peacock_frame_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_8);
            }
        });

        peacock_frame_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_9);
            }
        });

        peacock_frame_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_10);
            }
        });

        peacock_frame_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_11);
            }
        });

        peacock_frame_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameimg.setImageResource(R.drawable.peacock_frame_12);
            }
        });


        auto_fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KAZ_Effects.applyEffect1(image);
            }
        });

        b_n_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KAZ_Effects.applyEffect2(image);
            }
        });

        flip_vertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KAZ_Effects.applyEffect3(image);
            }
        });

        contrast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KAZ_Effects.applyEffect4(image);
            }
        });

        cross_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KAZ_Effects.applyEffect5(image);
            }
        });

        documentary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KAZ_Effects.applyEffect6(image);
            }
        });

        dual_tone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KAZ_Effects.applyEffect7(image);
            }
        });

        fill_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KAZ_Effects.applyEffect8(image);
            }
        });

        fish_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KAZ_Effects.applyEffect9(image);
            }
        });


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Intent intent = getIntent();
            frameimg.setImageResource(intent.getIntExtra("image", 0));

            imageuri = getIntent().getData();

            image.setImageURI(imageuri);
        }


        image.setOnTouchListener(new KAZ_MultiTouchListener());


        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                removeBorder();
                horizonatalscrollviewframe.setVisibility(View.INVISIBLE);
                horizonatalscrollview.setVisibility(View.INVISIBLE);
                seekBar.setVisibility(View.INVISIBLE);
                recycle.setVisibility(View.INVISIBLE);
                return true;
            }
        });


        cResolver = getContentResolver();
        window = getWindow();

        try {
            brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap brightBitmap = drawable.getBitmap();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                // TODO Auto-generated method stub
                image.setAlpha((float) progress / 255);


            }
        });
    }

    //        TODO TEXT


    private void TextCodeFind() {
        ivDone = (ImageView) findViewById(R.id.ivDone);
        ivDone.setOnClickListener(this);

        tvEnterText = (TextView) findViewById(R.id.tvEnterText);

        ivClearText = (ImageView) findViewById(R.id.ivClearText);
        ivClearText.setOnClickListener(this);
        ivSize = (ImageView) findViewById(R.id.ivSize);
        ivSize.setOnClickListener(this);
        ivFont = (ImageView) findViewById(R.id.ivFont);
        ivFont.setOnClickListener(this);
        ivOpacity = (ImageView) findViewById(R.id.ivOpacity);
        ivOpacity.setOnClickListener(this);

        sbSize = (SeekBar) findViewById(R.id.sbSize);
        sbOpacity = (SeekBar) findViewById(R.id.sbOpacity);

        ivInnerTexture = (ImageView) findViewById(R.id.ivInnerTexture);
        ivInnerTexture.setOnClickListener(this);

        hlvTexture = (KAZ_HorizontalListView) findViewById(R.id.hlvTexture);
        ivShadow = (ImageView) findViewById(R.id.ivShadow);
        ivShadow.setOnClickListener(this);

        llShadowContainer = (LinearLayout) findViewById(R.id.llShadowContainer);

        ivShadowColor = (ImageView) findViewById(R.id.ivShadowColor);
        ivShadowColor.setOnClickListener(this);

        sbTextShadow = (SeekBar) findViewById(R.id.sbTextShadow);

        ivTextColor = (ImageView) findViewById(R.id.ivTextColor);
        ivTextColor.setOnClickListener(this);


        blnIsFromTextColor = false;


        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tvEnterText.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tvEnterText.setAlpha((float) progress / 255);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        hlvTexture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    tvEnterText.getPaint().setShader(null);
                    tvEnterText.setText(tvEnterText.getText().toString());
                    return;
                }
                AssetManager assetManager = getAssets();
                InputStream istr = null;
                try {
                    istr = assetManager.open(textureList_text.get(position));
                    Bitmap bitmap = BitmapFactory.decodeStream(istr);
                    innerTexture(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sbTextShadow.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tvEnterText.setShadowLayer(progress, -1, 1, mSelectedColor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressed();
                break;

            case R.id.ivDone:
                if (tvEnterText.getText().toString().equals("")) {
                    Toast.makeText(KAZ_EditActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    finalBitmapText = getMainFrameBitmap();
                    AddText();
                    add_text.setVisibility(View.GONE);
                }
                break;

            case R.id.ivClearText:
                openDialogForText();
                break;

            case R.id.ivSize:
                llShadowContainer.setVisibility(View.GONE);
                sbOpacity.setVisibility(View.GONE);
                hlvTexture.setVisibility(View.GONE);
                if (tvEnterText.getText().toString().equals("")) {
                    Toast.makeText(KAZ_EditActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    if (sbSize.getVisibility() == View.VISIBLE) {
                        sbSize.setVisibility(View.GONE);
                    } else {
                        sbSize.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case R.id.ivFont:
                llShadowContainer.setVisibility(View.GONE);
                sbOpacity.setVisibility(View.GONE);
                sbSize.setVisibility(View.GONE);
                hlvTexture.setVisibility(View.GONE);
                if (tvEnterText.getText().toString().equals("")) {
                    Toast.makeText(KAZ_EditActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    openDialogForFontList();
                }
                break;

            case R.id.ivOpacity:
                llShadowContainer.setVisibility(View.GONE);
                hlvTexture.setVisibility(View.GONE);
                sbSize.setVisibility(View.GONE);
                if (tvEnterText.getText().toString().equals("")) {
                    Toast.makeText(KAZ_EditActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    if (sbOpacity.getVisibility() == View.VISIBLE) {
                        sbOpacity.setVisibility(View.GONE);
                    } else {
                        sbOpacity.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case R.id.ivDoneForEnterText:
                if (etForEnterText.getText().toString().equals("")) {
                    etForEnterText.setError("Please Add Text First");
                } else {
                    tvEnterText.setText(etForEnterText.getText().toString());
                    dialogForText.dismiss();
                }
                break;

            case R.id.ivInnerTexture:
                sbSize.setVisibility(View.GONE);
                sbOpacity.setVisibility(View.GONE);
                llShadowContainer.setVisibility(View.GONE);
                if (tvEnterText.getText().toString().equals("")) {
                    Toast.makeText(KAZ_EditActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    if (hlvTexture.getVisibility() == View.VISIBLE) {
                        hlvTexture.setVisibility(View.GONE);
                    } else {
                        listTexture_text("textture");
                        KAZTextureAdapter = new KAZ_TextureAdapter(textureList_text, KAZ_EditActivity.this);
                        hlvTexture.setAdapter(KAZTextureAdapter);
                        hlvTexture.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case R.id.ivShadow:
                sbSize.setVisibility(View.GONE);
                sbOpacity.setVisibility(View.GONE);
                hlvTexture.setVisibility(View.GONE);
                if (tvEnterText.getText().toString().equals("")) {
                    Toast.makeText(KAZ_EditActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {

                    if (llShadowContainer.getVisibility() == View.VISIBLE) {
                        llShadowContainer.setVisibility(View.GONE);
                    } else {
                        llShadowContainer.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case R.id.ivShadowColor:
                if (tvEnterText.getText().toString().equals("")) {
                    Toast.makeText(KAZ_EditActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    colorPickerDialog();
                }
                break;

            case R.id.ivTextColor:
                sbOpacity.setVisibility(View.GONE);
                sbSize.setVisibility(View.GONE);
                hlvTexture.setVisibility(View.GONE);
                llShadowContainer.setVisibility(View.GONE);

                blnIsFromTextColor = true;
                if (tvEnterText.getText().toString().equals("")) {
                    Toast.makeText(KAZ_EditActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    colorPickerDialog();
                }
                break;
        }
    }

    private void listTexture_text(String textture) {
        textureList_text = new ArrayList<>();
        textureList_text.clear();
        Resources res = getResources(); //if you are in an activity
        AssetManager am = res.getAssets();
        String fileList[] = new String[0];
        try {
            fileList = am.list(textture);
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    textureList_text.add(textture + "/" + fileList[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Bitmap getMainFrameBitmap() {

        tvEnterText.setDrawingCacheEnabled(true);

        Bitmap bitmap = Bitmap.createBitmap(tvEnterText.getDrawingCache());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bitmap.setConfig(Bitmap.Config.ARGB_8888);
        }
        tvEnterText.setDrawingCacheEnabled(false);
        Bitmap bmp = bitmap;

        int imgHeight = bmp.getHeight();
        int imgWidth = bmp.getWidth();
        int smallX = 0, largeX = imgWidth, smallY = 0, largeY = imgHeight;
        int left = imgWidth, right = imgWidth, top = imgHeight, bottom = imgHeight;
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight; j++) {
                if (bmp.getPixel(i, j) != Color.TRANSPARENT) {
                    if ((i - smallX) < left) {
                        left = (i - smallX);
                    }
                    if ((largeX - i) < right) {
                        right = (largeX - i);
                    }
                    if ((j - smallY) < top) {
                        top = (j - smallY);
                    }
                    if ((largeY - j) < bottom) {
                        bottom = (largeY - j);
                    }
                }
            }
        }
        Log.d("Trimed bitmap", "left:" + left + " right:" + right + " top:" + top + " bottom:" + bottom);
        bmp = Bitmap.createBitmap(bmp, left, top, imgWidth - left - right, imgHeight - top - bottom);
        return bmp;


    }

    private void colorPickerDialog() {
        ColorPickerDialogBuilder
                .with(KAZ_EditActivity.this)
                .setTitle("Choose color")
                .initialColor(getResources().getColor(R.color.white))
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        if (blnIsFromTextColor == false) {
                            mSelectedColor = selectedColor;
                        } else {
                            tvEnterText.setTextColor(selectedColor);
                            blnIsFromTextColor = false;
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    private void openDialogForText() {
        dialogForText = new Dialog(KAZ_EditActivity.this);
        dialogForText.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogForText.setContentView(R.layout.dialog_for_add_text);

        etForEnterText = dialogForText.findViewById(R.id.etForEnterText);
        ivDoneForEnterText = dialogForText.findViewById(R.id.ivDoneForEnterText);
        ivDoneForEnterText.setOnClickListener(this);

        dialogForText.show();
    }

    private void openDialogForFontList() {
        dialogForFontList = new Dialog(KAZ_EditActivity.this);
        dialogForFontList.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogForFontList.setContentView(R.layout.dialog_font);

        gvFontList = dialogForFontList.findViewById(R.id.gvFontList);
        gvFontList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                tvEnterText.setTypeface(Typeface.createFromAsset(getAssets(), fontList.get(position)));
                dialogForFontList.dismiss();
            }
        });

        listFonts("fonts");
        KAZFontListAdapter = new KAZ_FontListAdapter(fontList, KAZ_EditActivity.this);
        gvFontList.setAdapter(KAZFontListAdapter);

        dialogForFontList.show();
    }


    private void listFonts(String dirFrom) {
        fontList = new ArrayList<>();
        fontList.clear();
        Resources res = getResources(); //if you are in an activity
        AssetManager am = res.getAssets();
        String fileList[] = new String[0];
        try {
            fileList = am.list(dirFrom);
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    fontList.add(dirFrom + "/" + fileList[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("WrongConstant")
    private void innerTexture(Bitmap bitmap) {
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        tvEnterText.setLayerType(1, null);
        tvEnterText.getPaint().setShader(shader);

    }
//

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void saveImage(Bitmap image) {

        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG, "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());

        }
    }

    private File getOutputMediaFile() {
//        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
//                + "/Android/data/"
//                + getApplicationContext().getPackageName()
//                + "/Files");

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "KAZ All Frames");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName = "MI_" + timeStamp + ".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    public static Bitmap doBrightness(Bitmap src, int value) {
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        int A, R, G, B;
        int pixel;

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);

                // increase/decrease each channel
                R -= value;
                if (R > 255) {
                    R = 255;
                } else if (R < 0) {
                    R = 0;
                }

                G -= value;
                if (G > 255) {
                    G = 255;
                } else if (G < 0) {
                    G = 0;
                }

                B -= value;
                if (B > 255) {
                    B = 255;
                } else if (B < 0) {
                    B = 0;
                }

                // apply new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final image
        return bmOut;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 20:
                AddText();
                break;
        }
    }


    private void listTexture(String dirFrom) {
        stickerlist = new ArrayList<>();
        stickerlist.clear();
        Resources res = getResources();
        AssetManager am = res.getAssets();
        String fileList[] = new String[0];
        try {
            fileList = am.list(dirFrom);
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    stickerlist.add(dirFrom + "/" + fileList[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void AddText() {
        sticker = new KAZ_StickerImageViewKAZ(KAZ_EditActivity.this, onTouchSticker);
        Bitmap textstickerId = finalBitmapText;
        sticker.setImageBitmap(textstickerId);
        Random r = new Random();
        view_id = r.nextInt();
        if (view_id < 0) {
            view_id = view_id - (view_id * 2);
        }
        sticker.setId(view_id);
        stickerviewId.add(view_id);
        sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sticker.setControlItemsHidden(false);
            }
        });
        container.addView(sticker);
    }


    private KAZ_DemoStickerView.OnTouchSticker onTouchSticker = new KAZ_DemoStickerView.OnTouchSticker() {
        @Override
        public void onTouchedSticker() {
            removeBorder();
            horizonatalscrollviewframe.setVisibility(View.GONE);
            seekBar.setVisibility(View.GONE);
            horizonatalscrollview.setVisibility(View.GONE);
            recycle.setVisibility(View.GONE);
        }
    };


    private void removeBorder() {
        for (int i = 0; i < stickerviewId.size(); i++) {
            View view = container.findViewById(stickerviewId.get(i));
            if (view instanceof KAZ_StickerImageViewKAZ) {
                KAZ_StickerImageViewKAZ stickerView = (KAZ_StickerImageViewKAZ) view;
                stickerView.setControlItemsHidden(true);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(KAZ_EditActivity.this, KAZ_FrameListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}