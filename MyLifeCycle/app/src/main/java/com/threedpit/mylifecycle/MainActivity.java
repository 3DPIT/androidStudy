package com.threedpit.mylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Log.d("Main","onCreate호출");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main","onStart호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Main","onStop호출");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main","onDestroy호출");
    }

    @Override
    protected void onPause() {
        super.onPause();
        /**전화 오거나 할때 정지 된경우 데이터 저장*/
        saveState();
        Log.d("Main","onPause호출");
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**복원하기 위한*/
        loadState();

        Log.d("Main","onResume호출");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Main","onRestart호출");
    }
    public  void saveState(){
        /**
         * SharedPreferences 참조 하나 받고
         * getSharedPreferences()는 prefeerences 참조하는 것중 하나
         * Activity.MODE_PRIVATE : 접근범위 설정정         * */
        SharedPreferences pref =getSharedPreferences("pref", Activity.MODE_PRIVATE);
        /**
         * 객체 생성
         * */
        SharedPreferences.Editor editor = pref.edit();
        /**데이터 넣기 이렇게 넣게 되면
         * 입력상자에 사용자가 입력한것 가져올수있고
         * 마지막으로 커밋해주면 저장되는데 파일로 저장된다.
         * 단말 내부에 파일로 저장되기 때문에 앱이 종료되도 유지됨*/
        editor.putString("name",editText.getText().toString());
        editor.commit();
    }
    public void loadState(){
     SharedPreferences pref =getSharedPreferences("pref", Activity.MODE_PRIVATE);
     if(pref!=null){
        String name = pref.getString("name","");
        editText.setText(name);
     }

    }


}
