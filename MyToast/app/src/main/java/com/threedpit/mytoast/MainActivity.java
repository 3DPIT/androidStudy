package com.threedpit.mytoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**레이아웃인플레터를 이용해서
                 * layout의 toast_border.xml을 View를 이용해서
                 * 보여주는것*/
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_border,(ViewGroup)findViewById(R.id.toast_layout_root));

                /**텍스트를 따로 만들어준다.*/
                TextView text = (TextView)layout.findViewById(R.id.text);
                text.setText("토스트 메시지 입니다.");

                /**이렇게 객체를 생성 할 수 있다.*/
                Toast toastView = new Toast(getApplicationContext());
                /**위치를 선정을 합니다.*/
                toastView.setGravity(Gravity.TOP | Gravity.LEFT,200,800);
                /**만든 레이아웃을 보여준다.*/
                toastView.setView(layout);
                /**토스트 메시지를 보여준다.*/
                toastView.show();
            }
        });

    }
}
