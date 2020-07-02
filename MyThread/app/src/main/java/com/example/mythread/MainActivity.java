package com.example.mythread;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("메인 스레드 시작"," ");
        ExampleThread thread = null;
        for(int i = 0; i <=3 ; i++) {
            thread = new ExampleThread(i);
            thread.start();
        }

        try{
            thread.join();
        }catch (InterruptedException e)
        {

        }
        Log.i("메인 스레드 종료"," ");

    }


    private class ExampleThread extends Thread {

        private int threadNum = 0;

        public ExampleThread(int threadNum) {
            // 초기화 작업
            this.threadNum = threadNum;
        }

        public void run() {

            Log.i("시작된 스레드", Integer.toString(threadNum));
            try {
                // 스레드에게 수행시킬 동작들 구현
                Thread.sleep(1000); // 1초간 Thread를 잠재운다
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.i("종료된 스레드", Integer.toString(threadNum));
        }
    }
}