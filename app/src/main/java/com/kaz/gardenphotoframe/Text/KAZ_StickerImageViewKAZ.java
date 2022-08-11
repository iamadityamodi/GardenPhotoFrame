package com.kaz.gardenphotoframe.Text;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;



public class KAZ_StickerImageViewKAZ extends KAZ_DemoStickerView {

    private String owner_id;
    private ImageView iv_main;

    public KAZ_StickerImageViewKAZ(Context context, OnTouchSticker onTouchSticker) {
        super(context, onTouchSticker);
    }

    public KAZ_StickerImageViewKAZ(Context context, AttributeSet attrs, OnTouchSticker onTouchSticker) {
        super(context, attrs, onTouchSticker);
    }

    public KAZ_StickerImageViewKAZ(Context context, AttributeSet attrs, int defStyle, OnTouchSticker onTouchSticker) {
        super(context, attrs, defStyle, onTouchSticker);
    }

    public void setOwnerId(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwnerId() {
        return this.owner_id;
    }

    @Override
    public View getMainView() {
        if (this.iv_main == null) {
            this.iv_main = new ImageView(getContext());
          //  this.iv_main.setScaleType(ImageView.ScaleType.FIT_XY);
            
        }
        return iv_main;
    }

    public void setImageBitmap(Bitmap bmp) {
        this.iv_main.setImageBitmap(bmp);
    }

    public void setImageResource(int res_id) {
        this.iv_main.setImageResource(res_id);
    }

    public void setImageDrawable(Drawable drawable) {
        this.iv_main.setImageDrawable(drawable);
    }

    public Bitmap getImageBitmap() {
        return ((BitmapDrawable) this.iv_main.getDrawable()).getBitmap();
    }

}
