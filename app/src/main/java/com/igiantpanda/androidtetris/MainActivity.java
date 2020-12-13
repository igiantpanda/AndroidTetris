package com.igiantpanda.androidtetris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private GameView mGameView;
    private Button mBtnStart;
    private Button mBtnStop;
    private Button mBtnLeft;
    private Button mBtnRight;
    private Button mBtnDown;
    private Button mBtnRotate;
    private Paint mMapPaint;
    private boolean mMaps[][] = new boolean[10][20];
    private int mGameWidth;
//    private int mGameHeight;
    private int mBoxSize;

//    public MainActivity(Button mBtnStart) {
//        this.mBtnStart = mBtnStart;
//    }


    private int getScreenWidth() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    private void initData() {
        Log.d(TAG, "initData");
        int width = getScreenWidth();
        mGameWidth = width / 2;
//        mGameHeight = width;
        mBoxSize = mGameWidth / mMaps.length;

        mMapPaint = new Paint();
        mMapPaint.setColor(0x50000000);
        mMapPaint.setAntiAlias(true);
        mMapPaint.setStyle(Paint.Style.STROKE);
    }

    private void initViews() {
        Log.d(TAG, "initViews");
//        FrameLayout gameLayout = findViewById(R.id.game_layout);
//        mGameView = new View(this) {
//            @Override
//            protected void onDraw(Canvas canvas) {
//                super.onDraw(canvas);
//                Log.d(TAG, "onDraw");
//                for (int x = 0; x < mMaps.length; x++) {
//                    for (int y = 0; y < mMaps[x].length; y++) {
//                        if (mMaps[x][y]) {
//                            canvas.drawRect(x * mBoxSize, y * mBoxSize, x * mBoxSize + mBoxSize, y * mBoxSize + mBoxSize, mMapPaint);
//                        }
//                    }
//                }
//            }
//        };
        mGameView = findViewById(R.id.game_view);
        int padding = mGameWidth / 64;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(mGameView.getLayoutParams());
        layoutParams.width = mGameWidth;
        layoutParams.height = (mGameWidth - padding) * 2;
        layoutParams.leftMargin = padding;
        layoutParams.rightMargin = padding;
        layoutParams.topMargin = padding;
        layoutParams.bottomMargin = padding;
        mGameView.setLayoutParams(layoutParams);
        mGameView.setPadding(padding, padding, padding, padding);
        mGameView.setBackgroundColor(getResources().getColor(R.color.colorGameBg));
//        gameLayout.addView(mGameView);

        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(v -> mGameView.start());

        mBtnStop = findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(v -> mGameView.stop());

        mBtnLeft = findViewById(R.id.btn_left);
        mBtnLeft.setOnClickListener(v -> mGameView.move(IShape.Direction.LEFT));

        mBtnRight = findViewById(R.id.btn_right);
        mBtnRight.setOnClickListener(v -> mGameView.move(IShape.Direction.RIGHT));

        mBtnDown = findViewById(R.id.btn_down);
        mBtnDown.setOnClickListener(v -> mGameView.move(IShape.Direction.DOWN));

        mBtnRotate = findViewById(R.id.btn_rotate);
        mBtnRotate.setOnClickListener(v -> mGameView.rotate());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();
        mMaps[2][3] = true;
        mGameView.invalidate();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mGameView.start();
    }
}
