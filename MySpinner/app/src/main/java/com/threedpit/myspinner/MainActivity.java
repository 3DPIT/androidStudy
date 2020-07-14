package com.threedpit.myspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String[] items = {"kmp","ysk","jsh","msp","syc"};

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                items
        );
        //이것은 spinner객체를 우리가 눌렀을때 선택하는 아이템들이 보이는 레이아웃을 의미
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        //선택된 화면 텍스트뷰로 띄우기
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            textView.setText(items[i]);
        }
        @Override
        //선택한것을 선택하지 않는 상태로 만드는것
        public void onNothingSelected(AdapterView<?> adapterView) {
            textView.setText("");
        }
    });
    }
}
