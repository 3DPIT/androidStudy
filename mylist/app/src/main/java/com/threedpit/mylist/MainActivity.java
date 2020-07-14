package com.threedpit.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //그리드 모양으로 보여주거나 테이블 모양으로도 보여줄 수 있는데
        //그게 LayoutManager을 이용한다.
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

       // GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        // 이부분의 경우 사용자가 버튼을 눌러
        //입력한것 대로 나오게 한다던지
        //네트워킹을 통해 받아온 데이터 내부에서 자동으로
        //리사이클뷰에 어댑터에 추가하는 방식이 있다.
        adapter = new PersonAdapter();
        adapter.addItem(new Person("3DPIT","010-0000-0000"));
        adapter.addItem(new Person("4DPIT","010-1111-1111"));
        adapter.addItem(new Person("5DPIT","010-2222-2222"));


        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
               Person item = adapter.getItem(position);
                showToast("아이템 선택됨 : " + item.getName());
            }
        });
    }
    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }

}
