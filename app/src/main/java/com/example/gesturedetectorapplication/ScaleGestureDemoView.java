package com.example.gesturedetectorapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by xwxwaa on 2019/5/28.
 */

public class ScaleGestureDemoView extends View{

    private static final String TAG="ScaleGestureDemoView";

    private ScaleGestureDetector scaleGestureDetector;

    public ScaleGestureDemoView(Context context) {
        super(context);
        init();
    }

    public ScaleGestureDemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        scaleGestureDetector=new ScaleGestureDetector(getContext(),new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                // 缩放手势触发
                // 主要关心这两个值，缩放中心点和缩放因子。
                // 中心点：将所有的坐标都加起来，然后除以数量。
                // 缩放因子：计算各个手指到焦点的平均距离，在用户手指移动后用新的平均距离除以旧的平均距离，并以此计算得出缩放比例。
                Log.e(TAG, "focusX = " + detector.getFocusX());       // 缩放中心，x坐标
                Log.e(TAG, "focusY = " + detector.getFocusY());       // 缩放中心y坐标
                Log.e(TAG, "scale = " + detector.getScaleFactor());   // 缩放因子
                return true;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                // 缩放手势开始
                Log.e(TAG,"缩放手势开始");
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
//                super.onScaleEnd(detector);
                // 缩放手势结束
                Log.e(TAG,"缩放手势结束");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }
}
