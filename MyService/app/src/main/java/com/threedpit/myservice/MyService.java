package com.threedpit.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    //서비스는 비정상동작시 종료되면 다시 실행됨

    private static final String TAG = "MyService";// 여러번 입력할것 상수롤 만들어준것

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate 호출됨");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestory 호출됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand 호출됨");
        if(intent != null) {
            processCommand(intent); //함수를 만들어서 그 인텐트를 처리 하겠다 하는것
        }else{
        return Service.START_STICKY; //이 명령어가 자동으로 시작해라 하는것
        }
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void processCommand(Intent intent){
        String command =intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.d(TAG,"command : "+command+"name : " +name);

        try {
            Thread.sleep(5000);//5초동안 쉬는것
        }catch (Exception e){
            e.printStackTrace();
        }
        Intent showIntent = new Intent(getApplicationContext(),MainActivity.class);
        //아래의 flags가 없다면 이미 현재 메인 액티비티 화면에서 저게 실행이되는것 이라
        //현재의 같은 화면이 또 뜨게 된다.
        //SINGLE_TOP은 이미 메인엑티비티가 만들어져 있으면 그것을 그대로 보여주라고 하는것이고
        //CLEAR_TOP은  여러개  쌓여있으면 위에 것을 없애라 하는 것
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
        Intent.FLAG_ACTIVITY_SINGLE_TOP|
        Intent.FLAG_ACTIVITY_CLEAR_TOP );
        //NEW_TASK라고 하는것은 화면이 하나의 앱에서 계속 연속적으로 보이는것 처럼 만들어 줄때
        //TASK라는게 무조건 만들어 진다.  화면이 없는 상태에서 화면을 보여주고 싶을때도 만들어 줘야함

        showIntent.putExtra("command","show");//우리가 전달 받은것을 다시 보내기
        showIntent.putExtra("name",name+"from service");
        //메인 액티비티 쪽에서 위의 인텐트를 받아서 처리할 수 있어야하는데
        // 그 인텐트를 받아서 처리하는 방법은 메인액티비티.java에 서
        startActivity(showIntent);
    }

}
