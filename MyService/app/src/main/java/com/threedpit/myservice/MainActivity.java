package com.threedpit.myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = editText.getText().toString();
            Intent intent = new Intent(getApplicationContext(),MyService.class);
            intent.putExtra("command","show");
            intent.putExtra("name",name);
            //지금까지 우리가 startActivity를 했었는데 그것은 화면을 띄우는경우
                // 지금은 서비스를 하는것 이기 때문에 StartService();로 한다.
            startService(intent);
            }
        });
        Intent intent = getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }

    public  void processIntent(Intent intent){
        // 문제는 이미 메인액티비티가 메인 메모리에 만들어져 있는 상태인경우
        //onCreate가 실행이 되지 않는다.그래서 그것 대신에 위에onNewIntent이 호출된다.

       // 저렇게 onNEwIntent를 선언하고
        if(intent !=null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");
            Toast.makeText(this,"command : "+command+"name :"+name,Toast.LENGTH_LONG).show();

        }

    }

}
