package com.threedpit.mycallintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 =  findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-5678"));
                /**
                 * 직접 화명르 띄우는 것이 아니고 시스템적으로
                 * 요청을 하게 되고, OS 안에 Activity 매니져라는게
                 * 이것을 받게 된다.
                 * 이전화 번호가 있으니 전화 걸기 원한다
                 * 생각하고 단말의 전화 걸기앱이 실행된다.
                 * */
                startActivity(intent);
            }
        });
    }
}
