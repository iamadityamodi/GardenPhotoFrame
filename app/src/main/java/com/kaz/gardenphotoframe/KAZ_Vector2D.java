package com.kaz.gardenphotoframe;

import android.graphics.PointF;

public class KAZ_Vector2D extends PointF {

    public KAZ_Vector2D() {
        super();
    }

    public KAZ_Vector2D(float x, float y) {
        super(x, y);
    }

    public void normalize() {
        float length = (float) Math.sqrt(x * x + y * y);
        x /= length;
        y /= length;
    }

    public static float getAngle(KAZ_Vector2D vector1, KAZ_Vector2D vector2) {
        vector1.normalize();
        vector2.normalize();
        double degrees = (180.0 / Math.PI) * (Math.atan2(vector2.y, vector2.x) - Math.atan2(vector1.y, vector1.x));
        return (float) degrees;
    }
}
