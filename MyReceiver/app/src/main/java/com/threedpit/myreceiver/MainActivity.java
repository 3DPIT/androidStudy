package com.threedpit.myreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //필요한 위험 권한 을 다 띄워줌
        AutoPermissions.Companion.loadAllPermissions(this,101);

    }
    // 위험권한에 대한것이 나올때 사용자가 거부를 했는지 승인을 했는지 나옴
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }

    @Override
    public void onDenied(int requestCode, String[] permissions) {
        showToast("권한 거부된 것"+ permissions.length);
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        showToast("권한 승인된 것" + permissions.length);
    }

     public void showToast(String data ){
         Toast.makeText(this,data,Toast.LENGTH_LONG).show();
     }
}
