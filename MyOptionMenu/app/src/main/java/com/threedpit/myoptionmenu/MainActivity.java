package com.threedpit.myoptionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 이렇게 일단 아이콘으로 변경을 할 수있다.

        actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.home);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME| ActionBar.DISPLAY_USE_LOGO);
    }

    //메뉴를 처음 설정해주는 함수
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //R.menu.menu_main 이걸 지정하면서
        //인플레이터를 이용해서 인플레이션할수 있다.
        //그리고 그것을 파라미터로 전달 받은 menu에 붙여주겠다 하는것
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true; //이렇게 해주면 우리가 xml에 레이아웃에 넣었던 메뉴 아이템이 화면에 보임
    }

    //메뉴 아이템이 선택되었을때 실행되는 함수
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 이렇게 하면 우리가 menu_main.xml 에 설정한 id를 받아옴
        int curId = item.getItemId();
        switch (curId){
            case R.id.menu_refresh:
                showToast("새로고침 메뉴 선택됨");
                break;
            case R.id.menu_search:
                showToast("검색 메뉴 선택됨");
                break;
            case R.id.menu_settings:
                showToast("설정 메뉴 선택됨");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }
}
