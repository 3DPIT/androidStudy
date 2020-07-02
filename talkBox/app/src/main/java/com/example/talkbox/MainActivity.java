package com.example.talkbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage();
            }
        });

    }
    public void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내"); /**타이틀 생성*/
        builder.setMessage("종료 하시겠습니까?");
        /**
         * R.drawable.ic_dialog_alert 이렇게 설정하면 현재 프로젝트 안에서만
         * android.R.drawable.ic_dialog_alert 하면
         * android api에 있는것을 가져다 쓸 수 있다.
         * */
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        /**
         * 버튼 생성하기 긍정 버튼 YES
         * */
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "예버튼 눌림", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 버튼 생성하기 긍정 버튼 NO
         * */
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "아니오버튼 눌림", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 빌더의 작업이 끝나면
         *  AlertDialog dialog = builder.create();이렇게 생성해서
         */
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}