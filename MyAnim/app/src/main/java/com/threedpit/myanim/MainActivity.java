package com.threedpit.myanim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 우리가 만든 애니메이션을 AnimaionUtils에 LoadAnimaion으로 불러와준다.
               Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
                view.startAnimation(anim);//이렇게 해서 해주면 동작한다.
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 우리가 만든 애니메이션을 AnimaionUtils에 LoadAnimaion으로 불러와준다.
                Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale2);
                view.startAnimation(anim2);//이렇게 해서 해주면 동작한다.
            }
        });

    }
}
