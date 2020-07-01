package com.example.mylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Layout1 layout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1 = findViewById(R.id.layout1);
        layout1.setImageView(R.drawable.ic_launcher_foreground);
        layout1.setName("Black");
        layout1.setMoblie("010-0000-0000");

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setImageView(R.drawable.profile);
            }
        });
    }
}