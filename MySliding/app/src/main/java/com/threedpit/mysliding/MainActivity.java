package com.threedpit.mysliding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Animation translateLeftAnim;
    Animation translateRightAnim;

    LinearLayout page;
    Button button ;

    boolean isPageOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);

        translateLeftAnim = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this,R.anim.translate_right);

        //첫번째 애니메이션이 끝날때 버튼이 열기로 그대로 있는데
        //그 글씨를 닫기로 바꾸고 싶다. 그럴때
        //애니메이션이 종료 되었을 때, 애니메이션이 종료된 정보를 받을 수 있다.
        //그것을 리스너로 받아볼 수 있다.
        SlidingAnimaionListener animListener = new SlidingAnimaionListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPageOpen) {
                    page.startAnimation(translateRightAnim);
                }else {
                    page.setVisibility(View.VISIBLE);// 화면에 보이게 하기
                    page.startAnimation(translateLeftAnim);
                }
            }
        });
    }
    class SlidingAnimaionListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {// 애니메이션 시작하는 시점

        }

        @Override
        public void onAnimationEnd(Animation animation) {//애니메이션 끝나는 시점
            if(isPageOpen){
                page.setVisibility(View.INVISIBLE);// 화면에서 안보이게 하고
                button.setText("열기");// 열기로 버튼을 바꾸고
                isPageOpen = false;//닫힌 상태임을 표시
                 }else{
                button.setText("닫기");//닫기로 버튼을 바꾸고
                isPageOpen = true;//열린 상태임을 표시
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {//애니메이션 반복되는 시점점

        }    }
}
