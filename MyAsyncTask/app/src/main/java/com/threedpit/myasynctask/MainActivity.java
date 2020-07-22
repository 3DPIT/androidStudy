package com.threedpit.myasynctask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundTask task = new BackgroundTask();
                task.execute();// 이 괄호 안에 값을 넣으면 BackgroundTask클래스에 값 전달
            }
        });
    }
    class BackgroundTask extends AsyncTask<Integer,Integer,Integer> {
        @Override
        protected void onPreExecute() {//쓰레드가 실행되기 전에 실행
            value =0;
            progressBar.setProgress(value);
        }
        @Override
        protected void onPostExecute(Integer integer) {
            progressBar.setProgress(0);//작업이 끝나면 들어가는곳이라 0으로 변경
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue());
        }
        // 나머지 위에꺼는 쓰레드로 동작하다가 UI를 업데이트 하고 싶을때 실행된다.
        //쓰레드 밖인 메인쓰레드의 핸들러로 동작
        @Override
        protected Integer doInBackground(Integer... integers) {//쓰레드로 동작//쓰레드 시작
            while(isCancelled()==false){
                value+=1;
                if(value>=100){
                    break;
                }
                publishProgress(value);//publish에서 onProgressUpdate를 실행한다.
                try{
                    Thread.sleep(1000);
                }catch(Exception e){}
            }
            return value; //여기서 value 리턴하면 onPostExecute 실행
        }
    }

}
