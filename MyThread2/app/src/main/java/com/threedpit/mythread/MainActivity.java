package com.threedpit.mythread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    //MainHandler handler;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread = new BackgroundThread() ;
                thread.start();//run의 메소드안에 있는것이 실행된다.
            }
        });
        //handler = new MainHandler();
    }
    //가장기본적인게 쓰레드를 상속한 클래스 정의해서 그클래스로 객체 만들어 시작
    class BackgroundThread extends Thread{
        public void run(){//이 메소드는 쓰레드를 시작하면 run 매소드 자동실행
            int value = 0;
            for(int i =0; i<100;i++){
                try {
                    Thread.sleep(1000);//1 초동안 쉬기
                }catch (Exception e){};//sleep 이 예외가 생길수 있으므로 예외처리를 해줘야한다.
                value+=1;
                Log.d("MyThread","value : "+value);//1초에 한번씩 출력하는 예제
                final int finalValue = value;

                //몇초후에 동작하게하기 위해서는
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("값 : "+ finalValue);
                    }
                },5000);
                //textView.setText("값 : "+ value);
                // 이렇게 그냥 쓰는경우에는 메인쓰레드에서 원래 view에
                //접근을 하는데 그렇게 되면 동시접근으로 인해서 문제가 발생한다.
                //그래서 handler를 사용하여 main쓰레드에서 순서대로 작업을 해준다

              /* Message message =  handler.obtainMessage();
               Bundle bundle = new Bundle();
               bundle.putInt("value",value);
               message.setData(bundle);
               handler.sendMessage(message); //이렇게 해서 우선 핸들러로 넘겨줘야한다.*/

            }
        }
    }
    /*
    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            //핸들러 쪽으로 메세지를 보내는데 이것은 메인스레드에서 동작을 하게 된다.
            //동시접근의 문제를 해결 할 수 있다.
            super.handleMessage(msg);
           Bundle bundle=msg.getData(); //아까 bundle에넣어message로 보낸것이 여기로 전달된다.
            int value = bundle.getInt("value");
            textView.setText("value : "+value);
        }
    }*/

}
