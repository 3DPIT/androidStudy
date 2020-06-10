package com.threedpit.myorientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name;
    EditText editText;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast("onCreate 호출");

        editText = findViewById(R.id.editText);
        textView2 = findViewById(R.id.textView2);

        Button btn = findViewById(R.id.button);
        if(btn!=null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText != null) {
                        /**editText안에 문자가 들어가 있으면*/
                        name = editText.getText().toString();
                        /**editText안의 문자를
                         * 문자열로 바꿔서 name 스트링 변수에
                         * 넣는다.*/
                        showToast("사용자 입력값을 name 변수에 활당함");
                    }
                }
            });
        }
        /**protected void onCreate(Bundle savedInstanceState) {
         * 이부분을 보면 savedInstanceState 라는 변수가 있다.
         *     public void onSaveInstanceState(@NonNull Bundle outState) {
         *     여기의 이 번들 객체와 같다라고 생각하면된다.
         *
         */
        if(savedInstanceState!=null){
            if(textView2!=null){
                name =savedInstanceState.getString("name");
                /**이 번들 즉 savedInstanceState의 데이터를 빼달라는 함수*/
                textView2.setText(name);

                showToast("값을 복원했다."+ name);

            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        /**번들은 어떤 데이터들을 담아주느것이라고 생각하자*/
        outState.putString("name",name);
        /**액티비티가  화면상에 없어지는 순간에
         * 이 함수가 호출되면서 이데이터가 어디에
         * 저장되는것이다.
         * 어떻게 보관하는가?...
         * */
    }

    @Override
    protected void onDestroy() {
        showToast("onDestory 호출");
        super.onDestroy();
    }

    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }
}
