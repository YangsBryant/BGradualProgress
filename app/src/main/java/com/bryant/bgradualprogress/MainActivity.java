package com.bryant.bgradualprogress;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.bryant.progresslibrary.BGradualProgress;

public class MainActivity extends AppCompatActivity {

    private BGradualProgress progress,progress2,progress3,progress4;
    private int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = findViewById(R.id.progress);
        progress.setProgress(30);
        progress.setText("进度");

        progress2 = findViewById(R.id.progress2);
        progress2.setProgress(40);
        progress2.setText("进度");

        progress3 = findViewById(R.id.progress3);
        progress3.setProgress(80);
        progress3.setText("警告"+progress3.getProgress()+"%");

        progress4 = findViewById(R.id.progress4);
        progress4.setProgress(80);
        progress4.setText("渐变"+progress4.getProgress()+"%");

        new MyThread().start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num++;
                Message msg = new Message();
                msg.what = 0;
                msg.arg1 = num;
                mHandler.sendMessage(msg);
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    progress2.setProgress(msg.arg1);
                    progress2.setText(msg.arg1+"%");
                    if(msg.arg1>=100){
                        num = 0;
                    }
                    break;
            }
        }
    };
}
