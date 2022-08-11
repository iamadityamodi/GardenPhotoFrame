package com.kaz.gardenphotoframe.Text;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.kaz.gardenphotoframe.KAZ_HorizontalListView;
import com.kaz.gardenphotoframe.R;


public class KAZ_TextActivity extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        getWindow().setFlags(1024, 1024);


        bindView();
        blnIsFromTextColor = false;

        openDialogForText();

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
                    istr = assetManager.open(textureList.get(position));
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
                    Toast.makeText(KAZ_TextActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    finalBitmapText = getMainFrameBitmap();
                    setResult(RESULT_OK);
                    finish();
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
                    Toast.makeText(KAZ_TextActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(KAZ_TextActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    openDialogForFontList();
                }
                break;

            case R.id.ivOpacity:
                llShadowContainer.setVisibility(View.GONE);
                hlvTexture.setVisibility(View.GONE);
                sbSize.setVisibility(View.GONE);
                if (tvEnterText.getText().toString().equals("")) {
                    Toast.makeText(KAZ_TextActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(KAZ_TextActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    if (hlvTexture.getVisibility() == View.VISIBLE) {
                        hlvTexture.setVisibility(View.GONE);
                    } else {
                        listTexture("textture");
                        KAZTextureAdapter = new KAZ_TextureAdapter(textureList, KAZ_TextActivity.this);
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
                    Toast.makeText(KAZ_TextActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(KAZ_TextActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(KAZ_TextActivity.this, "Please Add Text First", Toast.LENGTH_SHORT).show();
                } else {
                    colorPickerDialog();
                }
                break;
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
                .with(KAZ_TextActivity.this)
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
        dialogForText = new Dialog(KAZ_TextActivity.this);
        dialogForText.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogForText.setContentView(R.layout.dialog_for_add_text);

        etForEnterText = dialogForText.findViewById(R.id.etForEnterText);
        ivDoneForEnterText = dialogForText.findViewById(R.id.ivDoneForEnterText);
        ivDoneForEnterText.setOnClickListener(this);

        dialogForText.show();
    }

    private void openDialogForFontList() {
        dialogForFontList = new Dialog(KAZ_TextActivity.this);
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
        KAZFontListAdapter = new KAZ_FontListAdapter(fontList, KAZ_TextActivity.this);
        gvFontList.setAdapter(KAZFontListAdapter);

        dialogForFontList.show();
    }

    private void bindView() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);

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

    private void listTexture(String dirFrom) {
        textureList = new ArrayList<>();
        textureList.clear();
        Resources res = getResources(); //if you are in an activity
        AssetManager am = res.getAssets();
        String fileList[] = new String[0];
        try {
            fileList = am.list(dirFrom);
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    textureList.add(dirFrom + "/" + fileList[i]);
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

    @Override
    public void onBackPressed() {

        Toast.makeText(this, "Create Text", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(TextActivity.this, EditActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
