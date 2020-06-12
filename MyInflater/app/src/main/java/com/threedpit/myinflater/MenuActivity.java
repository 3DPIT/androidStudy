package com.threedpit.myinflater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        container =findViewById(R.id.container);

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout();
            }
        });
    }
    public void addLayout(){
        /**
         * 레이아웃 화면을 setContentView가 아니라
         * 직접 인플레이션 하기 위한 함수
         * */
        LayoutInflater inflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       /**
        * 인플레이터를 생성한것에
        * R.layout.sub1 이것을
        * container 에 true(넣어주라)라는 의미
        * */
        inflater.inflate(R.layout.sub1,container,true);
        Toast.makeText(this,"부분화면추가",Toast.LENGTH_LONG).show();
    }
}
