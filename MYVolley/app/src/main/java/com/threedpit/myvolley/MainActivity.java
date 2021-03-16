package com.threedpit.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    static RequestQueue requestQueue; //요청 객체를 넣는 큐
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlStr = editText.getText().toString();
                request(urlStr);//요청해주는 메소드
            }
        });
        //이렇게 까지 하면 onCreate() 안에서
        //RequestQueue 라고 하는 객체가 하나 만들어 진것
        requestQueue= Volley.newRequestQueue(getApplicationContext());
    }
    public void request(String urlStr){ // 현재 이것은 요청 객체 하나 만든것
        //문자열로 보낼 수있는 객체   StringRequest
        StringRequest request = new StringRequest(
                Request.Method.GET,//GET방식인 POST 방식인지 지정
                urlStr,//문자열로 된 문자열 정보 어떤 사이트 접근 할 것인지
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //정상적으로 위의 정보로 요청을 보냈을때
                        //정상적으로 오는 응답이 이곳으로 떨어진다.

                        //볼리는 핸들러 처리 없이 그냥 바로 메소드 실행 가능
                        println("응답 ->"+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 ->"+error.toString());
                    }
                }
        ){//요청 파라미터를 처리하는 매소드
            //요청 파라미터는 웹에서 서버 쪽으로 데이터를 보낼 수 있는 방법
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<String, String>();
                return parms;// 요청 객체가 만들어진것이다.
            }
        };
        //요청객체를 만들었다면 그 객체에 대해서
        // 요청 받고 응답 받으면 캐싱을  해놓는데
        //  그럼 요청을 보낼 때 마다 새롭게 보내는것이 아니고 기존 응답을 가져올 수 있다.
        request.setShouldCache(false);
        requestQueue.add(request);// 아까 말한것 처럼 큐에 넣으면 알아서 처리됨
        println("요청 보냄");
    }
    public void println(String data){//텍스트 뷰에 찍히게 하는 메소드
        textView.append(data+"\n");
    }
}
