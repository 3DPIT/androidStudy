package com.threedpit.myfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment; //메인프래그먼트 참조하기위한 변수
    Main2Fragment main2Fragment;//메인2프래그먼트 참조하기위한 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //아래처럼하면 매니저를 참조할수 있음
        //현재 mainFragment는 메인xml레이아웃에 추가되어있어서 아래와 같이 해야하고
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        //main2프래그먼트의 경우는 메인xml레이아웃에 추가되어 있지 않기 때문에
        main2Fragment = new Main2Fragment();// new로 인스턴스 생성 했다고 동작하는게 아님 이게 액티비티로
        //올라가야 되는것임
    }
        public void onFragmentChanged(int index){
        if(index==0) {
            //여기서 트랜잭션 여러개 명령어를 한번에 사용할때 사용한다.
            // 느낌이 현재 장소에 mainFragment를 보여주라 라는 것
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
        }else if(index==1){
            // 느낌이 현재 장소에 mainFragment를 보여주라 라는 것
            getSupportFragmentManager().beginTransaction().replace(R.id.container, main2Fragment).commit();
        }
    }
}
