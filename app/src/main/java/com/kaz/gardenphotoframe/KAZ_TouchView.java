package com.kaz.gardenphotoframe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class KAZ_TouchView extends ImageView {

    private static final int PADDING = 8;
    private static final float STROKE_WIDTH = 8.0f;
    private Paint mBorderPaint;

    public KAZ_TouchView(Context context) {
        this(context, null);
    }

    public KAZ_TouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setPadding(PADDING, PADDING, PADDING, PADDING);
    }

    public KAZ_TouchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(Color.WHITE);
        mBorderPaint.setStrokeWidth(STROKE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING, mBorderPaint);
    }


}
