package com.nothing.android_week9_menu_searchview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CountDownActivity extends AppCompatActivity {
    private TextView mTextCount;
    CountdownThread mThread;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        mTextCount = (TextView) findViewById(R.id.text_count);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int currentTime = (int) msg.obj;
                mTextCount.setText(String.valueOf(currentTime));
            }
        };
        mThread = new CountdownThread();
        mThread.start();
        // dừng thread trong vòng 1000ms
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mTextCount.setText("Something");
//            }
//        }, 1000);
    }

    @Override
    protected void onDestroy() {
        if (mThread.isAlive()) {
            mThread.interrupt();
        }
        super.onDestroy();
    }

    public class CountdownThread extends Thread {
        private int mCurrentTime = 0;

        @Override
        public void run() {
            while (mCurrentTime <= 15) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTextCount.setText(String.valueOf(mCurrentTime));
//                    }
//                });
                Message msg = new Message();
                msg.obj = mCurrentTime;
                mHandler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                    mCurrentTime++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
