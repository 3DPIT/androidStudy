package com.threedpit.myorientaion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            /**위의 소스를 통해 위가 가로 방향인지 확인을 할 수 있다. */
            showToast("가로방향임");
        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            /**위의 소스를 통해 위가 세로 방향인지 확인을 할 수 있다. */
            showToast("세로방향임");
        }
    }
    public void showToast(String data){
        Toast.makeText(this, data,Toast.LENGTH_SHORT).show();
    }
}
