package com.threedpit.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButton2Clicked(View v){
        /**
         인텐트라는 객체를 생성하고 사이트 주소를 넣어주었고
         */
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.daum.net"));
        startActivity(intent); /**인텐트를 실행 하기 위한 것 */
    }
    public void onButton3Clicked(View v){
        /**
         인텐트라는 객체를 생성하고 전화 번호를 넣어주었다.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-6888-7481"));
        startActivity(intent);/**인텐트를 실행 하기 위한 것 */
    }
}
