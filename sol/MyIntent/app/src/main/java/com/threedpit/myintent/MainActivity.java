package com.threedpit.myintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent,101);
                /**
                 * 화면을 띄우고 거기에 대한 응답을
                 * 받고 싶은 경우에
                 * startActivityForResult(intent,101);
                 * 101은 어떤화면 으로 부터 왔는지 구분할수 있는 코드라고
                 * 요청 코드라고 합니다.
                 * */
            }
        });
    }
    /**이아래 메소드는
     * 메뉴엑티비티에서 메인 엑티비티로 돌아 왔을때 실행된다.*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       /**resultCode 아까 메뉴 엑티비티부분에서 전달한 200 이 들어오는것
        * data는 보낸 데이터 3dpit이 전달이 된다.
        * */
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){/**이런식으로 어디서 왔는지 구분 가능*/
            if(data !=null){
                String name = data.getStringExtra("name");
                if(name!=null){
                    Toast.makeText(this,"응답으로 받은 데이터 : " + name,Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
