package com.threedpit.myreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SmsActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        Intent intent = getIntent();
        processIntent(intent);

    }
    //현재 onCreate는 실행된거라
    //intent는 onNewIntent로 받아지기때문에 아래와 같이 해준다.

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
       // 액티비티는 미리 만들어져있는 상태에서
       // intent만 전달되기때문에 이렇게 해준다.
        processIntent(intent);
    }

    public void processIntent(Intent intent){
        if(intent != null){
         String sender = intent.getStringExtra("sender");
         String contents = intent.getStringExtra("contents");

            textView2.setText(sender);
            textView3.setText(contents);
        }
        else{
            finish();
        }
    }
}
