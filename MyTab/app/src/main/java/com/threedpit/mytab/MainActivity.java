package com.threedpit.mytab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
   Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       toolbar =findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);// 툴바가 화면에 보임

        //getSupportActionBar() 우리가 액션바 설정한것이 여기로 참조해서 가리킴
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);// 타이틀을 보여주게 할꺼냐 이런거

        fragment1=new Fragment1();
        fragment2=new Fragment2();
        fragment3=new Fragment3();

        //        getSupportFragmentManager().프래그먼트 매니저 찾고
        //이렇게 하면 첫번째 프레그먼트가 프레임레이아웃 안에 나옴
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();

        TabLayout tabs =  findViewById(R.id.tabs);//탭버튼을 위한 위젯을 받아오는것
        tabs.addTab(tabs.newTab().setText("통화기록"));//이것은 탭버튼을 추가하는데 통화기록글자 보이게 해라
        //아이콘이나 글자도 넣을수 있다.
        tabs.addTab(tabs.newTab().setText("스팸기록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            // 이함수는 탭버튼이 선택되었을때 동작하는것
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                // 몇번째 탭인지 안드로이드에서는 인덱스를 포지션으로 쓴다.
                if(position==0){
                    //프래그먼트1 번 화면으로 전환
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
                }
                else if(position==1){
                    //프래그먼트2 번 화면으로 전화
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
                }
                else if(position==2){
                    //프래그먼트3 번 화면으로 전화
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
