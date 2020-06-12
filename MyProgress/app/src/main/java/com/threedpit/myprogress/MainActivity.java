package com.threedpit.myprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    int value =0;
    ProgressBar prg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prg = findViewById(R.id.progressBar);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value+=10;/**버튼 누르면 10씩 증가*/
                if(value>100)value=0;
                prg.setProgress(value);/**프로그래스바 표현*/
            }
        });
    }
}
