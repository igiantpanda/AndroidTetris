package com.igiantpanda.androidtetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.shapes.Shape;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.igiantpanda.androidtetris.shape.ShapeFactory;

import java.util.List;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();

    private static final int MOVE_DOWN_INTERVAL = 800;
    private static final int MSG_MOVE_DOWN = 1;
    private static final int sXCount = 10;
    private static final int sYCount = 20;

    private boolean mMaps[][] = new boolean[sXCount][sYCount];
    private int mBoxSize;
    private Paint mBoxCheckPaint;
    private Paint mBoxUncheckPaint;
    private Paint mBorderPaint;
    private Handler mHandler;
    private IShape mShape;
    private boolean mInited;
    private boolean mRunning;

    private class InternalHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
//            Log.d(TAG, "handleMessage:" + msg.what);
            switch (msg.what) {
                case MSG_MOVE_DOWN:
                    if (mRunning) {
                        move(IShape.Direction.DOWN);
                    }
                    mHandler.sendEmptyMessageDelayed(MSG_MOVE_DOWN, MOVE_DOWN_INTERVAL);
                    break;
                default:
                    break;
            }
        }
    }

//    public GameView(Context context) {
//        super(context);
//        init();
//    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Log.d(TAG, "init");
        mHandler = new InternalHandler();
        mBoxCheckPaint = new Paint();
        mBoxCheckPaint.setColor(getResources().getColor(R.color.colorBoxCheck));
        mBoxCheckPaint.setAntiAlias(true);
        mBoxUncheckPaint = new Paint();
        mBoxUncheckPaint.setColor(getResources().getColor(R.color.colorBoxUncheck));
        mBoxUncheckPaint.setAntiAlias(true);
        mBorderPaint = new Paint();
        mBorderPaint.setColor(getResources().getColor(R.color.colorBoxCheck));
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);

        mShape = ShapeFactory.create();

        reInit();
        mHandler.sendEmptyMessage(MSG_MOVE_DOWN);
    }

    private void reInit() {
        mBoxSize = (getWidth() - getPaddingLeft() - getPaddingRight()) / sXCount;
        mBorderPaint.setStrokeWidth(mBoxSize / 8);
        Log.d(TAG, "init mBoxSize:" + mBoxSize);
    }

    private void updateMaps() {
        mShape.getBoxes().forEach(box -> mMaps[box.x][box.y] = true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout changed:" + changed);
        if (changed) {
            reInit();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = (getWidth() - mBoxSize * sXCount) / 2;
        int top = (getHeight() - mBoxSize * sYCount) / 2;

        Log.d(TAG, "onDraw");
        Log.d(TAG, "onDraw H:" + getHeight());
        Log.d(TAG, "onDraw left:" + left);
        Log.d(TAG, "onDraw top:" + top);

        // Draw border
        canvas.drawRect(0, 0, getWidth(), getHeight(), mBorderPaint);


//        if (!mInited) {
//            Log.d(TAG, "onDraw init");
//            mInited = true;
            for (int x = 0; x < mMaps.length; x++) {
                for (int y = 0; y < mMaps[x].length; y++) {
                    Paint paint = mMaps[x][y] ? mBoxCheckPaint : mBoxUncheckPaint;
                    int offset = mBoxSize / 8;
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(mBoxSize / 8);
                    canvas.drawRect(x * mBoxSize + offset + left, y * mBoxSize + offset + top, x * mBoxSize + mBoxSize - offset + left, y * mBoxSize + mBoxSize - offset + top, paint);
                    offset = mBoxSize / 4;
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(x * mBoxSize + offset + left, y * mBoxSize + offset + top, x * mBoxSize + mBoxSize - offset + left, y * mBoxSize + mBoxSize - offset + top, paint);

                }
            }
//        }

//        List<Point> boxes = mShape.getUncheckBoxes();
//        boxes.forEach(box -> {
//            Log.d(TAG, "onDraw Uncheck:" + box);
//
//            int offset = mBoxSize / 8;
//            mBoxUncheckPaint.setStyle(Paint.Style.STROKE);
//            mBoxUncheckPaint.setStrokeWidth(mBoxSize / 8);
//            canvas.drawRect(box.x * mBoxSize + offset, box.y * mBoxSize + offset, box.x * mBoxSize + mBoxSize - offset, box.y * mBoxSize + mBoxSize - offset, mBoxUncheckPaint);
//            offset = mBoxSize / 4;
//            mBoxUncheckPaint.setStyle(Paint.Style.FILL);
//            canvas.drawRect(box.x * mBoxSize + offset, box.y * mBoxSize + offset, box.x * mBoxSize + mBoxSize - offset, box.y * mBoxSize + mBoxSize - offset, mBoxUncheckPaint);
//
//        });
        List<Point> boxes = mShape.getCheckBoxes();
        boxes.forEach(box -> {
//            Log.d(TAG, "onDraw Check:" + box);
            int offset = mBoxSize / 8;
            mBoxCheckPaint.setStyle(Paint.Style.STROKE);
            mBoxCheckPaint.setStrokeWidth(mBoxSize / 8);
            canvas.drawRect(box.x * mBoxSize + offset + left, box.y * mBoxSize + offset + top, box.x * mBoxSize + mBoxSize - offset + left, box.y * mBoxSize + mBoxSize - offset + top, mBoxCheckPaint);
            offset = mBoxSize / 4;
            mBoxCheckPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(box.x * mBoxSize + offset + left, box.y * mBoxSize + offset + top, box.x * mBoxSize + mBoxSize - offset + left, box.y * mBoxSize + mBoxSize - offset + top, mBoxCheckPaint);

        });



    }

    public void start() {
        Log.d(TAG, "start");
        mRunning = true;
    }

    public void stop() {
        Log.d(TAG, "stop");
        mRunning = false;
    }

    public void move(IShape.Direction direction) {
        if (mShape.move(mMaps, direction)) {
            invalidate();
        } else if (direction.equals(IShape.Direction.DOWN)) {
            updateMaps();
            mShape = ShapeFactory.create();
        }
    }

    public void rotate() {
        if (mShape.rotate(mMaps)) {
            invalidate();
        }
    }
}
