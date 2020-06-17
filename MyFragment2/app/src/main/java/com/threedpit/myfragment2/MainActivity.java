package com.threedpit.myfragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ViewSwitcher;

import java.util.List;

public class MainActivity extends AppCompatActivity implements imageSelectionCallback{
    ListFragment listFragment;
    Viewerfragment viewerfragment;
    //이미지자체를 배열에 넣어 인덱스 0,1,2를 가지게함
    int[] images = {R.drawable.spring,R.drawable.summer,R.drawable.fall};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //메인액티비티에 프래그먼트를 띄우기 위한 초기 설정
       FragmentManager manager = getSupportFragmentManager();
       listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
       viewerfragment = (Viewerfragment) manager.findFragmentById(R.id.viewerFragment);

    }

    //이미지를 바꾸게 하기 위한 메소드 implements 구현을 사용하여 메소드 오버라이딩하여 사용

    // public 으로 쓰여있어서 어디서는 실행이 가능
    @Override
    public void onImageSelected(int position) {
        //이렇게하면 뷰어프래그먼트 화면에 이미지를 띄우겠다 생각하면된다.
         viewerfragment.setImage(images[position]);
    }
}
