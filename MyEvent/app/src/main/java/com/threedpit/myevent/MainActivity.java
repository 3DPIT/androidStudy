package com.threedpit.myevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
              /**
               *  onTouch라는 매소드는 뷰를 클릭 했을 떄  사용
               *  손으로 누르거나 떼거나 움직이는 경우
               *  여기서는 모션 이벤트의 객체로
               * */
            int action =  event.getAction();

            float curX = event.getX();
            float curY = event.getY();

            if(action == MotionEvent.ACTION_DOWN){ /**클릭 했을때*/
                println("손가락 눌림 : " + curX + curY);
            }else if(action==MotionEvent.ACTION_MOVE){/**움직였을때 */
                println("손가락 움직임 : " + curX + curY);
            }else if(action==MotionEvent.ACTION_UP){/**클릭을 땠을때*/
                println("손가락 뗌 : " + curX + curY);
            }
            return true; /**onTouch 라는 함수가 정상으로 동작했다*/
            }
        });
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDwon호출됨");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPtess 호출됨");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling 호출됨"+velocityX+" , " +velocityY);
                return true;
            }
        });
        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            println("시스템[BACK] 버튼이 눌렸어요.");

            return true; /**이게 true 되어 있으면 이후 동작이 실행 되지 않아
                                백 버튼을 해도 앱이 꺼지지 않는다.*/
        }
        return false;
    }

    public void println(String data){
        textView.append(data+"\n");
    }
}
