package com.threedpit.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;//웹 서버쪽의 주소
    TextView textVeiw;

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textVeiw = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//버튼을 눌렀을 때 웹으로 요청하겠다.
               final String urlStr = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);//웹으로 요청하는것으로 네트워킹이므로 쓰레드를 사용해야한다.
                    }
                }).start();
            }
        });
    }

    public void request(String urlStr){
        //요청을 하는 가장 기본적인 클래스
        //http Url Connection이라는 것
        try {

            StringBuilder builder = new StringBuilder();//데이터 저장소소

           URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn !=null ){
                conn.setConnectTimeout(10000);//10초동안 연결 안되면 끊기
                conn.setRequestMethod("GET");//요청 방식
                conn.setDoInput(true);//요청을 보내고 받는것중에 응답 받는것 설정

                int resCode = conn.getResponseCode();//이때 요청을 보내고 응답을 받음
                //내부적으로 소켓연결을 만들고 그위에 http로 데이터를 요청하고 응답을 받는 과정

                //웹은 원래 연결을 만들고 나서 계속 유지하지 않고
                //그냥 연결을 끊어버린다. 연결을 유지하는 옵션이 없는것은 아니다.

                //InputStreamReader나 writer 같은것들은
                //데이터를 주고 받을때 주로 문자열로 주고 받는데
                //그런것을 간단히 처리하게 해주는것  Reader는 읽어오는것
                // BufferedReader로 묶어주게 되면 버퍼를 써서 더 성능을 향상 시키는 방법

           BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));// 데이터를 받는 통로
            String line =null;
            while(true){
                line = reader.readLine();// 한줄씩 읽어줌, 줄바꿈 기호 까지
                if(line == null)break;

                builder.append(line+"\n");
            }
                reader.close();
                conn.disconnect();
            }
            println("응답 ->"+builder.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textVeiw.append(data+"\n");
            }
        });
    }

}
