package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class menu extends AppCompatActivity {
TextView phoneTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView nameTv = findViewById(R.id.nameTextView);
        TextView phoneTv = findViewById(R.id.phoneTextView);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String phone = intent.getExtras().getString("phoneNum");

        nameTv.setText(name);
        phoneTv.setText(phone);
    }
}