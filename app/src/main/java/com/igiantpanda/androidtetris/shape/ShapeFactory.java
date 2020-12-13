package com.igiantpanda.androidtetris.shape;

import android.util.Log;

import com.igiantpanda.androidtetris.IShape;

public class ShapeFactory {
    private static final String TAG = ShapeFactory.class.getSimpleName();
    private static final int SHAPE_COUNT = 5;

    public static IShape create() {
        double r = Math.random();
        int random = (int) (r * 10) % SHAPE_COUNT;

        Log.d(TAG, "create r:" + r);
        Log.d(TAG, "create:" + random);
        switch (random) {
            case 0:
                return new ShapeI();
            case 1:
                return new ShapeL();
            case 2:
                return new ShapeO();
            case 3:
                return new ShapeT();
            case 4:
            default:
                return new ShapeZ();
        }
    }
}
