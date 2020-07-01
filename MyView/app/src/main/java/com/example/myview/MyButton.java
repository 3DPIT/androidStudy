package com.example.myview;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;
//대게 안드로이드는 ui 구성 요소가 있으면 무조건 Context 객체를 가짐
//버튼이 있으면 버튼의 정보를 가진것
public class MyButton extends AppCompatButton{
    public MyButton(Context context) {
        super(context);
        init(context);

    }
//AttributSet은 이버튼을 가지고 new mybutton 소스코드를 가지고
    //버튼객체를 직접 메모리에 만들수 있지만
    //그것말고 xml 레이아웃안에다 < > 이런 표시를해서 mybutton을 추가 할 수 있다.
    //그때의 android : layoutwidth, height 등의 이런 정보가  AttributeSet으로 넘어온다.
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void init(Context context){
        setBackgroundColor(Color.CYAN);
        setTextColor(Color.BLACK);

        //getResources는 res폴더안에 있는 리소스의미
        //이렇게 하면 sp 단위로 받아서 쓸 수 있다.
      float textSize =  getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize); //여기서는 sp 단위로 못하고 픽셀단위로 가능한데 sp단위로 하려면
        //res 폴더안의 values 폴더 에
    }

    //재정의 하는 함수
    //버튼의 크기를 지정함
   /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    */
   //onDraw 특정 무엇을 그릴때
   //onTouchEvent 의 경우 사용자 터치시 사용


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("MyButton","onDraw 호출됨");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyButton","onTouchEvent 호출됨");
        int action = event.getAction();
        switch (action){
            // 손가락으로 누른경우
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.BLUE);
                setTextColor(Color.RED);
                break;
                //손가락 떼는경우
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.CYAN);
                setTextColor(Color.BLACK);
                break;
        }
            invalidate(); // 우리 화면에 보이는것이 유효하지 않다는 것
        //이때 onDraw가 호출되서 다시 그려준다.
            return true;
    }
}
