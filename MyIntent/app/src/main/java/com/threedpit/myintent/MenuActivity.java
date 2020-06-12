package com.threedpit.myintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                /**데이터를 넣은것 put Extra로*/
                intent.putExtra("name","3dpit");
                /**돌아갈때 메인엑티비티에 전달됨\
                 * 또는 RESULT_OK 라고 해도된다.*/
                setResult(200,intent);
                finish();
            }
        });
    }
}
