package com.threedpit.mypager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);// 3개의 프레그먼트 추가할것이라서 3

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        Fragment2 fragment2= new Fragment2();
        adapter.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);// 마지막 이렇게 해줘야 페이지가 보인다.

        //버튼 클릭시 특정 페이지 보여주기
        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);// 이 메소드로 해상 페이지로 넘겨줌
                //0 1 2 순서라 생각하면 된다 그래서 1은 두번째 페이지

               // 점으로 표시하는것은 타이틀 스트립으로 표시할수 있다.
            }
        });
    }
    //뷰페이저 안에 보이는 화면은 프레그먼트로 구현하겠다하는것
    //그 프레그먼트를 관리하는 어댑터를 상속함
    class MyPagerAdapter extends FragmentStatePagerAdapter{
    ArrayList<Fragment> items = new ArrayList<Fragment>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        }
        //프레그먼트 추가
        public void addItem(Fragment item){
            items.add(item);
        }
        @NonNull
        @Override
        // 몇번째 위치에 있는지
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        //프레그먼트 갯수수
       public int getCount() {
            return items.size();
        }
    }

}
