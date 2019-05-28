package com.example.gesturedetectorapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_doubleTap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    // 创建一个监听回调
    final GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener(){
        /**
         * 单击事件发生后300ms后触发
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // 如果想同时监听双击与单击事件，则监听单击事件不推荐使用OnClickListener，原因有二：
            // 一，它们是存在冲突的，若要同时出发，则 setOnTouchListener 不能消费事件，如果 onTouchListener 消费了事件，就可能导致 OnClick 无法正常触发。
            // 二，使用 OnClickListener 会在双击事件发生时触发两次
            Toast.makeText(MainActivity.this,"不要单击",Toast.LENGTH_SHORT).show();
            return super.onSingleTapConfirmed(e);
        }
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(MainActivity.this,"双击666",Toast.LENGTH_SHORT).show();
            Log.e("TAG","第二次按下时触发");
            return super.onDoubleTap(e);
        }

        /**
         * 与 onDoubleTap 的差别很小
         *  onDoubleTapEvent 用来进行更细微的控制
         *
         *  函数的执行顺序如下：
         *  onDoubleTap
         *  onDoubleTapEvent - down
         *  onDoubleTapEvent - move
         *  onDoubleTapEvent - up
         */
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            switch (e.getActionMasked()){
                case MotionEvent.ACTION_DOWN:
                    Log.e("TAG","ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e("TAG","ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.e("TAG","第二次抬起时触发");
                    break;
                    default:
                        break;
            }
            return super.onDoubleTapEvent(e);
        }
    };

    // 创建一个检测器
    GestureDetector gestureDetector;
    private void initData() {
        // 使用无Handler的构造函数
          gestureDetector = new GestureDetector(this,onGestureListener);

        // 使用有Handler的构造函数的几种方式；
        // 1.主线程中创建Handler
        // 重点在于传递的 Handler 一定要有 Looper
//        final Handler handler = new Handler();
//        // 主线程创建的hanlder会自动关联主线程的Looper
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                gestureDetector = new GestureDetector(MainActivity.this,onGestureListener,handler);
//            }
//        }).start();

        // 2.子线程中创建Handler
        // 重点在于传递的 Handler 一定要有 Looper
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Handler handler1 = new Handler(Looper.getMainLooper());
//                gestureDetector = new GestureDetector(MainActivity.this,onGestureListener,handler1);
//            }
//        }).start();

        // 3.若子线程准备了 Looper 那么可以直接使用无Handler的构造函数进行创建
        // 重点在于传递的 Handler 一定要有 Looper
//        new Thread(new Runnable() {
//            @Override public void run() {
//                Looper.prepare(); // <- 重点在这里
//                gestureDetector = new GestureDetector(MainActivity.this,onGestureListener);
//            }
//        }).start();

        // 给监听器设置数据源
        btn_doubleTap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    private void initView() {
        btn_doubleTap = findViewById(R.id.btn_doubleTap);
    }


}
